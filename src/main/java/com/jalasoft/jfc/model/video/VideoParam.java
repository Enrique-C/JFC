/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.video;

import com.jalasoft.jfc.model.Param;

/**
 * Class is used for specifying attributes of VideoParam.
 *
 * @version 0.1 13 Dic 2019
 *
 * @author Juan Martinez
 */
public class VideoParam extends Param {

    // Content aspect ratio value.
    private String aspectRatio;

    // Content frameRate value.
    private String frameRate;

    // Content video's width.
    private int width;

    // Content video's height.
    private int height;

    // Content videoCodec value.
    private String videoCodec;

    // Content videoBitRate value.
    private String videoBitRate;

    // Content a specific time.
    private boolean thumbnail;

    //Contains format of an Video.
    private String videoFormat;

    /**
     * Gets aspectRatio value.
     * @return aspectRatio double value.
     */
    public String getAspectRatio() {
        return aspectRatio;
    }

    /**
     * Gets frameRate value.
     * @return frameRate String value.
     */
    public String getFrameRate() {
        return frameRate;
    }

    /**
     * Gets wight value.
     * @return wight int value.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets height value.
     * @return height int value.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets videoCodec value.
     * @return videoCodec String value.
     */
    public String getVideoCodec() {
        return videoCodec;
    }

    /**
     * Gets videoBitRate value.
     * @return videoBitRate String value.
     */
    public String getVideoBitRate() {
        return videoBitRate;
    }

    /**
     * Gets thumbnail value.
     * @return value of thumbnail.
     */
    public boolean getThumbnail() {
        return thumbnail;
    }

    /**
     * Gets Video Format.
     * @return value of Video format.
     */
    public String getVideoFormat() {
        return videoFormat;
    }

    /**
     * Sets aspectRatio value.
     * @param aspectRatio for setting this.aspectRatio.
     */
    public void setAspectRatio(String aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    /**
     * Sets frameRate value.
     * @param frameRate for setting this.frameRate.
     */
    public void setFrameRate(String frameRate) {
        this.frameRate = frameRate;
    }

    /**
     * Sets width value.
     * @param width for setting this.width.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Sets height value.
     * @param height for setting this.height.
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Sets videoCodec value.
     * @param videoCodec for setting this.videoCodec.
     */
    public void setVideoCodec(String videoCodec) {
        this.videoCodec = videoCodec;
    }

    /**
     * Sets videoBitRate value.
     * @param videoBitRate for setting this.videoBitRate.
     */
    public void setVideoBitRate(String videoBitRate) {
        this.videoBitRate = videoBitRate;
    }

    /**
     * Sets thumbnail's value.
     * @param thumbnail time String value.
     */
    public void setThumbnail(boolean thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * Sets videoFormat value.
     * @param videoFormat value.
     */
    public void setVideoFormat(String videoFormat) {
        this.videoFormat = videoFormat;
    }
}
