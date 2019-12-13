/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 *  ("Confidential Information"). You shall not disclose such Confidential
 *  Information and shall use it only in accordance with the terms of the
 *  license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model;

/**
 *
 *  VideoCommand enum is used for specify FFmpeg commands.
 *
 * @version 1.0 13 Dic 2019
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
    SCALE("-qscale"),
    ROTATE("-filter:v"),
    CHANNELS("-ac"),
    VOLUME("-vol"),
    EMPTY(""),
    ASTERISK("*");

    private String command;     //command variable

    /**
     * VideoCommand for setting command value.
     * @param command String value
     */
    VideoCommand(String command) {
        this.command = command;
    }

    /**
     * getCommand method for getting command value.
     * @return String value.
     */
    public String getCommand(){
        return command;
    }
}
