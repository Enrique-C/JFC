/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.controller;

import com.jalasoft.jfc.model.result.FileResponse;
import com.jalasoft.jfc.model.IConverter;
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
     * @param outputFileName contains name of output file.
     * @param rotate degrees of rotation.
     * @param scale contains input Scale 1-10.
     * @param imageFormat format of a image.
     * @return Result of the conversion.
     */
    @PostMapping
    public FileResponse pdfConverter(
            @RequestParam("file") MultipartFile file,  @RequestParam (defaultValue = " ") String md5,
            @RequestParam String outputFileName,@RequestParam(defaultValue = "0") int rotate,
            @RequestParam(defaultValue = "%") String scale, @RequestParam(defaultValue = "false") boolean thumbnail,
            @RequestParam(defaultValue = ".png") String imageFormat, @RequestParam(defaultValue = "0") int width,
            @RequestParam(defaultValue = "0") int height, @RequestParam(defaultValue = "") String pagesToConvert) {

        Param param = new PdfParam();
        PdfParam pdfParam = (PdfParam) param;
        FileResponse fileResult = new FileResponse();
        String sameMd5 = "Md5 Error! binary is invalid.";
        int quantityPages = 0;
        IConverter pdfConverter = new PdfConverter();

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadedFile + file.getOriginalFilename());
            Files.write(path, bytes);
            PDDocument doc = PDDocument.load(new File(uploadedFile + file.getOriginalFilename()));
            quantityPages = doc.getNumberOfPages();
            pdfParam.setInputPathFile(path.toString());
            if (outputFileName.equals(null) || outputFileName.equals("")){
                outputFileName = file.getOriginalFilename();
                outputFileName = outputFileName.replaceFirst("[.][^.]+$", "");
            }
            String md5FileUploaded = Md5Checksum.getMd5(path.toString());
            pdfParam.setMd5(md5);
            String md5FileFromClient = pdfParam.getMd5();

            if (md5FileUploaded.equals(md5FileFromClient)) {
                pdfParam.setOutputPathFile(convertedFile);
                pdfParam.setOutputFileName(outputFileName);
                pdfParam.setImageFormat(imageFormat);
                pdfParam.setPagesToConvert(pagesToConvert);
                pdfParam.setQuantityOfPage(quantityPages);
                pdfParam.setThumbnail(thumbnail);
                pdfParam.setWidth(width);
                pdfParam.setScale(scale);
                pdfParam.setHeight(height);
                pdfParam.setRotate(rotate);
                pdfParam.setFolderName(md5FileUploaded);

                pdfConverter.convert(pdfParam);
                fileResult.setDownload(pdfParam.getOutputPathFile() +pdfParam.getFolderName());
            }
        }  catch (ConvertException ex) {
            ex.printStackTrace();
        } catch (CommandValueException cve) {
            cve.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
            // response error result (400, 200)
        }
        return fileResult;
    }

}
