/*
 * Copyright (c) 2019 Jalasoft.
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.command;

import java.io.File;

/**
 * This Class has Command FFmpeg path.
 *
 * @version 0.1 23 Dic 2019.
 *
 * @author Oscar Lopez.
 */
public class CommandFFMpegPath implements ICommandStrategy {

    // Content command value.
    private final String FFMPEG_PATH = "thirdparty/ffmpeg/bin/ffmpeg.exe";

    /**
     * Generates a command.
     * @return exe of FFmpeg path.
     */
    @Override
    public String command() {
        File file = new File(FFMPEG_PATH);
        if (file.exists()) {
            return FFMPEG_PATH + this.SPACE;
        }
        return null;
    }
}
