/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.command.imagick;

import com.jalasoft.jfc.model.command.ICommandStrategy;
import com.jalasoft.jfc.model.exception.CommandValueException;
import com.jalasoft.jfc.model.utility.PathJfc;

import java.io.File;

/**
 * Validates ImageMagick path.
 *
 * @version 0.1 19 Dec 2019.
 *
 * @author Enrique Carrizales.
 */
public class CommandImageMagickPath implements ICommandStrategy {

    // Content Image Magick Path value.
    private  String imageMagickPath;

    /**
     * Assigns the Image Magick Path.
     */
    public CommandImageMagickPath() {
        imageMagickPath = PathJfc.getMagickPath();
    }

    /**
     * Generates a command.
     * @return exe of ImageMagick path.
     * @throws CommandValueException when is a invalid command.
     */
    @Override
    public String command() throws CommandValueException {
        try {
            File file = new File(imageMagickPath);

            if (file.exists()) {
                return imageMagickPath;
            }
            throw new CommandValueException("Image magick doesn't exist\n", "Image magick not found\n");
        } catch (CommandValueException cve) {
            throw new CommandValueException(cve.getMessage(), this.getClass().getName());
        }
    }
}
