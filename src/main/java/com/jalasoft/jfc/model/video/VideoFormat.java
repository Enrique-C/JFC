package com.jalasoft.jfc.model.video;

/**
 * This enum is used for specify types of image formats.
 *
 * @version 0.1 02 Jan 2020
 *
 * @author Oscar Lopez
 */
public enum VideoFormat {
    MKV(".mkv"),
    MP4(".mp4"),
    MPG(".mpg"),
    AVI(".avi"),
    FLV(".flv"),
    MPEG(".mpeg"),
    GP3(".3gp"),
    MOV(".mov"),
    WMV(".wmv"),
    VID(".vid");

    // Command variable
    private String format;

    /**
     * This method is for setting command value.
     * @param format String value
     */
    VideoFormat(String format) {
        this.format = format;
    }

    /**
     * This method gets command value.
     * @return String value.
     */
    public String getVideoFormat(){
        return format;
    }
}
