/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.strategy;

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

    /**
     * It Creates a new CommandOutputFilePath object.
     * @param commandValue contains a value.
     */
    public CommandOutputFilePath(String commandValue) {
        this.commandValue = commandValue;
    }

    /**
     * Generates a command.
     * @return output path.
     */
    @Override
    public String command() {
        File file = new File(commandValue);
        if (file.exists()) {
            return this.SPACE + commandValue;
        }
        return null;
    }
}
