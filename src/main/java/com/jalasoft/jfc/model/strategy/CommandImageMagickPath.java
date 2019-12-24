/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.strategy;

import com.jalasoft.jfc.model.utility.PathJfc;

import java.io.File;
import java.io.IOException;

/**
 * Validates ImageMagick path.
 *
 * @version 0.1 19 Dec 2019.
 *
 * @author Enrique Carrizales.
 */
public class CommandImageMagickPath implements ICommandStrategy {

    // Content command value.
    private  String imageMagickPath;

    // Variable type PathJfc.
    PathJfc pathJfc;

    /**
     * This initialize PathJfc and gets the Image Magick Path.
     * @throws IOException
     */
    public CommandImageMagickPath() throws IOException {
        pathJfc = new PathJfc();
        imageMagickPath = pathJfc.getMagickPath();
    }

    /**
     * Generates a command.
     * @return exe of ImageMagick path.
     */
    @Override
    public String command() {
        File file = new File(imageMagickPath);
        if (file.exists()) {
            return this.SPACE + imageMagickPath;
        }
        return null;
    }
}
