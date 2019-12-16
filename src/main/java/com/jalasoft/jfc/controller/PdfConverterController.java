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
     * @param file contains the image file.
     * @param inputPathFile contains the input path of the PDF.
     * @param outputPathFile contains the output path of file converted.
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
            @RequestParam("file") MultipartFile file, @RequestParam String inputPathFile,
            @RequestParam String outputPathFile, @RequestParam String outputFileName,
            @RequestParam(defaultValue = "0") int rotate, @RequestParam float scale, @RequestParam int dpi,
            @RequestParam String imageType, @RequestParam String formatImage) {

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            PdfConverter pdf = new PdfConverter();
            PdfParam param = new PdfParam();
            //param.setPathInput("c:/Users/Admin/Downloads/cocina-aire-libre.pdf");
            param.setPathInput(path.toString());
            //param.setPathOuput ("c:/Users/Admin/Desktop/pruebasConvertPDF/");
            param.setPathOuput (CONVERTED_FILE);
            //param.setOutputFileName("ImageCoFinal_");
            param.setOutputFileName(outputFileName);
            //param.setImageType(ImageType.RGB);
            param.setImageType(selectImageType(imageType));
            param.setPdfFormatImage(selectFormatImage(formatImage));
            pdf.convert(param);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return UPLOADED_FOLDER;
    }

    private PdfFormatImage selectFormatImage(String formatImage) {
        PdfFormatImage formatImageSelected = PdfFormatImage.JPEG;
        try{
            if (formatImage != null || !formatImage.equals("jpg") || !formatImage.equals("bmp") || !formatImage.equals("gif")
                    || !formatImage.equals("png") || !formatImage.equals("wbmp")){
                throw new NullPointerException();
            }else {
                if (formatImage =="bmp")
                {
                    formatImageSelected = PdfFormatImage.BMP;
                }
                if (formatImage =="gif")
                {
                    formatImageSelected = PdfFormatImage.GIF;
                }
                if (formatImage =="png")
                {
                    formatImageSelected = PdfFormatImage.PNG;
                }
                if (formatImage =="wbmp")
                {
                    formatImageSelected = PdfFormatImage.WBMP;
                }
            }
        }catch (NullPointerException e){
            throw new NullPointerException();
        }
        return formatImageSelected;
    }


    private ImageType selectImageType(String imageType) {

        ImageType imageTypeSelected = ImageType.RGB;
        try{
            if (imageType != null || !imageType.equals("rgb") || !imageType.equals("gray")
                    || !imageType.equals("binary") || !imageType.equals("argb")){
                throw new NullPointerException();
            }else {
                if (imageType =="gray")
                {
                    imageTypeSelected = ImageType.GRAY;
                }
                if (imageType =="binary")
                {
                    imageTypeSelected = ImageType.BINARY;
                }
                if (imageType =="argb")
                {
                    imageTypeSelected = ImageType.ARGB;
                }
            }
        }catch (NullPointerException e){
            throw new NullPointerException();
        }
        return imageTypeSelected;
    }
}
