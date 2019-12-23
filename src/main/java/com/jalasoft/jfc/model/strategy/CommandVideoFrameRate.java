/*
 * Copyright (c) 2019 Jalasoft.
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.strategy;

/**
 * This Class changes the Frame Rate of a video.
 *
 * @version 0.1 20 Dic 2019.
 *
 * @author Oscar Lopez.
 */
public class CommandVideoFrameRate implements ICommandStrategy {

    // Content command value.
    private String commandValue;

    /**
     * Creates a new CommandVideoFrameRate object.
     *
     * @param commandValue, receive a value.
     */
    public CommandVideoFrameRate(String commandValue) {
        this.commandValue = commandValue;
    }

    /**
     * This method builds a command.
     *
     * @return command concatenated.
     */
    @Override
    public String command() {
        return this.SPACE + commandValue + this.SPACE;
    }
}
