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
import com.jalasoft.jfc.model.image.ImageConverter;
import com.jalasoft.jfc.model.image.ImageParam;
import com.jalasoft.jfc.model.exception.ConvertException;
import com.jalasoft.jfc.model.utility.PathJfc;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

/**
 * Manage ImageConverter Requests.
 *
 * @author Enrique Carrizales.
 *
 * @version 0.1 13 Dic 2019.
 */
@RestController
@RequestMapping(path = "/imageConverter")
public class ImageConverterController {

    // Variable PathJfc type.
    private PathJfc pathJfc;

    // Variable upload file.
    private final String uploadedFile;

    // Variable converted file path.
    private final String convertedFile;

    ImageConverterController() {
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
     * This method receives an image to convert.
     * @param file contains the image file.
     * @param Thumbnail contains the output path of thumbnail converted.
     * @param ImageWidth number of image width.
     * @param ImageHeight number of image height.
     * @param degreesToRotate degrees of rotate.
     * @return the path of the upload file.
     */
    @PostMapping()
    public String imageConverter(
            @RequestParam("file") MultipartFile file,  @RequestParam(defaultValue = " ") String md5,
            @RequestParam String outputFileName, @RequestParam (defaultValue = ".png") String imageFormat,
            @RequestParam (defaultValue = "false") boolean Thumbnail,  @RequestParam (defaultValue = "0")
            int ImageWidth, @RequestParam (defaultValue = "0") int ImageHeight, @RequestParam (defaultValue = "0")
            float degreesToRotate) throws CommandValueException {

        Md5Checksum md5Checksum = new Md5Checksum();
        Param param = new ImageParam();
        ImageParam imageParam = (ImageParam) param;
        String md5FileUploaded = "a";
        String md5FileFromClient = "b";
        String sameMd5 = "Md5 Error! binary is invalid.";
        IConverter imageConverter = new ImageConverter();

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadedFile + file.getOriginalFilename());
            Files.write(path, bytes);
            imageParam.setInputPathFile(path.toString());
            md5FileUploaded = md5Checksum.getMd5(path.toString());
            imageParam.setMd5(md5);
            md5FileFromClient = imageParam.getMd5();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            if (md5FileUploaded.equals(md5FileFromClient)) {
                imageParam.setOutputPathFile(convertedFile);
                imageParam.setImageFormat(imageFormat);
                imageParam.setOutputFileName(outputFileName);
                imageParam.isThumbnail(Thumbnail);
                imageParam.setImageWidth(ImageWidth);
                imageParam.setImageHeight(ImageHeight);
                imageParam.setDegreesToRotate(degreesToRotate);

                sameMd5 = "convert" + imageConverter.convert(imageParam).toString();
            }
        }  catch (ConvertException ex) {
            ex.printStackTrace();
        } catch (CommandValueException cve) {
            cve.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sameMd5;
    }
}
