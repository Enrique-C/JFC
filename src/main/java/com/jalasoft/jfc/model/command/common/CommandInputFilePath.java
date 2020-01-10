/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.command.common;

import com.jalasoft.jfc.model.command.ICommandStrategy;
import com.jalasoft.jfc.model.exception.CommandValueException;
import com.jalasoft.jfc.model.exception.ErrorMessageJfc;

import java.io.File;

/**
 * Validates an input file path.
 *
 * @version 0.1 19 Dec 2019.
 *
 * @author Enrique Carrizales.
 */
public class CommandInputFilePath implements ICommandStrategy {

    // Content command value.
    private String commandValue;

    /**
     * Creates a new CommandInputFilePath object.
     * @param commandValue contains a value.
     */
    public CommandInputFilePath(String commandValue) {
        this.commandValue = commandValue;
    }

    /**
     * Generates a commands.
     * @return input path.
     * @throws CommandValueException
     */
    @Override
    public String command() throws CommandValueException {
        File file = new File(commandValue);
        try {
            if (file.exists()) {
                file.deleteOnExit();
                return this.SPACE + commandValue;
            }
            throw new CommandValueException(ErrorMessageJfc.INVALID_INPUT_PATH.getErrorMessageJfc(), ErrorMessageJfc
                    .FILE_NO_FOUND.getErrorMessageJfc());
        } catch (NullPointerException nex) {
            throw  new CommandValueException(ErrorMessageJfc.PAGES_NULL.getErrorMessageJfc(), this.getClass()
                    .getName());
        }
    }
}
