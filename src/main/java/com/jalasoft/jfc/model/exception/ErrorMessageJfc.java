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
    INVALID_INPUT_PATH("Invalid file path input"),
    FILE_NO_FOUND("File not found"),
    PAGES_NULL("Value of pages to convert is NULL"),
    PAGES_NUMBER_NOT_EXIST("Number of page doesn't exist"),
    INVALID_SCALE("Invalid input file path value"),
    EXIFTOOL_NOT_EXIST("Exiftool doesn't exist"),
    EXIFTOOL_NOT_FOUND("Exiftool not found: "),
    FFMPEG_NOT_EXIST("FFMepg doesn't exist"),
    ASPECTRATIO_NOT_CHANGE("Can not change the Aspect ratio"),
    VIDEOBITRATE_NOT_CHANGE("Can not change the Video Bit rate"),
    AUDIOBITRATE_NOT_CHANGE("Can not change the Audio Bit rate"),
    VIDEOCODEC_NOT_CHANGE("Can not change the Video codec"),
    VIDEO_NOT_CONVERTED("Can not convert a video"),
    FORMTAT_INVALID("Format is invalid"),
    FRAMERATE_NOT_CHANGE("Can not change the Frame rate"),
    SCALE_NOT_CHANGE("Can not change the Scale"),
    IMAGEMAGICK_NOT_EXIST("Image magick doesn't exist"),
    IMAGEMAGICK_NOT_FOUND("Image magick not found"),
    RESIZE_INVALID("Invalid resize values"),
    WIDTH_HEIGHT_INVALID("Width or height are invalid"),
    IMAGE_ROTATE_INVALID("Invalid Image rotate value"),
    COMMAND_NULL("Command value is NULL"),
    COMMAND_INVALID("Command value is invalid"),
    MD5_ERROR("Md5 Error! binary is invalid"),
    IMAGE_CONVERT_ERROR_MESSAGE("Error converting an Image"),
    AUDIO_CONVERT_ERROR_MESSAGE("Error converting an Audio"),
    LIBREOFFICE_NOT_EXIST("LibreOffice doesn't exist");


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
