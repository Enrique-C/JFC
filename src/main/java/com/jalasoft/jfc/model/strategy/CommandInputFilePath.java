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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidParameterException;

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
     * It Creates a new CommandInputFilePath object.
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
                return this.SPACE + commandValue;
            }
            throw new CommandValueException("Invalid input file path value\n", "File not found\n");
        } catch (CommandValueException cve){
            throw new CommandValueException(cve.getMessage(), this.getClass().getName());
        } catch (NullPointerException nex) {
            throw  new NullPointerException("Pages to convert value is NULL " + this.getClass().getName());
        }
    }
}
