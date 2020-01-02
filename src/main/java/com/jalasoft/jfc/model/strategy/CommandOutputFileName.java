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

import java.security.InvalidParameterException;

/**
 * This class verify if outputFileName value.
 *
 * @version 0.1 19 Dic 2019
 *
 * @author Juan Martinez
 */
public class CommandOutputFileName implements ICommandStrategy {

    // Content command value.
    private String commandValue;

    // Content input file name without extension.
    private String inputFileName;

    /**
     * It Creates a new CommandOutputFileName object.
     * @param commandValue contains a value.
     */
    public CommandOutputFileName(String commandValue, String inputFileName) {
        this.commandValue = commandValue;
        this.inputFileName = inputFileName;
    }

    /**
     * Build command.
     * @return string of command.
     * @throws CommandValueException
     */
    public String command() throws CommandValueException {
        String regexRule = "[^a-zA-Z0-9.]";
        String replaceValue = "";

            if (commandValue.equals("_t") || commandValue.isEmpty()) {
                commandValue = inputFileName;
            }
            commandValue = commandValue.replaceAll(regexRule, replaceValue);
        return commandValue;
    }
}
