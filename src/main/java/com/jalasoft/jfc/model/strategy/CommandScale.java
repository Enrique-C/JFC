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
import java.util.regex.Pattern;

/**
 * This class verify the scale value.
 *
 * @version 0.1 19 Dic 2019
 *
 * @author Juan Martinez
 */
public class CommandScale implements ICommandStrategy {

    // Content command value.
    private String commandValue;

    /**
     * It Creates a new CommandScale object.
     * @param commandValue contains a value.
     */
    public CommandScale(String commandValue) {
        this.commandValue = commandValue;
    }

    /**
     * This method builds a command.
     * @return command concatenated.
     * @throws CommandValueException when is a invalid command.
     */
    public String command() throws CommandValueException {
        final Pattern pattern = Pattern.compile("[0-9]\\d*[%]");
        try {
            if (commandValue.equals("%")) {
                return "";
            }
            if (pattern.matcher(commandValue).matches()) {
                return SPACE + ImageMagickCommand.SCALE.getCommand() + SPACE + commandValue;
            }
            throw new CommandValueException("Invalid scale value\n", "command value is invalid\n");
        } catch (NullPointerException nex) {
            throw  new CommandValueException("Command value is NULL ", this.getClass().getName());
        }
    }
}
