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
import com.jalasoft.jfc.model.exception.ErrorMessageJfc;
import com.jalasoft.jfc.model.pdf.ImageMagickCommand;

import java.util.regex.Pattern;

/**
 * This class verify valid pages of pdf file.
 *
 * @version 0.1 19 Dic 2019.
 *
 * @author Juan Martinez.
 */
public class CommandPagesToConvert implements ICommandStrategy {

    // Contents command value.
    private String commandValue;

    // Contents quantity of pdf file pages.
    private int quantityOfPages;

    /**
     * Creates a new CommandPagesToConvert object.
     * @param commandValue contains value of command.
     * @param quantityOfPages contains value of pages.
     */
    public CommandPagesToConvert(String commandValue, int quantityOfPages) {
        this.commandValue = commandValue;
        this.quantityOfPages = quantityOfPages;
    }

    /**
     * Build a command.
     * @return String of pages to convert.
     * @throws CommandValueException when there is an invalid command.
     */
    public String command() throws CommandValueException {
        final Pattern pattern = Pattern.compile("^(?:\\d+(?:-\\d+)?(?:,|$))+$");
        try {
            if (commandValue.isEmpty()) {
                return commandValue;
            }
            int firstValue = 0;
            int secondValue = 1;
            int first;
            int second;
            if (!pattern.matcher(commandValue).matches()) {
                throw new CommandValueException(ErrorMessageJfc.COMMAND_INVALID.getErrorMessageJfc(), this.getClass()
                        .getName());
            }
            String[] splitNumber = commandValue.split("[-,]");
            if (splitNumber.length == 1) {
                first = Integer.parseInt(commandValue);
                if (first <= quantityOfPages) {
                    return ImageMagickCommand.OPEN_BRACKET.getCommand() + commandValue +
                           ImageMagickCommand.CLOSE_BRACKET.getCommand();
                }
                throw new CommandValueException(ErrorMessageJfc.PAGES_NUMBER_NOT_EXIST.getErrorMessageJfc(), this
                        .getClass().getName());
            } else {
                first = Integer.parseInt(splitNumber[firstValue]);
                second = Integer.parseInt(splitNumber[secondValue]);
                if (first <= quantityOfPages && second <= quantityOfPages) {
                    return ImageMagickCommand.OPEN_BRACKET.getCommand() + commandValue +
                           ImageMagickCommand.CLOSE_BRACKET.getCommand();
                }
                throw new CommandValueException(ErrorMessageJfc.PAGES_NUMBER_NOT_EXIST.getErrorMessageJfc(), this.getClass().getName());
            }
        } catch (NullPointerException nex) {
            throw  new CommandValueException(ErrorMessageJfc.PAGES_NULL.getErrorMessageJfc(), this.getClass()
                    .getName());
        }
    }
}
