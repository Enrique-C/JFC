/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class is used to get paths.
 *
 * @version 0.1 23 Dic 2019
 *
 * @author Alan Escalera
 */
public class PathJfc {

    // This is a class properties.
    private Properties properties;

    // This is a InputStream.
    private InputStream inputPath;

    // This is a constant variable to get application.properties file.
    private final String APPLICATION_PROPERTIES = "application.properties";

    // This is a constant variable of the key imageMagick path.
    private final String IMAGE_MAGICK_PATH = "path.magick";

    // This is a constant variable of key FFmpeg path.
    private final String FFMPEG_PATH = "path.ffmpeg";

    /**
     * This inizialize properties and inputPaht. Also load the inputPath in properties.
     * @throws IOException
     */
    public PathJfc() throws IOException {
        properties = new Properties();
        inputPath = PathJfc.class.getClassLoader().getResourceAsStream(APPLICATION_PROPERTIES);
        properties.load(inputPath);
    }

    /**
     * This method gets the path Magick.
     * @return the Image Magick Path.
     */
    public String getMagickPath(){
        return  properties.getProperty(IMAGE_MAGICK_PATH);
    }

    /**
     * This method gets the path.
     * @return the FFmpeg path.
     */
    public String getFfmpegPath(){
        return  properties.getProperty(FFMPEG_PATH);
    }
}
