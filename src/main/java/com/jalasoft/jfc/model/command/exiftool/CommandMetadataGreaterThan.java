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

/**
 * Concatenates greater than value.
 *
 * @version 0.1 08 Jan 2020.
 *
 * @author Juan Martinez.
 */
public class CommandMetadataGreaterThan implements ICommandStrategy {

    // Command variable.
    private String verboseCommand;

    /**
     * Creates a new CommandMetadataGreaterThan object.
     */
    public CommandMetadataGreaterThan() {
        verboseCommand = ExiftoolCommand.GREATER_THAN.getCommand();
    }

    /**
     * Builds command.
     * @return string of command.
     * @throws CommandValueException then something was wrong.
     */
    @Override
    public String command() throws CommandValueException {
        try {
            if (!verboseCommand.isEmpty()) {
                return SPACE + verboseCommand;
            }
            throw new CommandValueException("Verbose command is invalid", this.getClass().getName());
        } catch (CommandValueException vce) {
            throw new CommandValueException(vce.getMessage(), vce.getCause().toString());
        }
    }
}
