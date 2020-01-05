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
import com.jalasoft.jfc.model.exception.Md5Exception;
import com.jalasoft.jfc.model.result.MessageResponse;
import com.jalasoft.jfc.model.result.ErrorResponse;
import com.jalasoft.jfc.model.result.FileResponse;
import com.jalasoft.jfc.model.result.Response;
import com.jalasoft.jfc.model.utility.FileController;
import com.jalasoft.jfc.model.utility.Md5Checksum;
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

    // Variable PathJfc type.
    private PathJfc pathJfc;

    // Variable upload file.
    private final String uploadedFile;

    // Variable converted file path.
    private final String convertedFile;

    /**
     * It assigns paths of input and output.
     */
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
     * @param isThumbnail boolean of thumbnail.
     * @param ImageWidth number of image width.
     * @param ImageHeight number of image height.
     * @param degreesToRotate degrees of rotate.
     * @param md5 contains the checksum of the file uploaded.
     * @param imageFormat contains the type of format.
     * @param isMetadata boolean of metadata.
     * @return Response it mean the result of the conversion.
     */
    @PostMapping()
    public Response imageConverter(
            @RequestParam("file") MultipartFile file, @RequestParam String md5, @RequestParam String outputName,
            @RequestParam(defaultValue = ".png") String imageFormat, @RequestParam(defaultValue = "false")
                    boolean isThumbnail, @RequestParam(defaultValue = "false") boolean isMetadata,
            @RequestParam(defaultValue = "false") boolean Grayscale, @RequestParam(defaultValue = "0") int ImageWidth,
            @RequestParam(defaultValue = "0") int ImageHeight, @RequestParam(defaultValue = "0")
                    float degreesToRotate) {

        FileResponse fileResponse = new FileResponse();
        ErrorResponse errorResponse = new ErrorResponse();
        ImageParam imageParam = new ImageParam();
        String FAILMD5 = "Md5 Error! binary is invalid.";
        IConverter imageConverter = new ImageConverter();

        try {
            String fileUploadedPath = FileController.writeFile(uploadedFile + file.getOriginalFilename(), file);
            imageParam.setInputPathFile(fileUploadedPath);
            String md5FileUploaded = Md5Checksum.getMd5(fileUploadedPath);
            imageParam.setMd5(md5);
            String md5FileFromClient = imageParam.getMd5();

            if (md5FileUploaded.equals(md5FileFromClient)) {
                imageParam.setOutputPathFile(convertedFile);
                imageParam.setImageFormat(imageFormat);
                imageParam.setOutputName(FileController.setName(outputName, file));
                imageParam.isThumbnail(isThumbnail);
                imageParam.isMetadata(isMetadata);
                imageParam.isGrayscale(Grayscale);
                imageParam.setImageWidth(ImageWidth);
                imageParam.setImageHeight(ImageHeight);
                imageParam.setDegreesToRotate(degreesToRotate);
                imageParam.setFolderName(md5FileFromClient);

                fileResponse = imageConverter.convert(imageParam);
            }
            else {
                throw new Md5Exception(FAILMD5, imageParam.getMd5());
            }
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
