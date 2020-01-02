/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.strategy;

import com.jalasoft.jfc.model.exception.CommandValueException;
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
     * @throws IOException when is a invalid file.
     */
    public CommandImageMagickPath() throws CommandValueException {
        try {
            pathJfc = new PathJfc();
            imageMagickPath = pathJfc.getMagickPath();
        } catch (IOException ie) {
            throw new CommandValueException("invalid path", this.getClass().getName());
        }
    }

    /**
     * Generates a command.
     * @return exe of ImageMagick path.
     */
    @Override
    public String command() throws CommandValueException {
        try {
            File file = new File(imageMagickPath);

            if (file.exists()) {
                return this.SPACE + imageMagickPath;
            }
            throw new CommandValueException("Image magick doesn't exist\n", "Image magick not found\n");
        } catch (CommandValueException cve) {
            throw new CommandValueException(cve.getMessage(), this.getClass().getName());
        }
    }
}
