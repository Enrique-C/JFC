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
 * Contains Command Video ThumbNail.
 *
 * @version 0.1 23 Dic 2019.
 *
 * @author Oscar Lopez.
 */
public class CommandVideoThumbNail implements ICommandStrategy {

    // Contents a boolean to generate thumbnail.
    private boolean commandValue;

    // Contents value of two second to extract a thumbnail.
    private final String BEGIN_SECONDS = "00:00:02.000";

    //Contents number 4.
    private final short second = 4;

    //Contents number 10.
    private final short framePerSecond = 10;

    //Contents a thumbnail size.
    private final String THUMBNAIL_SIZE = "128:128";

    /**
     * Creates a new CommandVideoRotate object.
     * @param commandValue receives a boolean value.
     */
    public CommandVideoThumbNail(boolean commandValue) {
        this.commandValue = commandValue;
    }

    /**
     * Builds a command.
     * @return concatenated command.
     */
    @Override
    public String command() {
        if (this.commandValue) {
            return this.SPACE + VideoCommand.THUMBNAIL.getCommand() + this.SPACE + BEGIN_SECONDS + this.SPACE +
                  VideoCommand.VF.getCommand() + this.SPACE + VideoCommand.SCALE.getCommand() + THUMBNAIL_SIZE +
                  this.SPACE + VideoCommand.T.getCommand() + this.SPACE + second + this.SPACE +
                  VideoCommand.R.getCommand() + this.SPACE + framePerSecond;
        }
        return "";
    }
}
