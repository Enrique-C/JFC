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
import com.jalasoft.jfc.model.exception.CommandValueException;
import com.jalasoft.jfc.model.exception.ConvertException;
import com.jalasoft.jfc.model.pdf.PdfConverter;
import com.jalasoft.jfc.model.pdf.PdfParam;
import com.jalasoft.jfc.model.utility.PathJfc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.pdfbox.pdmodel.PDDocument;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.IOException;

/**
 * Manage PdfConverter Requests.
 *
 * @author Enrique Carrizales.
 *
 * @version 0.1 14 Dic 2019.
 */
@Api(value = "PdfConverterController", description = "REST API related to PdfParam Entity")
@RestController
@RequestMapping("/api")
public class PdfConverterController {

    /**
     * This method receives a PDF to convert.
     * @param file contains the image file
     * @param md5 contains md5 value.
     * @param outputName contains the name of output file.
     * @param rotate degrees to rotate.
     * @param scale contains input Scale 1-10.
     * @param isThumbnail boolean of thumbnail.
     * @param imageFormat format of a image.
     * @param width contains integer value.
     * @param height contains integer value.
     * @param pagesToConvert contains number of pdf file pages.
     * @param request contains client data.
     * @param isMetadata boolean of metadata.
     * @return Response is the result of the conversion.
     */
    @PostMapping("/pdfConverter")
    @ApiOperation(value = "Pdf specifications", notes = "Provides values for converting Pdf file to Image",
            response = Response.class)
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
            String fileUploadedPath = FileController.writeFile(PathJfc.getInputFilePath() + file.
                    getOriginalFilename(), file);
            PDDocument doc = PDDocument.load(new File(fileUploadedPath));
            int quantityPages = doc.getNumberOfPages();

            if (Md5Checksum.getMd5(fileUploadedPath, md5)) {
                pdfParam.setMd5(md5);
                pdfParam.setInputPathFile(fileUploadedPath);
                pdfParam.setOutputPathFile(PathJfc.getOutputFilePath());
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
                pdfParam.setFolderName(md5);

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
