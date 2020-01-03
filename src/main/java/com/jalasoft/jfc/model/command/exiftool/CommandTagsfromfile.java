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
 * This class generates a tagsfromfile command.
 *
 * @version 0.1 03 Jan 2020.
 *
 * @author Enrique Carrizales.
 */
public class CommandTagsfromfile implements ICommandStrategy {

    /**
     * Generates a command.
     * @return a command String.
     * @throws CommandValueException generates an error message.
     */
    @Override
    public String command() throws CommandValueException {
        return this.SPACE + ExiftoolCommand.TAGSFROMFILE.getCommand();
    }
}
