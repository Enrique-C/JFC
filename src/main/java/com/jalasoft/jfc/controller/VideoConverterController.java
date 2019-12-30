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

    // Variable path converted file.
    private final String convertedFile;

    VideoConverterController(){
        try {
            pathJfc = new PathJfc();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        uploadedFile = pathJfc.getInputFilePath();
        convertedFile = pathJfc.getOutputFilePath();
    }

    /**
     * This method receives an video to convert
     * @param file contains the video file.
     * @param outputFileName contains name of converted file.
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
     * @return the path of the upload file.
     */
    @PostMapping
    public String videoConverter(
            @RequestParam("file") MultipartFile file,  @RequestParam (defaultValue = " ") String md5,
            @RequestParam String outputFileName, @RequestParam (defaultValue = "0.0") int aspectRatio,
            @RequestParam (defaultValue = "") String frameRate, @RequestParam (defaultValue = "0") int width,
            @RequestParam (defaultValue = "0") int height, @RequestParam (defaultValue = "") String videoCodec,
            @RequestParam (defaultValue = "") String audioCodec, @RequestParam (defaultValue = "") String videoBitRate,
            @RequestParam (defaultValue = "") String audioBitRate, @RequestParam (defaultValue = "-1") int quality,
            @RequestParam (defaultValue = "0") int channelsNumber, @RequestParam (defaultValue = "") String volume,
            @RequestParam (defaultValue = "") short rotate) throws CommandValueException {

        Md5Checksum md5Checksum = new Md5Checksum();
        Param param = new VideoParam("thirdparty\\FFmpeg\\bin\\ffmpeg.exe");
        VideoParam videoParam = (VideoParam) param;
        String md5FileUploaded = "a";
        String md5FileFromClient = "b";
        String sameMd5 = "Md5 Error! binary is invalid.";
        IConverter videoConverter = new VideoConverter();

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadedFile + file.getOriginalFilename());
            Files.write(path, bytes);
            videoParam.setInputPathFile(path.toString());
            md5FileUploaded = md5Checksum.getMd5(path.toString());
            videoParam.setMd5(md5);
            md5FileFromClient = videoParam.getMd5();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            if (md5FileUploaded.equals(md5FileFromClient)) {
                videoParam.setOutputPathFile(convertedFile);
                videoParam.setOutputFileName(outputFileName);
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

                sameMd5 = "converted " + videoConverter.convert(videoParam).toString();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ConvertException ex) {
            ex.printStackTrace();
        }
        return sameMd5;
    }
}
