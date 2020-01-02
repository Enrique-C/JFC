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
import com.jalasoft.jfc.model.pdf.ImageMagickCommand;

/**
 * This class generates a image with grayscale.
 *
 * @version 0.1 02 Jan 2020
 *
 * @author  Enrique Carrizales
 */
public class CommandImageGrayscale implements ICommandStrategy{

    // Content command value.
    private boolean commandValue;

    /**
     * Allows to instantiate this class.
     * @param commandValue receives a boolean value.
     */
    public CommandImageGrayscale(boolean commandValue) {
        this.commandValue = commandValue;
    }

    /**
     * Builds a command.
     * @return command concatenated.
     * @throws CommandValueException generates a error message.
     */
    @Override
    public String command() throws CommandValueException {
        final String GRAY_COLOR = "gray";

        if (commandValue) {
            return this.SPACE + ImageMagickCommand.COLORSPACE.getCommand() + this.SPACE + GRAY_COLOR;
        } else {
            return "";
        }
    }
}
