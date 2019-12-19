/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.controller;

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
 * @version 0.1 13 Dic 2019.
 *
 * @author Enrique Carrizales
 */
@RestController
@RequestMapping(path = "/videoConverter")
public class VideoConverterController {

    // Constant upload file.
    private static final String UPLOADED_FOLDER = "src/main/java/com/jalasoft/jfc/resources/";

    /**
     * videoConverter method receives an video to convert
     * @param file contains the video file.
     * @param fFmpeg binary variable of FFmpeg.
     * @param inputPathFile contains the input path of the file.
     * @param outputPathFile contains the output path of file converted.
     * @param outputFileName contains name of converted file.
     * @param aspectRatio contains aspect ratio value.
     * @param frameRate contains the number of images per second.
     * @param width contains video's wight.
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
            @RequestParam("file") MultipartFile file, @RequestParam String fFmpeg, @RequestParam String inputPathFile,
            @RequestParam String outputPathFile, @RequestParam String outputFileName, @RequestParam double aspectRatio,
            @RequestParam String frameRate, @RequestParam int width, @RequestParam int height,
            @RequestParam String videoCodec, @RequestParam String audioCodec, @RequestParam String videoBitRate,
            @RequestParam String audioBitRate, @RequestParam byte quality, @RequestParam byte channelsNumber,
            @RequestParam String volume, @RequestParam String rotate) {

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return UPLOADED_FOLDER;
    }
}
