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
import org.apache.tomcat.util.http.fileupload.FileUtils;

import java.io.File;
import java.io.IOException;

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
     * It Creates a new CommandOutputFilePath object.
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
            throw new CommandValueException("Invalid input file path value\n", "File not found\n");
        } catch (NullPointerException nex) {
            throw  new CommandValueException("Pages to convert value is NULL ", this.getClass().getName());
        }
    }
}
