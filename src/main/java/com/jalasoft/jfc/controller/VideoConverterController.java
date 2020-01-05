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
import com.jalasoft.jfc.model.Param;
import com.jalasoft.jfc.model.exception.CommandValueException;
import com.jalasoft.jfc.model.utility.PathJfc;
import com.jalasoft.jfc.model.video.VideoConverter;
import com.jalasoft.jfc.model.video.VideoParam;
import com.jalasoft.jfc.model.exception.ConvertException;

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
 * Manage VideoConverter Requests.
 *
 * @author Enrique Carrizales.
 *
 * @version 0.1 13 Dic 2019.
 */
@RestController
@RequestMapping(path = "/videoConverter")
public class VideoConverterController {

    // Variable PathJfc type.
    private PathJfc pathJfc;

    // Variable upload file.
    private final String uploadedFile;

    // Variable converted file path.
    private final String convertedFile;

    /**
     * It assigns paths of input and output.
     */
    VideoConverterController() {
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
     * This method receives an video to convert
     * @param file contains the video file.
     * @param outputName contains name of converted file.
     * @param aspectRatio contains aspect ratio value.
     * @param frameRate contains the number of images per second.
     * @param width contains video's width.
     * @param height contains video's height.
     * @param videoCodec contains videoCodec value.
     * @param audioCodec contains audioCodec value.
     * @param videoBitRate contains videoBitRate value.
     * @param audioBitRate contains audioBitRate value.
     * @param quality contains quality of video.
     * @param channelsNumber contains number of output channels.
     * @param volume contains the level of sound.
     * @param rotate degrees of rotation.
     * @param isThumbnail boolean of thumbnail.
     * @param isMetadata boolean of metadata.
     * @return Response it mean the result of the conversion.
     */
    @PostMapping
    public Response videoConverter(
            @RequestParam("file") MultipartFile file, @RequestParam(defaultValue = " ") String md5,
            @RequestParam String outputName, @RequestParam(defaultValue = "0.0") String aspectRatio,
            @RequestParam(defaultValue = "") String frameRate, @RequestParam(defaultValue = "0") int width,
            @RequestParam(defaultValue = "0") int height, @RequestParam(defaultValue = "") String videoCodec,
            @RequestParam(defaultValue = "") String audioCodec, @RequestParam(defaultValue = "") String videoBitRate,
            @RequestParam(defaultValue = "") String audioBitRate, @RequestParam(defaultValue = "-1") int quality,
            @RequestParam(defaultValue = "0") int channelsNumber, @RequestParam(defaultValue = "") String volume,
            @RequestParam(defaultValue = "") short rotate, @RequestParam(defaultValue = "") boolean isThumbnail,
            @RequestParam(defaultValue = "false") boolean isMetadata) {

        FileResponse fileResponse = new FileResponse();
        ErrorResponse errorResponse = new ErrorResponse();
        VideoParam videoParam = new VideoParam();
        String failMd5 = "Md5 Error! binary is invalid.";
        IConverter videoConverter = new VideoConverter();

        try {
            String fileUploadedPath = FileController.writeFile(uploadedFile + file.getOriginalFilename(), file);

            if (Md5Checksum.getMd5(fileUploadedPath, md5)) {
                videoParam.setMd5(md5);
                videoParam.setInputPathFile(fileUploadedPath);
                videoParam.setOutputPathFile(convertedFile);
                videoParam.setOutputName(FileController.setName(outputName, file));
                videoParam.setAspectRatio(aspectRatio);
                videoParam.setFrameRate(frameRate);
                videoParam.setWidth(width);
                videoParam.setHeight(height);
                videoParam.setQuality(quality);
                videoParam.setChannelsNumber(channelsNumber);
                videoParam.setVolume(volume);
                videoParam.setRotate(rotate);
                videoParam.setVideoCodec(videoCodec);
                videoParam.setAudioCodec(audioCodec);
                videoParam.setVideoBitRate(videoBitRate);
                videoParam.setAudioBitRate(audioBitRate);
                videoParam.setThumbnail(isThumbnail);
                videoParam.isMetadata(isMetadata);
                videoParam.setFolderName(md5);

                fileResponse = videoConverter.convert(videoParam);
            }
            else {
                throw new Md5Exception(failMd5, videoParam.getMd5());
            }
        } catch (ConvertException ex) {
            errorResponse.setName(videoParam.getOutputName());
            errorResponse.setStatus(MessageResponse.ERROR406.getMessageResponse());
            errorResponse.setError(ex.toString());
            return errorResponse;
        } catch (CommandValueException cve) {
            errorResponse.setName(videoParam.getOutputName());
            errorResponse.setStatus(MessageResponse.ERROR400.getMessageResponse());
            errorResponse.setError(cve.toString());
            return errorResponse;
        } catch (IOException ex) {
            errorResponse.setName(videoParam.getOutputName());
            errorResponse.setStatus(MessageResponse.ERROR404.getMessageResponse());
            errorResponse.setError(ex.toString());
            return errorResponse;
        } catch (Md5Exception ex) {
            errorResponse.setName(videoParam.getOutputName());
            errorResponse.setStatus(MessageResponse.ERROR406.getMessageResponse());
            errorResponse.setError(ex.toString());
            return errorResponse;
        } catch (Exception ex) {
            errorResponse.setName(videoParam.getOutputName());
            errorResponse.setStatus(MessageResponse.ERROR404.getMessageResponse());
            errorResponse.setError(ex.toString());
            return errorResponse;
        }
        return fileResponse;
    }
}
