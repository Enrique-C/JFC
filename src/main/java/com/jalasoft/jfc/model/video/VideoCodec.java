/*
 * Copyright (c) 2019 Jalasoft.
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.video;

/**
 * This enum is used for specifying FFmpeg Video codecs.
 *
 * @version 0.1 24 Dic 2019
 *
 * @author Oscar Lopez
 */
public enum VideoCodec {

    X264("x264"),
    OPENH264("OpenH264"),
    XVID("Xvid"),
    lIBVPX("libvpx"),
    FFMPEG_CODECS("FFmpeg codecs"),
    LAGARITH("Lagarith"),
    DIRAC("Dirac"),
    HUFFYUV("Huffyuv"),
    DAALA("Daala"),
    THOR("Thor"),
    TURING("Turing"),
    AV1("AV1");

    // Command variable.
    private String command;

    /**
     * Sets command value.
     * @param command String value.
     */
    VideoCodec(String command) {
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
