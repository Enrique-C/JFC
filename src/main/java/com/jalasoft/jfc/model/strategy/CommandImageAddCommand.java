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

/**
 * This class generates a command to add a new command.
 *
 * @version 0.1 29 Dic 2019
 *
 * @author Enrique Carrizales
 */
public class CommandImageAddCommand implements ICommandStrategy{

    final String PIPELINE = "|";

    /**
     * Generates a command.
     * @return a String command.
     * @throws CommandValueException generates an exception.
     */
    @Override
    public String command() throws CommandValueException {
        return this.SPACE + PIPELINE;
    }
}
