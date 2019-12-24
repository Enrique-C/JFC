/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.video;

/**
 * This enum is used for specifying FFmpeg commands.
 *
 * @version 0.1 13 Dic 2019
 *
 * @author Juan Martinez
 */
public enum VideoCommand {
    INFILE("-i"),
    ASPECT_RATIO("-aspect"),
    FRAME_RATE("-r"),
    VIDEO_CODEC("-vcodec"),
    AUDIO_CODEC("-acodec"),
    FRAME_SIZE("-s"),
    VIDEO_BITRATE("-b:v"),
    AUDIO_BITRATE("-b:a"),
    VF("-vf"),
    SCALE("scale="),
    ROTATE90("vflip"),
    ROTATE180("hflip"),
    ROTATE270("vflip,hflip"),
    CHANNELS("-ac"),
    VOLUME("-vol"),
    EMPTY(""),
    COLON(":"),
    ASTERISK("*"),
    THUMBNAIL("-an -s 128x128 -ss"),
    V_FRAMES("-vframes");

    // Command variable.
    private String command;

    /**
     * Sets command value.
     * @param command String value.
     */
    VideoCommand(String command) {
        this.command = command;
    }

    /**
     * Gets command value.
     * @return String value.
     */
    public String getCommand(){
        return command;
    }
}
