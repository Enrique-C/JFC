/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.command;

import com.jalasoft.jfc.model.exception.CommandValueException;

import java.util.List;

/**
 * Contents a list of commands.
 *
 * @version 0.1 20 Dic 2019.
 *
 * @author Juan Martinez.
 */
public class ContextStrategy {

    // Contents a list of commands.
    private List<ICommandStrategy> commands;

    /**
     * Allows to instantiate Context.
     * @param commands receives commands.
     */
    public ContextStrategy(List<ICommandStrategy> commands){
        this.commands = commands;
    }

    /**
     * Builds a command.
     * @return commandString concatenated.
     * @throws CommandValueException when there is an invalid command.
     */
    public String buildCommand() throws CommandValueException {
        StringBuilder commandString = new StringBuilder();
            for (ICommandStrategy itemCmd : commands) {
                String itemCmdValue = itemCmd.command();
                commandString.append(itemCmdValue);
            }
        return commandString.toString();
    }
}
