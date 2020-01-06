/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.exception;

/**
 * Contains messages of throw new exceptions.
 *
 * @version 0.1 06 Jan 2020
 *
 * @author Alan Escalera
 */
public enum ErrorMessageJfc {
    INVALID_INPUT_PATH("Invalid input file path value\n"),
    FILE_NO_FOUND("File not found\n"),
    PAGES_NULL("Pages to convert value is NULL "),
    INVALID_SCALE("Invalid input file path value\n"),
    EXIFTOOL_NOT_EXIST("Exiftool doesn't exist\n"),
    EXIFTOOL_NOT_FOUND("Exiftool not found\n: "),
    FFMPEG_NOT_EXIST("FFMepg doesn't exist\n"),
    ASPECTRATIO_NOT_CHANGE("Can not change the Aspect ratio"),
    VIDEOBITRATE_NOT_CHANGE("Can not change the Video Bit rate"),
    VIDEOCODEC_NOT_CHANGE("Can not change the Video codec"),
    VIDEO_NOT_CONVERTED("Can not convert a video"),
    FORMTAT_INVALID("Format is invalid"),
    FRAMERATE_NOT_CHANGE("Can not change the Frame rate"),
    SCALE_NOT_CHANGE("Can not change the Scale"),
    IMAGEMAGICK_NOT_EXIST("Image magick doesn't exist\n"),
    IMAGEMAGICK_NOT_FOUND("Image magick not found\n"),
    RESIZE_INVALID("Invalid resize values\n"),
    WIDTH_HEIGHT_INVALID("width or height are invalid\n"),
    COMMAND_NULL("Command value is NULL "),
    COMMAND_INVALID("command value is invalid\n");

    // Command variable.
    private String message;

    /**
     * Sets the message value.
     * @param message String value.
     */
    ErrorMessageJfc(String message) {
        this.message = message;
    }

    /**
     * Gets the message value.
     * @return String value.
     */
    public String getErrorMessageJfc(){
        return message;
    }
}
