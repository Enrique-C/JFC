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
 * This Class contains Command Video ThumbNail.
 *
 * @version 0.1 23 Dic 2019.
 *
 * @author Oscar Lopez.
 */
public class CommandVideoThumbNail implements ICommandStrategy{

    // Content value to rotate.
    private boolean commandValue;

    // Content value of one second to extract a thumbnail.
    private final String secondBegin = "00:00:02.000";

    //Content number 0
    private final short getFrames = 100;

    /**
     * Creates a new CommandVideoRotate object.
     *
     * @param commandValue, receive a value.
     */
    public CommandVideoThumbNail(boolean commandValue) {
        this.commandValue = commandValue;
    }

    /**
     * This method builds a command.
     *
     * @return command concatenated.
     */
    @Override
    public String command() {
        if (commandValue) {
            return this.SPACE + VideoCommand.THUMBNAIL.getCommand() + this.SPACE + secondBegin + this.SPACE +
                  VideoCommand.V_FRAMES.getCommand() + this.SPACE + getFrames;
        }
        return "";
    }
}
