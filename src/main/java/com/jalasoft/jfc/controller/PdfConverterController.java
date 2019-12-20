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
    private static final String UPLOADED_FOLDER = "src/main/java/com/jalasoft/jfc/resource/";

    // Constant path converted file.
    private static final String CONVERTED_FILE = "src/main/java/com/jalasoft/jfc/resource/";

    /**
     * pdfConverter method receives a PDF to convert.
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
            @RequestParam("file") MultipartFile file, @RequestParam (defaultValue = CONVERTED_FILE)
            String outputPathFile, @RequestParam String outputFileName, @RequestParam(defaultValue = "90") int rotate,
            @RequestParam (defaultValue = "300%") String scale, @RequestParam String thumbnail,
            @RequestParam (defaultValue = ".png")String imageFormat, @RequestParam (defaultValue = "1024") int wight,
            @RequestParam (defaultValue = "720") int height, @RequestParam String pagesToConvert) {

        Param param = new PdfParam();
        PdfParam pdfParam = (PdfParam) param;
        pdfParam.setMagick("thirdparty/ImageMagick/magick.exe");
        IConverter pdfConverter = new PdfConverter();
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            pdfParam.setInputPathFile(path.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        pdfParam.setOutputPathFile(outputPathFile);
        pdfParam.setOutputFileName(outputFileName);
        //pdfParam.setThumbnail(thumbnail);
        pdfParam.setImageFormat(imageFormat);
        //pdfParam.setWight(wight);
        //pdfParam.setHeight(height);
        //pdfParam.setPagesToConvert(pagesToConvert);
        //pdfParam.setRotate(rotate);
        //pdfParam.setScale(scale);

        return "convert" + pdfConverter.convert(pdfParam);
    }

    private ImageFormat selectFormatImage(String formatImage) {
        ImageFormat formatImageSelected = null;
        try{
            if (formatImage == null){
                throw new NullPointerException();
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
            throw new IllegalArgumentException();
        }
        return imageTypeSelected;
    }
}
