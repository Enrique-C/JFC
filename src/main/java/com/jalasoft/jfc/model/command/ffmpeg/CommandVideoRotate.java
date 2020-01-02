/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.command.ffmpeg;

import com.jalasoft.jfc.model.command.ICommandStrategy;
import com.jalasoft.jfc.model.video.VideoCommand;

/**
 * This Class contains Command Video Rotate.
 *
 * @version 0.1 23 Dic 2019.
 *
 * @author Oscar Lopez.
 */
public class CommandVideoRotate implements ICommandStrategy {

    // Content value to rotate.
    private short commandValue;

    // Content value 90.
    private final short degrees90 = 90;

    // Content value 180.
    private final short degrees180 = 180;

    // Content value 270.
    private final short degrees270 = 270;
    /**
     * Creates a new CommandVideoRotate object.
     *
     * @param commandValue, receive a value.
     */
    public CommandVideoRotate(short commandValue) {
        this.commandValue = commandValue;
    }

    /**
     * This method builds a command.
     *
     * @return command concatenated.
     */
    @Override
    public String command() {
        if (commandValue == degrees90 || commandValue == degrees180 || commandValue == degrees270) {
            return SPACE + VideoCommand.VF.getCommand() + SPACE + VideoCommand.ROTATE.getCommand() +
                   SPACE + commandValue;
        }
        return null;
    }
}
