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
import com.jalasoft.jfc.model.image.ImageFormat;
import com.jalasoft.jfc.model.pdf.PdfConverter;
import com.jalasoft.jfc.model.pdf.PdfParam;
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
            @RequestParam (defaultValue = "1") String scale, @RequestParam (defaultValue = "100") int dpi,
            @RequestParam String imageType, @RequestParam String formatImage) throws ConvertException {

        Param param = new PdfParam();
        PdfParam pdfParam = (PdfParam) param;
        IConverter pdfConverter = new PdfConverter();
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            pdfParam.setInputPathFile(path.toString());
        } catch (IOException e) {
            throw new ConvertException("Aqui el nuevo mensaje","Aqui dondeen que lugar se genera");
        }
        pdfParam.setOutputPathFile(outputPathFile);
        pdfParam.setOutputFileName(outputFileName);
        pdfParam.setRotate(rotate);
        pdfParam.setScale(scale);

        return pdfConverter.convert(pdfParam).toString();
    }

    private ImageFormat selectFormatImage(String formatImage) throws ConvertException {
        ImageFormat formatImageSelected = null;
        try{
            if (formatImage == null){
                throw new ConvertException("Aqui el nuevo mensaje","Aqui dondeen que lugar se genera");
            }else {
                if (formatImage.equals("gif")) {
                    formatImageSelected = ImageFormat.GIF;
                }
                if (formatImage.equals("png")) {
                    formatImageSelected = ImageFormat.PNG;
                }
                if (formatImage.equals("jpg") || formatImage.equals("jpeg") ) {
                    formatImageSelected = ImageFormat.JPEG ;
                }
            }
        }catch (NullPointerException e){
            throw new ConvertException("Aqui el nuevo mensa","Aqui dondeen que lugar se genera");
        }
        return formatImageSelected;
    }

    private ImageType selectImageType(String imageType) throws ConvertException {
        ImageType imageTypeSelected = null;
        try{
            if (imageType == null ){
                throw new ConvertException("Aqui el nuevo mensaje","Aqui dondeen que lugar se genera");
            }else {
                if (imageType.equals("gray")) {
                    imageTypeSelected = ImageType.GRAY;
                }
                if (imageType.equals("binary")) {
                    imageTypeSelected = ImageType.BINARY;
                }
                if (imageType.equals("rgb") ) {
                    imageTypeSelected = ImageType.RGB;
                }
            }
        }catch (IllegalArgumentException e){
            throw new ConvertException("Aqui el nuevo mensaje","Aqui dondeen que lugar se genera");
        }
        return imageTypeSelected;
    }
}
