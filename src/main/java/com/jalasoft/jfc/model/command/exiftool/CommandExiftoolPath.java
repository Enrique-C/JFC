/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.command.exiftool;

import com.jalasoft.jfc.model.command.ICommandStrategy;
import com.jalasoft.jfc.model.exception.CommandValueException;
import com.jalasoft.jfc.model.utility.PathJfc;

import java.io.File;

/**
 * Validates Exiftool path.
 *
 * @version 0.1 03 Jan 2020.
 *
 * @author Enrique Carrizales.
 */
public class CommandExiftoolPath implements ICommandStrategy {

    // Content Exiftool Path value.
    private  String exiftoolPath;

    /**
     * Assigns the Exiftool Path.
     */
    public CommandExiftoolPath() { this.exiftoolPath = PathJfc.getExiftoolPath(); }

    /**
     * Generates a command.
     * @return exe of Exiftool path.
     * @throws CommandValueException when is a invalid command.
     */
    @Override
    public String command() throws CommandValueException {
        try {
            File file = new File(exiftoolPath);

            if (file.exists()) {
                return exiftoolPath;
            }
            throw new CommandValueException("Exiftool doesn't exist\n", "Exiftool not found\n");
        } catch (CommandValueException cve) {
            throw new CommandValueException(cve.getMessage(), this.getClass().getName());
        }
    }
}
