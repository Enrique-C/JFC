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
    private static Properties properties;

    // This is a InputStream.
    private InputStream inputPath;

    // This is a constant variable to get application.properties file.
    private static final String APPLICATION_PROPERTIES = "application.properties";

    // This is a constant variable of the key imageMagick path.
    private static final String IMAGE_MAGICK_PATH = "path.magick";

    // This is a constant variable of key FFmpeg path.
    private static final String FFMPEG_PATH = "path.ffmpeg";

    // This is a constant variable of key input files path.
    private static final String INPUT_FILES_PATH = "path.input.files";

    // This is a constant variable of key output files path.
    private static final String OUTPUT_FILES_PATH = "path.output.files";

    // This is a constant variable of key public files path.
    private static final String PUBLIC_FILES_PATH = "path.public.files";

    /**
     * Initializes properties and inputPath. Also load the inputPath in properties.
     * @throws IOException
     */
    public PathJfc() throws IOException {
        properties = new Properties();
        inputPath = PathJfc.class.getClassLoader().getResourceAsStream(APPLICATION_PROPERTIES);
        properties.load(inputPath);
    }

    /**
     * This gets the Image Magick's path.
     * @return the Image Magick Path.
     */
    public static String getMagickPath() {
        return properties.getProperty(IMAGE_MAGICK_PATH);
    }

    /**
     * This gets the FFmpeg's path.
     * @return the FFmpeg path.
     */
    public static String getFfmpegPath() {
        return properties.getProperty(FFMPEG_PATH);
    }

    /**
     * This gets the Input Files path.
     * @return the Input files Path path.
     */
    public static String getInputFilePath() {
        return properties.getProperty(INPUT_FILES_PATH);
    }

    /**
     * This gets the Output Files path.
     * @return the Output files Path path.
     */
    public static String getOutputFilePath() {
        return properties.getProperty(OUTPUT_FILES_PATH);
    }

    /**
     * This gets the Public files path.
     * @return the Public files Path path.
     */
    public static String getPublicFilePath() {
        return properties.getProperty(PUBLIC_FILES_PATH);
    }
}
