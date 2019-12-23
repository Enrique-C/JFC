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

    // This is a constant variable of the key path magick.
    private final String PATH_MAGICK = "path.magick";

    // This is a constant variable of key path FFmpeg
    private final String PATH_FFMPEG = "path.ffmpeg";

    public PathJfc() throws IOException {
        properties = new Properties();
        inputPath = PathJfc.class.getClassLoader().getResourceAsStream(APPLICATION_PROPERTIES);
        properties.load(inputPath);
    }

    /**
     * This method gets the path Magick.
     * @return
     */
    public String getMagickPath(){
        return  properties.getProperty(PATH_MAGICK);
    }

    /**
     * This method gets the path .
     * @return
     */
    public String getFfmpegPath(){
        return  properties.getProperty(PATH_FFMPEG);
    }
}
