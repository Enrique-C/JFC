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
import com.jalasoft.jfc.model.entity.FileEntity;
import com.jalasoft.jfc.model.exception.Md5Exception;
import com.jalasoft.jfc.model.repository.FileRepository;
import com.jalasoft.jfc.model.result.MessageResponse;
import com.jalasoft.jfc.model.result.ErrorResponse;
import com.jalasoft.jfc.model.result.FileResponse;
import com.jalasoft.jfc.model.result.Response;
import com.jalasoft.jfc.model.utility.FileServiceController;
import com.jalasoft.jfc.model.utility.LinkGenerator;
import com.jalasoft.jfc.model.utility.Md5Checksum;
import com.jalasoft.jfc.model.exception.CommandValueException;
import com.jalasoft.jfc.model.utility.PathJfc;
import com.jalasoft.jfc.model.video.VideoConverter;
import com.jalasoft.jfc.model.video.VideoParam;
import com.jalasoft.jfc.model.exception.ConvertException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

/**
 * Manage VideoConverter Requests.
 *
 * @author Enrique Carrizales.
 *
 * @version 0.1 13 Dic 2019.
 */
@Api(value = "VideoConverterController", description = "REST API related to VideoParam Entity")
@RestController
@RequestMapping("/api/v1")
public class VideoConverterController {

    // Inject FileRepository.
    @Autowired
    FileRepository fileRepository;

    /**
     * This method receives an video to convert
     * @param file contains the video file.
     * @param outputName contains the name of converted file.
     * @param aspectRatio contains aspect ratio value.
     * @param frameRate contains the number of images per second.
     * @param width contains video's width.
     * @param height contains video's height.
     * @param videoCodec contains videoCodec value.
     * @param videoBitRate contains videoBitRate value.
     * @param isThumbnail boolean of thumbnail.
     * @param isMetadata boolean of metadata.
     * @param request contains client request data.
     * @return Response is the result of the conversion.
     */
    @PostMapping("/videoConverter")
    @ApiOperation(value = "Video specifications", notes = "Provides values for converting Video file to other one.",
            response = Response.class, authorizations = { @Authorization(value="JWT") })
    public ResponseEntity<Response> videoConverter(
            @RequestParam("file") MultipartFile file, @RequestParam(defaultValue = " ") String md5,
            @RequestParam(defaultValue = "output") String outputName, @RequestParam(defaultValue = " ") String aspectRatio,
            @RequestParam(defaultValue = " ") String frameRate, @RequestParam(defaultValue = "0") int width,
            @RequestParam(defaultValue = "0") int height, @RequestParam(defaultValue = " ") String videoCodec,
            @RequestParam(defaultValue = "0") String videoBitRate, @RequestParam(defaultValue = "false")
            boolean isThumbnail, @RequestParam(defaultValue = "false") boolean isMetadata, HttpServletRequest request,
            @RequestParam(defaultValue = ".avi") String videoFormat) {

        FileResponse fileResponse;
        ErrorResponse errorResponse = new ErrorResponse();
        VideoParam videoParam = new VideoParam();
        IConverter videoConverter = new VideoConverter();

        try {
            FileEntity fileEntity = new FileEntity();
            String cleanMd5 = md5.trim();

            if (fileRepository.findByMd5(cleanMd5) != null) {
                videoParam.setInputPathFile(fileRepository.findByMd5(cleanMd5).getFilePath());
            } else {
                String fileUploadedPath = FileServiceController.writeFile(PathJfc.getInputFilePath() + file
                        .getOriginalFilename(), file);
                cleanMd5 = Md5Checksum.getMd5(fileUploadedPath, cleanMd5);
                videoParam.setInputPathFile(fileUploadedPath);
                fileEntity.setFilePath(fileUploadedPath);
                fileEntity.setMd5(cleanMd5);
                fileRepository.save(fileEntity);
            }

            videoParam.setMd5(cleanMd5);
            videoParam.setInputName(FileServiceController.getName(file));
            videoParam.setOutputPathFile(PathJfc.getOutputFilePath());
            videoParam.setOutputName(outputName);
            videoParam.setAspectRatio(aspectRatio);
            videoParam.setFrameRate(frameRate);
            videoParam.setWidth(width);
            videoParam.setHeight(height);
            videoParam.setVideoCodec(videoCodec);
            videoParam.setVideoBitRate(videoBitRate);
            videoParam.setThumbnail(isThumbnail);
            videoParam.isMetadata(isMetadata);
            videoParam.setVideoFormat(videoFormat);
            videoParam.setFolderName(cleanMd5);

            fileResponse = videoConverter.convert(videoParam);
            LinkGenerator linkGenerator = new LinkGenerator();
            fileResponse.setDownload(linkGenerator.linkGenerator(fileResponse.getDownload(), request));
            fileResponse.setName(videoParam.getFolderName());
            fileResponse.setStatus(MessageResponse.SUCCESS200.getMessageResponse());

            return new ResponseEntity<>(fileResponse, HttpStatus.CREATED);
        } catch (ConvertException ex) {
            errorResponse.setName(videoParam.getOutputName());
            errorResponse.setStatus(MessageResponse.ERROR406.getMessageResponse());
            errorResponse.setError(ex.toString());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
        } catch (CommandValueException cve) {
            errorResponse.setName(videoParam.getOutputName());
            errorResponse.setStatus(MessageResponse.ERROR400.getMessageResponse());
            errorResponse.setError(cve.toString());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } catch (IOException ex) {
            errorResponse.setName(videoParam.getOutputName());
            errorResponse.setStatus(MessageResponse.ERROR404.getMessageResponse());
            errorResponse.setError(ex.toString());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        } catch (Md5Exception ex) {
            errorResponse.setName(outputName);
            errorResponse.setStatus(MessageResponse.ERROR406.getMessageResponse());
            errorResponse.setError(ex.toString());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception ex) {
            errorResponse.setName(videoParam.getOutputName());
            errorResponse.setStatus(MessageResponse.ERROR404.getMessageResponse());
            errorResponse.setError(ex.toString());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }
}
