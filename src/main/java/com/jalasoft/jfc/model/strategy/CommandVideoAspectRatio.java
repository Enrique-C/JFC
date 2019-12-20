/*
 * Copyright (c) 2019 Jalasoft.
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.strategy;

import com.jalasoft.jfc.model.video.VideoCommand;

/**
 * This class changes aspect ratio of a video.
 *
 * @author Oscar Lopez.
 * @version 0.1 20 Dic 2019.
 */
public class CommandVideoAspectRatio implements ICommandStrategy {

    // Content command value.
    private String commandValue;

    /**
     * This is the constructor of class.
     *
     * @param commandValue, receive a value.
     * @return command concatenated.
     */
    public CommandVideoAspectRatio(String commandValue) {
        this.commandValue = commandValue;
    }

    /**
     * This method builds a command.
     *
     * @return command concatenated.
     */
    @Override
    public String command() {
        return this.SPACE + VideoCommand.INFILE + this.SPACE + commandValue + this.SPACE;
    }
}
