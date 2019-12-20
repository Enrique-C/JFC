/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.strategy;

/**
 *
 * Builds a command to convert an image.
 *
 * @version 0.1 18 Dic 2019.
 *
 * @author Enrique Carrizales.
 */
public class CommandImageConverter implements ICommandStrategy{

    // Content command value
    private String commandValue;

    final String command = "convert";

    /**
     * It builds the class.
     * @param commandValue contains a value.
     */
    public CommandImageConverter(String commandValue) {
        this.commandValue = commandValue;
    }

    /**
     * Builds a convert command.
     * @return command convert.
     */
    @Override
    public String command() {
        return this.SPACE + command;
    }
}
