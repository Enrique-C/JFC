/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.strategy;

import com.jalasoft.jfc.model.pdf.ImageMagickCommand;

/**
 * Rotates an image.
 *
 * @version 0.1 19 Dic 2019
 *
 * @author Enrique Carrizales
 */
public class CommandImageRotate implements ICommandStrategy {

    // Content command value.
    private String commandValue;

    /**
     * It Creates a new CommandImageRotate object.
     * @param commandValue contains a value.
     */
    public CommandImageRotate(String commandValue) {
        this.commandValue = commandValue;
    }

    /**
     * It Builds a command.
     * @return String of a command.
     */
    @Override
    public String command() {
        int intValue = Integer.parseInt(commandValue);
        if (intValue > 0) {
            return this.SPACE + ImageMagickCommand.ROTATE.getCommand() + this.SPACE + commandValue;
        }
        return null;
    }
}
