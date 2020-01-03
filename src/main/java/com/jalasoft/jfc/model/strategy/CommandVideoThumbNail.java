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

    // Content value of two second to extract a thumbnail.
    private final String secondBegin = "00:00:02.000";

    //Content number 4
    private final short second = 4;

    //Content number 10
    private final short framePerSecond = 10;

    //Content a thumbnail size
    private final String thumbnailSize = "128:128";
    /**
     * Creates a new CommandVideoRotate object.
     *
     * @param commandValue, receive a boolean value.
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
                  VideoCommand.VF.getCommand() + this.SPACE + VideoCommand.SCALE.getCommand() + thumbnailSize +
                  this.SPACE + VideoCommand.T.getCommand() + this.SPACE + second + this.SPACE +
                  VideoCommand.R.getCommand() + this.SPACE + framePerSecond;
        }
        return "";
    }
}
