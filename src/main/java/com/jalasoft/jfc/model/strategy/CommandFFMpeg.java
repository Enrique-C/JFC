/*
 * Copyright (c) 2019 Jalasoft.
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.command;

import com.jalasoft.jfc.model.exception.CommandValueException;

/**
 * This Class has fFmpeg Command.
 *
 * @version 0.1 23 Dic 2019.
 *
 * @author Oscar Lopez.
 */
public class CommandFFMpeg implements ICommandStrategy {

    //Content ffmpeg value;
    private final String commandValue = "ffmpeg ";

    /**
     * This method builds a command.
     * @return command concatenated.
     */
    @Override
    public String command() throws CommandValueException {
        return commandValue;
    }
}
