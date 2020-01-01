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
 * This class verify a valid image format.
 *
 * @version 0.1 19 Dic 2019
 *
 * @author Juan Martinez
 */
public class CommandPagesToConvert implements ICommandStrategy {

    // Content command value.
    private String commandValue;

    // Content quantity of pdf file pages.
    private int quantityOfPages;

    /**
     * It Creates a new CommandPagesToConvert object.
     * @param commandValue contains a value.
     */
    public CommandPagesToConvert(String commandValue) {
        this.commandValue = commandValue;
    }

    /**
     * Build a command.
     * @return String of a command.
     * @throws CommandValueException
     */
    public String command() throws CommandValueException {
        final Pattern pattern = Pattern.compile("[0-9]\\d*||[0-9][-][0-9]\\d*$||[^$]");
        try {
                if (commandValue.isEmpty()){
                    return commandValue;
                }
                if (pattern.matcher(commandValue).matches()){

                    return ImageMagickCommand.OPEN_BRACKET.getCommand() + commandValue +
                            ImageMagickCommand.CLOSE_BRACKET.getCommand();
                }
                throw new CommandValueException("Invalid command value", this.getClass().getName());
        } catch (NullPointerException nex) {
            throw  new CommandValueException("Pages to convert value is NULL ", this.getClass().getName());
        }
    }
}
