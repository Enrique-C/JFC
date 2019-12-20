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
import com.jalasoft.jfc.model.Param;
import com.jalasoft.jfc.model.exception.ConvertException;
import com.jalasoft.jfc.model.pdf.PdfConverter;
import com.jalasoft.jfc.model.pdf.PdfParam;

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

    // Constant upload file.
    private static final String UPLOADED_FOLDER = "src/main/java/com/jalasoft/jfc/resource/";

    // Constant path converted file.
    private static final String CONVERTED_FILE = "src/main/java/com/jalasoft/jfc/resource/";

    /**
     * This method receives a PDF to convert.
     *
     * @param outputPathFile contains the output path of file converted.
     * @param file contains the image file
     * @param outputFileName contains name of output file.
     * @param rotate degrees of rotation.
     * @param scale contains input Scale 1-10.
     * @param imageFormat format of a image.
     * @return the path of the upload file.
     */
    @PostMapping
    public String pdfConverter(
            @RequestParam("file") MultipartFile file, @RequestParam(defaultValue = CONVERTED_FILE)
            String outputPathFile, @RequestParam String outputFileName, @RequestParam(defaultValue = "90") int rotate,
            @RequestParam(defaultValue = "300%") String scale, @RequestParam(defaultValue = "x128") String thumbnail,
            @RequestParam(defaultValue = ".png") String imageFormat, @RequestParam(defaultValue = "1024") int wight,
            @RequestParam(defaultValue = "720") int height, @RequestParam String pagesToConvert)
            throws ConvertException {

        Param param = new PdfParam();
        PdfParam pdfParam = (PdfParam) param;
        pdfParam.setMagick("thirdparty/ImageMagick/magick.exe");
        IConverter pdfConverter = new PdfConverter();
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            pdfParam.setInputPathFile(path.toString());
        } catch (IOException ex) {
            throw new ConvertException("The there is not a file to upload", "PdfConverterController");
        }
        pdfParam.setOutputPathFile(outputPathFile);
        pdfParam.setOutputFileName(outputFileName);
        pdfParam.setImageFormat(imageFormat);

        return "convert " + pdfConverter.convert(pdfParam);
    }
}
