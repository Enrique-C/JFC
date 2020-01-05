/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.controller;

import com.jalasoft.jfc.model.exception.Md5Exception;
import com.jalasoft.jfc.model.result.MessageResponse;
import com.jalasoft.jfc.model.result.ErrorResponse;
import com.jalasoft.jfc.model.result.FileResponse;
import com.jalasoft.jfc.model.IConverter;
import com.jalasoft.jfc.model.result.Response;
import com.jalasoft.jfc.model.utility.FileController;
import com.jalasoft.jfc.model.utility.LinkGenerator;
import com.jalasoft.jfc.model.utility.Md5Checksum;
import com.jalasoft.jfc.model.Param;
import com.jalasoft.jfc.model.exception.CommandValueException;
import com.jalasoft.jfc.model.exception.ConvertException;
import com.jalasoft.jfc.model.pdf.PdfConverter;
import com.jalasoft.jfc.model.pdf.PdfParam;
import com.jalasoft.jfc.model.utility.PathJfc;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Manage PdfConverter Requests.
 *
 * @author Enrique Carrizales.
 *
 * @version 0.1 14 Dic 2019.
 */
@RestController
@RequestMapping(path = "/pdfConverter")
public class PdfConverterController {

    // Variable PathJfc type.
    private PathJfc pathJfc;

    // Variable upload file.
    private final String uploadedFile;

    // Variable converted file path.
    private final String convertedFile;

    /**
     * It assigns paths of input and output.
     */
    PdfConverterController() {
        try {
            pathJfc = new PathJfc();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        uploadedFile = pathJfc.getInputFilePath();
        convertedFile = pathJfc.getOutputFilePath();
    }

    /**
     * This method receives a PDF to convert.
     * @param file contains the image file
     * @param md5 contains md5 value.
     * @param outputName contains name of output file.
     * @param rotate degrees of rotation.
     * @param scale contains input Scale 1-10.
     * @param isThumbnail boolean of thumbnail.
     * @param imageFormat format of a image.
     * @param width contains integer value.
     * @param height contains integer value.
     * @param pagesToConvert contains number of pdf file pages.
     * @param request contains client data.
     * @param isMetadata boolean of thumbnail.
     * @return Response it mean the result of the conversion.
     */
    @PostMapping
    public Response pdfConverter(
            @RequestParam("file") MultipartFile file, @RequestParam(defaultValue = " ") String md5,
            @RequestParam String outputName, @RequestParam(defaultValue = "0") int rotate,
            @RequestParam(defaultValue = "%") String scale, @RequestParam(defaultValue = "false") boolean isThumbnail,
            @RequestParam(defaultValue = "false") boolean isMetadata, @RequestParam(defaultValue = ".png")
            String imageFormat, @RequestParam(defaultValue = "0") int width, @RequestParam(defaultValue = "0")
            int height, @RequestParam(defaultValue = "") String pagesToConvert, HttpServletRequest request) {

        PdfParam pdfParam = new PdfParam();
        FileResponse fileResponse = new FileResponse();
        ErrorResponse errorResponse = new ErrorResponse();
        String failMd5 = "Md5 Error! binary is invalid.";
        IConverter pdfConverter = new PdfConverter();

        try {
            String fileUploadedPath = FileController.writeFile(uploadedFile + file.getOriginalFilename(), file);
            PDDocument doc = PDDocument.load(new File(fileUploadedPath));
            int quantityPages = doc.getNumberOfPages();
            String md5FileUploaded = Md5Checksum.getMd5(fileUploadedPath);
            pdfParam.setMd5(md5);
            String md5FileFromClient = pdfParam.getMd5();

            if (md5FileUploaded.equals(md5FileFromClient)) {
                pdfParam.setInputPathFile(fileUploadedPath);
                pdfParam.setOutputPathFile(convertedFile);
                pdfParam.setOutputName(FileController.setName(outputName, file));
                pdfParam.setImageFormat(imageFormat);
                pdfParam.setPagesToConvert(pagesToConvert);
                pdfParam.setQuantityOfPage(quantityPages);
                pdfParam.setThumbnail(isThumbnail);
                pdfParam.isMetadata(isMetadata);
                pdfParam.setWidth(width);
                pdfParam.setScale(scale);
                pdfParam.setHeight(height);
                pdfParam.setRotate(rotate);
                pdfParam.setFolderName(md5FileUploaded);

                fileResponse = pdfConverter.convert(pdfParam);
                LinkGenerator linkGenerator = new LinkGenerator();
                fileResponse.setDownload(linkGenerator.linkGenerator(fileResponse.getDownload(), request));

            }
            else {
                throw new Md5Exception(failMd5, pdfParam.getMd5());
            }

        } catch (ConvertException ex) {
            errorResponse.setName(pdfParam.getOutputName());
            errorResponse.setStatus(MessageResponse.ERROR406.getMessageResponse());
            errorResponse.setError(ex.toString());
            return errorResponse;
        } catch (CommandValueException cve) {
            errorResponse.setName(pdfParam.getOutputName());
            errorResponse.setStatus(MessageResponse.ERROR400.getMessageResponse());
            errorResponse.setError(cve.toString());
            return errorResponse;
        } catch (IOException ex) {
            errorResponse.setName(pdfParam.getOutputName());
            errorResponse.setStatus(MessageResponse.ERROR404.getMessageResponse());
            errorResponse.setError(ex.toString());
            return errorResponse;
        }catch (Md5Exception ex) {
            errorResponse.setName(pdfParam.getOutputName());
            errorResponse.setStatus(MessageResponse.ERROR406.getMessageResponse());
            errorResponse.setError(ex.toString());
            return errorResponse;
        } catch (Exception ex) {
            errorResponse.setName(pdfParam.getOutputName());
            errorResponse.setStatus(MessageResponse.ERROR404.getMessageResponse());
            errorResponse.setError(ex.toString());
            return errorResponse;
        }
        return fileResponse;
    }
}
