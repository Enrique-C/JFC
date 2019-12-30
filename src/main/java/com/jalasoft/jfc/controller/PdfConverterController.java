/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.controller;

import com.jalasoft.jfc.model.IConverter;
import com.jalasoft.jfc.model.Md5Checksum;
import com.jalasoft.jfc.model.Param;
import com.jalasoft.jfc.model.exception.CommandValueException;
import com.jalasoft.jfc.model.exception.ConvertException;
import com.jalasoft.jfc.model.pdf.PdfConverter;
import com.jalasoft.jfc.model.pdf.PdfParam;

import com.jalasoft.jfc.model.utility.PathJfc;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    // Variable path converted file.
    private final String convertedFile;

    PdfConverterController(){
        try {
            pathJfc = new PathJfc();
        }
        catch (IOException ex){
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
     * @return the path of the upload file.
     */
    @PostMapping
    public String pdfConverter(
            @RequestParam("file") MultipartFile file,  @RequestParam (defaultValue = " ") String md5,
            @RequestParam String outputFileName,@RequestParam(defaultValue = "0") int rotate,
            @RequestParam(defaultValue = "%") String scale, @RequestParam(defaultValue = "false") boolean thumbnail,
            @RequestParam(defaultValue = ".png") String imageFormat, @RequestParam(defaultValue = "0") int width,
            @RequestParam(defaultValue = "0") int height, @RequestParam String pagesToConvert) {

        Md5Checksum md5Checksum = new Md5Checksum();
        Param param = new PdfParam();
        PdfParam pdfParam = (PdfParam) param;
        String md5FileUploaded = "a";
        String md5FileFromClient = "b";
        String sameMd5 = "Md5 Error! binary is invalid.";
        IConverter pdfConverter = new PdfConverter();

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadedFile + file.getOriginalFilename());
            Files.write(path, bytes);
            pdfParam.setInputPathFile(path.toString());
            md5FileUploaded = md5Checksum.getMd5(path.toString());
            pdfParam.setMd5(md5);
            md5FileFromClient = pdfParam.getMd5();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            if (md5FileUploaded.equals(md5FileFromClient)) {
                pdfParam.setOutputPathFile(convertedFile);
                pdfParam.setOutputFileName(outputFileName);
                pdfParam.setImageFormat(imageFormat);
                pdfParam.setPagesToConvert(pagesToConvert);
                pdfParam.setThumbnail(thumbnail);
                pdfParam.setWidth(width);
                pdfParam.setScale(scale);
                pdfParam.setHeight(height);

                sameMd5 = "convert " + pdfConverter.convert(pdfParam).toString();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ConvertException ex) {
            ex.printStackTrace();
        } catch (CommandValueException cve) {
            cve.printStackTrace();
        } catch (NullPointerException nex) {
            nex.printStackTrace();
        }
        return sameMd5;
    }
}
