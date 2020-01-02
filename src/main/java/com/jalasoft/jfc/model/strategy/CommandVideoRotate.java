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
 * This Class contains Command Video Rotate.
 *
 * @version 0.1 23 Dic 2019.
 *
 * @author Oscar Lopez.
 */
public class CommandVideoRotate implements ICommandStrategy {

    // Content value to rotate.
    private short commandValue;

    // Content value Comma.
    private final String COMMA = ", ";

    // Content value quotation mark.
    private final char quotationMark = '"';
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
        switch (commandValue) {
            case 90:
                return SPACE + VideoCommand.VF.getCommand() + SPACE + this.quotationMark +
                       VideoCommand.ROTATE.getCommand() + this.quotationMark;
            case 180:
                return SPACE + VideoCommand.VF.getCommand() + SPACE + this.quotationMark +
                        VideoCommand.ROTATE.getCommand() + this.COMMA + VideoCommand.ROTATE.getCommand() +
                        this.quotationMark;
            case 270:
                return SPACE + VideoCommand.VF.getCommand() + SPACE + this.quotationMark +
                        VideoCommand.ROTATE.getCommand() + this.COMMA + VideoCommand.ROTATE.getCommand() + this.COMMA +
                        VideoCommand.ROTATE.getCommand() + this.quotationMark;
            default:
                return null;
        }
    }
}
