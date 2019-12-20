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

import java.util.regex.Pattern;

public class CommandThumbnail implements ICommandStrategy {

    // Content command value.
    private String commandValue;

    /**
     * Allows to instantiate this class.
     * @param commandValue
     */
    public CommandThumbnail(String commandValue){
        this.commandValue = commandValue;
    }

    /**
     * This method builds a command.
     * @return command concatenated.
     */
    public String command() {
        final Pattern pattern = Pattern.compile("[x][0-9]\\d*");
        if (!pattern.matcher(commandValue).matches()){
            return null;
        }
        return SPACE + ImageMagickCommand.THUMBNAIL.getCommand() + SPACE + commandValue;
    }
}
