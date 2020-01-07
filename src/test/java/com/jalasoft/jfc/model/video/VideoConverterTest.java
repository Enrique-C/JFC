/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.video;

import com.jalasoft.jfc.model.exception.CommandValueException;
import com.jalasoft.jfc.model.exception.ConvertException;
import com.jalasoft.jfc.model.exception.ZipJfcException;
import com.jalasoft.jfc.model.utility.PathJfc;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class VideoConverterTest {

    @Test
    public void Convert_VideoMp4ToAvi_zip() throws ZipJfcException, CommandValueException, ConvertException,
            IOException {
            VideoConverter videoConverter = new VideoConverter();
            VideoParam videoParam = getParamsVideo();

            String zipVideo = videoConverter.convert(videoParam).getDownload();
            final int EMPTY_BYTES = 0;

            File zipFile = new File(zipVideo);
            boolean expected = zipFile.exists() && zipFile.getTotalSpace() > EMPTY_BYTES;

            assertTrue(expected);
        }

        private VideoParam getParamsVideo() {
            PathJfc pathJfc = new PathJfc();

            String fileUploadedPath = "src/test/resources/grabacion.mp4";

            String md5 = "af738d53637a29f531fa0c2bf1eb1516";
            String outputPath = "src/test/resources/";
            String outputName = "video_Test";
            String videoFormat = ".avi";
            boolean isThumbnail = false;
            boolean isMetadata = false;

            VideoParam videoParam = new VideoParam();

            videoParam.setMd5(md5);
            videoParam.setInputPathFile(fileUploadedPath);
            videoParam.setOutputPathFile(outputPath);
            videoParam.setOutputName(outputName);
            videoParam.setAspectRatio("16:9");
            videoParam.setFrameRate("25");
            videoParam.setWidth(1024);
            videoParam.setHeight(720);
            videoParam.setQuality(-1);
            videoParam.setVideoCodec("mpeg4");
            videoParam.setVideoBitRate("300");
            videoParam.setThumbnail(isThumbnail);
            videoParam.isMetadata(isMetadata);
            videoParam.setVideoFormat(videoFormat);
            videoParam.setFolderName(md5);

            return videoParam;
    }
}
