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

    // Properties type variable.
    private static Properties properties;

    // InputStream type variable.
    private InputStream inputPath;

    // Constant variable to get application.properties file.
    private static final String APPLICATION_PROPERTIES = "application.properties";

    // Constant variable of the key imageMagick path.
    private static final String IMAGE_MAGICK_PATH = "path.magick";

    // Constant variable of key FFmpeg path.
    private static final String FFMPEG_PATH = "path.ffmpeg";

    // Constant variable of key Exiftool path.
    private static final String EXIFTOOL_PATH = "path.exiftool";

    // Constant variable of key input files path.
    private static final String INPUT_FILES_PATH = "path.input.files";

    // Constant variable of key output files path.
    private static final String OUTPUT_FILES_PATH = "path.output.files";

    // Constant variable of key public files path.
    private static final String PUBLIC_FILES_PATH = "path.public.files";

    /**
     * Initializes properties and inputPath. Also load the inputPath in properties.
     * @throws IOException
     */
    public PathJfc() {
        properties = new Properties();
        inputPath = PathJfc.class.getClassLoader().getResourceAsStream(APPLICATION_PROPERTIES);
        try {
            properties.load(inputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets Image Magick's path.
     * @return Image Magick's Path.
     */
    public static String getMagickPath() {
        return properties.getProperty(IMAGE_MAGICK_PATH);
    }

    /**
     * Gets FFmpeg's path.
     * @return FFmpeg's path.
     */
    public static String getFfmpegPath() {
        return properties.getProperty(FFMPEG_PATH);
    }

    /**
     * Gets Exiftool's path.
     * @return Exiftool's path.
     */
    public static String getExiftoolPath() {
        return properties.getProperty(EXIFTOOL_PATH);
    }

    /**
     * Gets Input Files's path.
     * @return Input files Path's path.
     */
    public static String getInputFilePath() {
        return properties.getProperty(INPUT_FILES_PATH);
    }

    /**
     * Gets Output Files's path.
     * @return Output files Path's path.
     */
    public static String getOutputFilePath() {
        return properties.getProperty(OUTPUT_FILES_PATH);
    }

    /**
     * Gets Public files's path.
     * @return Public files Path's path.
     */
    public static String getPublicFilePath() {
        return properties.getProperty(PUBLIC_FILES_PATH);
    }
}
