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
import com.jalasoft.jfc.model.exception.ErrorMessageJfc;
import com.jalasoft.jfc.model.exception.Md5Exception;
import com.jalasoft.jfc.model.result.MessageResponse;
import com.jalasoft.jfc.model.result.ErrorResponse;
import com.jalasoft.jfc.model.result.FileResponse;
import com.jalasoft.jfc.model.result.Response;
import com.jalasoft.jfc.model.utility.FileServiceController;
import com.jalasoft.jfc.model.utility.LinkGenerator;
import com.jalasoft.jfc.model.utility.Md5Checksum;
import com.jalasoft.jfc.model.exception.CommandValueException;
import com.jalasoft.jfc.model.image.ImageConverter;
import com.jalasoft.jfc.model.image.ImageParam;
import com.jalasoft.jfc.model.exception.ConvertException;
import com.jalasoft.jfc.model.utility.PathJfc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

/**
 * Manages ImageConverter Requests.
 *
 * @version 0.1 13 Dic 2019.
 *
 * @author Enrique Carrizales.
 */
@Api(value = "ImageConverterController", description = "REST API related to ImageParam Entity")
@RestController
@RequestMapping("/api")
public class ImageConverterController {

    /**
     * Receives an image to convert.
     * @param file contains the image file.
     * @param md5 contains the checksum of the file uploaded.
     * @param outputName contains the output file name.
     * @param imageFormat contains the type of format.
     * @param isThumbnail boolean of thumbnail.
     * @param isMetadata boolean of metadata.
     * @param Grayscale boolean of grayscale.
     * @param ImageWidth number of image width.
     * @param ImageHeight number of image height.
     * @param degreesToRotate degrees to rotate.
     * @param request contains client request data.
     * @return Response is the result of the conversion.
     */
    @PostMapping("/imageConverter")
    @ApiOperation(value = "Image specifications", notes = "Provides values for converting Image file to other one",
            response = Response.class)
    public Response imageConverter(
            @RequestParam("file") MultipartFile file, @RequestParam String md5, @RequestParam String outputName,
            @RequestParam(defaultValue = ".png") String imageFormat, @RequestParam(defaultValue = "false")
            boolean isThumbnail, @RequestParam(defaultValue = "false") boolean isMetadata,
            @RequestParam(defaultValue = "false") boolean Grayscale, @RequestParam(defaultValue = "0") int ImageWidth,
            @RequestParam(defaultValue = "0") int ImageHeight, @RequestParam(defaultValue = "0")
            float degreesToRotate, HttpServletRequest request) {

        FileResponse fileResponse = new FileResponse();
        ErrorResponse errorResponse = new ErrorResponse();
        ImageParam imageParam = new ImageParam();
        IConverter imageConverter = new ImageConverter();

        try {
            String fileUploadedPath = FileServiceController.writeFile(PathJfc.getInputFilePath() + file.
                    getOriginalFilename(), file);

                imageParam.setMd5(Md5Checksum.getMd5(fileUploadedPath, md5));
                imageParam.setInputPathFile(fileUploadedPath);
                imageParam.setOutputPathFile(PathJfc.getOutputFilePath());
                imageParam.setImageFormat(imageFormat);
                imageParam.setOutputName(FileServiceController.setName(outputName, file));
                imageParam.isThumbnail(isThumbnail);
                imageParam.isMetadata(isMetadata);
                imageParam.isGrayscale(Grayscale);
                imageParam.setImageWidth(ImageWidth);
                imageParam.setImageHeight(ImageHeight);
                imageParam.setDegreesToRotate(degreesToRotate);
                imageParam.setFolderName(md5);

                fileResponse = imageConverter.convert(imageParam);
                LinkGenerator linkGenerator = new LinkGenerator();
                fileResponse.setDownload(linkGenerator.linkGenerator(fileResponse.getDownload(), request));
        } catch (ConvertException ex) {
            errorResponse.setName(imageParam.getOutputName());
            errorResponse.setStatus(MessageResponse.ERROR406.getMessageResponse());
            errorResponse.setError(ex.toString());
            return errorResponse;
        } catch (CommandValueException cve) {
            errorResponse.setName(imageParam.getOutputName());
            errorResponse.setStatus(MessageResponse.ERROR400.getMessageResponse());
            errorResponse.setError(cve.toString());
            return errorResponse;
        } catch (IOException ex) {
            errorResponse.setName(imageParam.getOutputName());
            errorResponse.setStatus(MessageResponse.ERROR404.getMessageResponse());
            errorResponse.setError(ex.toString());
            return errorResponse;
        } catch (Md5Exception ex) {
            errorResponse.setName(imageParam.getOutputName());
            errorResponse.setStatus(MessageResponse.ERROR406.getMessageResponse());
            errorResponse.setError(ex.toString());
            return errorResponse;
        } catch (Exception ex) {
            errorResponse.setName(imageParam.getOutputName());
            errorResponse.setStatus(MessageResponse.ERROR404.getMessageResponse());
            errorResponse.setError(ex.toString());
            return errorResponse;
        }
        return fileResponse;
    }
}
