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
import com.jalasoft.jfc.model.image.ImageConverter;
import com.jalasoft.jfc.model.image.ImageFormat;
import com.jalasoft.jfc.model.image.ImageParam;
import com.jalasoft.jfc.model.exception.ConvertException;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

/**
 * Manage ImageConverter Requests.
 *
 * @author Enrique Carrizales.
 * @version 0.1 13 Dic 2019.
 */
@RestController
@RequestMapping(path = "/imageConverter")
public class ImageConverterController {

    // Constant upload file.
    private static final String UPLOADED_FOLDER = "src/main/java/com/jalasoft/jfc/resource/";

    // Constant path converted file.
    private static final String CONVERTED_FILE = "src/main/java/com/jalasoft/jfc/resource/";

    /**
     * This method receives an image to convert.
     *
     * @param file contains the image file.
     * @param outputPathFile contains the output path of image converted.
     * @param outputPathThumbnail contains the output path of thumbnail converted.
     * @param widthOfFile number of image width.
     * @param heightOfFile number of image height.
     * @param whiteBlankPercentage percentage of whiteBlanck.
     * @param degreesToRotate degrees of rotate.
     * @return the path of the upload file.
     */
    @PostMapping()
    public String imageConverter(
            @RequestParam("file") MultipartFile file, @RequestParam(defaultValue = CONVERTED_FILE)
            String outputPathFile, @RequestParam String outputFileName, @RequestParam(defaultValue = "png")
                    String imageFormat, @RequestParam(defaultValue = CONVERTED_FILE) String outputPathThumbnail,
            @RequestParam(defaultValue = "640") int widthOfFile, @RequestParam(defaultValue = "800")
                    int heightOfFile, @RequestParam(defaultValue = "0") int whiteBlankPercentage,
            @RequestParam(defaultValue = "0") double degreesToRotate) throws ConvertException {

        Param param = new ImageParam();
        ImageParam imageParam = (ImageParam) param;
        IConverter imageConverter = new ImageConverter();
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            imageParam.setInputPathFile(path.toString());
        } catch (IOException e) {
            throw new ConvertException("The there is not a file to upload", "ImageConverterController");
        }
        imageParam.setOutputPathFile(outputPathFile);
        imageParam.setImageFormat(selectFormatImage(imageFormat));
        imageParam.setOutputFileName(outputFileName);
        imageParam.setOutputPathThumbnail(outputPathThumbnail);
        imageParam.setWidthOfFile(widthOfFile);
        imageParam.setHeightOfFile(heightOfFile);
        imageParam.setWhiteBlankPercentage(whiteBlankPercentage);
        imageParam.setDegreesToRotate(degreesToRotate);

        imageConverter.convert(imageParam);

        return "convert" + imageConverter.convert(imageParam);
    }

    private ImageFormat selectFormatImage(String formatImage) {
        ImageFormat formatImageSelected = null;

        try {
            if (formatImage == null) {
                throw new NullPointerException();
            } else {
                if (formatImage.equals("gif")) {
                    formatImageSelected = ImageFormat.GIF;
                }
                if (formatImage.equals("png")) {
                    formatImageSelected = ImageFormat.PNG;
                }
                if (formatImage.equals("jpg") || formatImage.equals("jpeg")) {
                    formatImageSelected = ImageFormat.JPEG;
                }
                if (formatImage.equals("tif")) {
                    formatImageSelected = ImageFormat.TIFF;
                }
            }
        } catch (NullPointerException e) {
            throw new NullPointerException();
        }
        return formatImageSelected;
    }
}
