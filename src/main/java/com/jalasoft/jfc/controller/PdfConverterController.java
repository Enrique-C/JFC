/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 *  ("Confidential Information"). You shall not disclose such Confidential
 *  Information and shall use it only in accordance with the terms of the
 *  license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.controller;

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
 *
 *  Manage PdfConverter Requests.
 *
 * @version 0.1
 *
 * @author Enrique Carrizales
 */
@RestController
@RequestMapping(path = "/pdfConverter")
public class PdfConverterController {

    private static final String UPLOADED_FOLDER =
            "src/main/java/com/jalasoft/jfc/resources/";  //Constant upload file

    /**
     * pdfConverter method receives a PDF to convert.
     * @param file contains the image file.
     * @param inputPathFile contains the input path of the PDF.
     * @param outputPathFile contains the output path of file converted.
     * @param outputFileName contains name of output file.
     * @param rotate degrees of rotation.
     * @param scale contains input Scale 1-10.
     * @param dpi contains level of Scale 1-10.
     * @param imageType type of a image.
     * @param formatImage format of a image.
     * @return get the path of the upload file.
     */
    @PostMapping
    public String pdfConverter(
            @RequestParam("file") MultipartFile file,
            @RequestParam String inputPathFile,
            @RequestParam String outputPathFile,
            @RequestParam String outputFileName,
            @RequestParam int rotate,
            @RequestParam float scale,
            @RequestParam int dpi,
            @RequestParam String imageType,
            @RequestParam String formatImage) {

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return UPLOADED_FOLDER;
    }
}
