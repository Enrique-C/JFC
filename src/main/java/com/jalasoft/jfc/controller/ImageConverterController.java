/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.controller;

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
 * @version 0.1 13 Dic 2019.
 *
 * @author Enrique Carrizales.
 */
@RestController
@RequestMapping(path = "/imageConverter")
public class ImageConverterController {

    // Constant upload file
    private static final String UPLOADED_FOLDER = "src/main/java/com/jalasoft/jfc/resources/";

    /**
     * imageConverter method receives an image to convert.
     * @param file contains the image file.
     * @param inputPathFile contains the input path of the image.
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
            @RequestParam("file") MultipartFile file, @RequestParam String inputPathFile,
            @RequestParam String outputPathFile, @RequestParam String outputPathThumbnail,
            @RequestParam String widthOfFile, @RequestParam String heightOfFile,
            @RequestParam String whiteBlankPercentage, @RequestParam String degreesToRotate) throws ConvertException {

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
        } catch (IOException e) {
            throw new ConvertException("To Do Message","To Do Error in method that was generated");
        }
        return UPLOADED_FOLDER;
    }
}
