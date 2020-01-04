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
import com.jalasoft.jfc.model.image.ImageFormat;

/**
 * This class verify a valid image format.
 *
 * @version 0.1 19 Dic 2019
 *
 * @author Juan Martinez
 */
public class CommandImageFormat implements ICommandStrategy {

    // Content command value.
    private String commandValue;

    /**
     * Creates a new CommandImageFormat object.
     * @param commandValue contains a value.
     */
    public CommandImageFormat(String commandValue) {
        this.commandValue = commandValue;
    }

    /**
     * Builds a command.
     * @return String of a command.
     * @throws CommandValueException generates a error message.
     */
    public String command() throws CommandValueException {
        for (ImageFormat image : ImageFormat.values()) {
            if (image.getImageFormat().equals(commandValue)) {
                return commandValue;
            }
        }
        throw new CommandValueException("Format is not valid", this.getClass().getName());
    }
}
