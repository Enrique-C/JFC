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

/**
 * This class validates thumbnail value.
 *
 * @version 0.1 20 Dic 2019
 *
 * @author Juan Martinez
 */
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
        String result = null;
        if (commandValue != null) {
            if (pattern.matcher(commandValue).matches()){
                result = SPACE + ImageMagickCommand.THUMBNAIL.getCommand() + SPACE + commandValue;
            }
        }
        return result;
    }
}
