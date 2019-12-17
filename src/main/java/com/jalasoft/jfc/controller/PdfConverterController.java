/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 *  ("Confidential Information"). You shall not disclose such Confidential
 *  Information and shall use it only in accordance with the terms of the
 *  license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.controller;

import com.jalasoft.jfc.model.PdfConverter;
import com.jalasoft.jfc.model.PdfFormatImage;
import com.jalasoft.jfc.model.PdfParam;
import org.apache.pdfbox.rendering.ImageType;
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
 * @version 0.1 14 Dic 2019.
 *
 * @author Enrique Carrizales
 */
@RestController
@RequestMapping(path = "/pdfConverter")
public class PdfConverterController {

    // Constant upload file.
    private static final String UPLOADED_FOLDER = "src/main/java/com/jalasoft/jfc/resources/";
    // Constant path converted file.
    private static final String CONVERTED_FILE = "src/main/java/com/jalasoft/jfc/resources/";

    /**
     * pdfConverter method receives a PDF to convert.
     * @param outputPathFile contains the output path of file converted.
     * @param file contains the image file
     * @param outputFileName contains name of output file.
     * @param rotate degrees of rotation.
     * @param scale contains input Scale 1-10.
     * @param dpi contains level of Scale 1-10.
     * @param imageType type of a image.
     * @param formatImage format of a image.
     * @return the path of the upload file.
     */
    @PostMapping
    public String pdfConverter(
            @RequestParam("file") MultipartFile file, @RequestParam (defaultValue = CONVERTED_FILE)
            String outputPathFile, @RequestParam String outputFileName, @RequestParam(defaultValue = "0") int rotate,
            @RequestParam (defaultValue = "1") float scale, @RequestParam (defaultValue = "100") int dpi,
            @RequestParam String imageType, @RequestParam String formatImage) {

        PdfParam param = new PdfParam();
        PdfConverter pdf = new PdfConverter();
        String convertionState = "Failed Conversion";
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            param.setInputPathFile(path.toString());
            param.setOutputPathFile(outputPathFile);
            param.setOutputFileName(outputFileName);
            param.setRotate(rotate);
            param.setDpi(dpi);
            param.setScale(scale);
            param.setImageType(selectImageType(imageType));
            param.setPdfFormatImage(selectFormatImage(formatImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(pdf.convert(param))
        {
            convertionState = "Successful Conversion";
        }
        return convertionState;
    }

    private PdfFormatImage selectFormatImage(String formatImage) {
        PdfFormatImage formatImageSelected = null;
        try{
            if (formatImage == null){
                throw new NullPointerException();
            }else {
                if (formatImage.equals("bmp"))
                {
                    formatImageSelected = PdfFormatImage.BMP;
                }
                if (formatImage.equals("gif"))
                {
                    formatImageSelected = PdfFormatImage.GIF;
                }
                if (formatImage.equals("png"))
                {
                    formatImageSelected = PdfFormatImage.PNG;
                }
                if (formatImage.equals("jpg") || formatImage.equals("jpeg") )
                {
                    formatImageSelected = PdfFormatImage.JPEG ;
                }
            }
        }catch (NullPointerException e){
            throw new NullPointerException();
        }
        return formatImageSelected;
    }

    private ImageType selectImageType(String imageType) {

        ImageType imageTypeSelected = null;
        try{
            if (imageType == null ){
                throw new IllegalArgumentException();
            }else {
                if (imageType.equals("gray"))
                {
                    imageTypeSelected = ImageType.GRAY;
                }
                if (imageType.equals("binary"))
                {
                    imageTypeSelected = ImageType.BINARY;
                }
                if (imageType.equals("rgb") ) {
                    imageTypeSelected = ImageType.RGB;
                }
            }
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException();
        }
        return imageTypeSelected;
    }
}
