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
import com.jalasoft.jfc.model.result.ErrorResponse;
import com.jalasoft.jfc.model.result.FileResponse;
import com.jalasoft.jfc.model.result.Response;
import com.jalasoft.jfc.model.utility.Md5Checksum;
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
    public Response imageConverter(
            @RequestParam("file") MultipartFile file,  @RequestParam(defaultValue = " ") String md5,
            @RequestParam String outputFileName, @RequestParam (defaultValue = ".png") String imageFormat,
            @RequestParam (defaultValue = "false") boolean Thumbnail,  @RequestParam (defaultValue = "0")
            int ImageWidth, @RequestParam (defaultValue = "0") int ImageHeight, @RequestParam (defaultValue = "0")
            float degreesToRotate) throws CommandValueException {

        FileResponse fileResponse = new FileResponse();
        ErrorResponse errorResponse = new ErrorResponse();
        Param param = new ImageParam();
        ImageParam imageParam = (ImageParam) param;
        String failMd5 = "Md5 Error! binary is invalid.";
        IConverter imageConverter = new ImageConverter();

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadedFile + file.getOriginalFilename());
            Files.write(path, bytes);
            imageParam.setInputPathFile(path.toString());
            String md5FileUploaded = Md5Checksum.getMd5(path.toString());
            imageParam.setMd5(md5);
            String md5FileFromClient = imageParam.getMd5();

            if (md5FileUploaded.equals(md5FileFromClient)) {
                imageParam.setOutputPathFile(convertedFile);
                imageParam.setImageFormat(imageFormat);
                imageParam.setOutputFileName(outputFileName);
                imageParam.isThumbnail(Thumbnail);
                imageParam.setImageWidth(ImageWidth);
                imageParam.setImageHeight(ImageHeight);
                imageParam.setDegreesToRotate(degreesToRotate);

                fileResponse = imageConverter.convert(imageParam);
            }
            else {
                throw new ConvertException(failMd5,this.getClass().getName());
            }

        } catch (ConvertException ex) {
            errorResponse.setName(imageParam.getOutputFileName());
            errorResponse.setStatus("Error! 406 Not Acceptable");
            errorResponse.setError(ex.toString());
            return errorResponse;
        } catch (CommandValueException cve) {
            errorResponse.setName(imageParam.getOutputFileName());
            errorResponse.setStatus("Error! 400 Bad Request");
            errorResponse.setError(cve.toString());
            return errorResponse;
        } catch (IOException ex) {
            errorResponse.setName(imageParam.getOutputFileName());
            errorResponse.setStatus("Error! Bad Request");
            errorResponse.setError(ex.toString());
            return errorResponse;
        } catch (Exception ex) {
            errorResponse.setName(imageParam.getOutputFileName());
            errorResponse.setStatus("Error! 404 Not Found");
            errorResponse.setError(ex.toString());
            return errorResponse;
            // response error result (400, 200)
        }
        return fileResponse;
    }
}
