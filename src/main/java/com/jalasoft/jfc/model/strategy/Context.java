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

import java.util.ArrayList;
import java.util.List;

/**
 * This class content a list of commands.
 *
 * @version 0.1 20 Dic 2019
 *
 * @author Juan Martinez
 */
public class Context {

    // Content a list of commands.
    private List<ICommandStrategy> commands;

    /**
     * Allows to instantiate Context.
     * @param commands
     */
    public Context(ArrayList<ICommandStrategy> commands){
        this.commands = commands;
    }

    /**
     * This method builds a command.
     * @return commandString concatenated.
     */
    public String buildCommand() throws CommandValueException {
        StringBuilder commandString = new StringBuilder();

        for (ICommandStrategy itemCmd : commands) {
            String itemCmdValue = itemCmd.command();
            if (itemCmdValue != null) {
                commandString.append(itemCmdValue);
            }
        }
        return commandString.toString();
    }
}
