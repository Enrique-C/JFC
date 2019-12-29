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

import java.io.IOException;
import java.util.List;

/**
 * This class content a list of commands.
 *
 * @version 0.1 20 Dic 2019
 *
 * @author Juan Martinez
 */
public class ContextStrategy {

    // Content a list of commands.
    private List<ICommandStrategy> commands;

    /**
     * Allows to instantiate Context.
     * @param commands
     */
    public ContextStrategy(List<ICommandStrategy> commands){
        this.commands = commands;
    }

    /**
     * This method builds a command.
     * @return commandString concatenated.
     */
    public String buildCommand() throws CommandValueException, NullPointerException, IOException {
        StringBuilder commandString = new StringBuilder();
        try {
            for (ICommandStrategy itemCmd : commands) {
                String itemCmdValue = itemCmd.command();
                    commandString.append(itemCmdValue);
            }
            return commandString.toString();
        } catch (CommandValueException cve){
            throw new CommandValueException(cve.getMessage(), this.getClass().getName());
        } catch (NullPointerException nex) {
            throw  new NullPointerException(nex.getMessage());
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }
}
