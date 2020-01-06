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
 * Validates a output file path.
 *
 * @version 0.1 19 Dec 2019.
 *
 * @author Enrique Carrizales.
 */
public class CommandOutputFilePath implements ICommandStrategy {

    // Content command value.
    private String commandValue;

    // Content input file name without extension.
    private String inputFileName;

    /**
     * Creates a new CommandOutputFilePath object.
     * @param commandValue contains a value.
     * @param inputFileName contains file name witout extension.
     */
    public CommandOutputFilePath(String commandValue, String inputFileName) {
        this.commandValue = commandValue;
        this.inputFileName = inputFileName;
    }

    /**
     * Generates a command.
     * @return output path.
     * @throws CommandValueException when is a invalid command.
     */
    @Override
    public String command() throws CommandValueException {
        File file = new File(commandValue);
        try {
            if (file.exists()) {
                file = new File(commandValue + inputFileName);
                file.mkdir();
                return this.SPACE + commandValue + inputFileName + "/";
            }
            throw new CommandValueException(ErrorMessageJfc.INVALID_INPUT_PATH.getErrorMessageJfc(), ErrorMessageJfc
                    .FILE_NO_FOUND.getErrorMessageJfc());
        } catch (NullPointerException nex) {
            throw new CommandValueException(ErrorMessageJfc.PAGES_NULL.getErrorMessageJfc(), this.getClass()
                    .getName());
        }
    }
}
