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
import com.jalasoft.jfc.model.pdf.PdfConverter;
import com.jalasoft.jfc.model.pdf.PdfParam;
import com.jalasoft.jfc.model.video.VideoConverter;
import com.jalasoft.jfc.model.video.VideoParam;
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
    private static final String UPLOADED_FOLDER = "src/main/java/com/jalasoft/jfc/resource/";

    // Constant path converted file.
    private static final String CONVERTED_FILE = "src/main/java/com/jalasoft/jfc/resource/";

     /* @param videoCodec contains videoCodec value.
            * @param audioCodec contains audioCodec value.
            * @param videoBitRate contains videoBitRate value.
            * @param audioBitRate contains audioBitRate value.
            * @param quality contains quality of video.
     * @param channelsNumber contains number of output channels.
            * @param volume contains the level of sound.
            * @param rotate degrees of rotation.*/


    /**
     * videoConverter method receives an video to convert
     * @param file contains the video file.
     * @param outputPathFile contains the output path of file converted.
     * @param outputFileName contains name of converted file.
     * @param aspectRatio contains aspect ratio value.
     * @param frameRate contains the number of images per second.
     * @param wight contains video's wight.
     * @param height contains video's height.

     * @return the path of the upload file.
     */
    @PostMapping
    public String videoConverter(
            @RequestParam("file") MultipartFile file, @RequestParam (defaultValue = CONVERTED_FILE)
            String outputPathFile, @RequestParam String outputFileName, @RequestParam (defaultValue = "4:3")
            double aspectRatio, @RequestParam (defaultValue = "30") String frameRate,
            @RequestParam (defaultValue = "800") int wight, @RequestParam (defaultValue = "640")int height /*,
            @RequestParam (defaultValue = "") String videoCodec, @RequestParam (defaultValue = "") String audioCodec,
            @RequestParam (defaultValue = "") String videoBitRate, @RequestParam (defaultValue = "")
                    String audioBitRate, @RequestParam byte quality, @RequestParam (defaultValue = "") byte channelsNumber,
            @RequestParam (defaultValue = "") String volume, @RequestParam (defaultValue = "") String rotate*/) {

        Param param = new VideoParam("thirdparty\\FFmpeg\\bin\\ffmpeg.exe");
        VideoParam videoParam = (VideoParam) param;
        IConverter videoConverter = new VideoConverter();
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            videoParam.setInputPathFile(path.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        videoParam.setOutputPathFile(outputPathFile);
        videoParam.setOutputFileName(outputFileName);
        videoParam.setAspectRatio(aspectRatio);
        videoParam.setFrameRate(frameRate);
        videoParam.setWight(wight);
        videoParam.setHeight(height);
        //videoParam.setQuality((byte) 5);
        //videoParam.setRotate("transpose=2,transpose=2");

        videoConverter.convert(videoParam);

        return "converted " +videoConverter.convert(videoParam);
    }
}
