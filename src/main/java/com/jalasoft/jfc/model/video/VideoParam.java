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

    // Binary variable of FFmpeg.
    private String fFmpeg;

    // Content aspect ratio value.
    private double aspectRatio;

    // Content frameRate value.
    private String frameRate;

    // Content video's width.
    private int width;

    // Content video's height.
    private int height;

    // Content videoCodec value.
    private String videoCodec;

    // Content audioCodec value.
    private String audioCodec;

    // Content videoBitRate value.
    private String videoBitRate;

    // Content audioBitRate value.
    private String audioBitRate;

    // Content quality of video.
    private int quality;

    // Content number of audio channels.
    private int channelsNumber;

    // Content audio volume value.
    private String volume;

    // Content rotate value.
    private String rotate;

    // Content a specific time.
    private String thumbnail;

    // Number of video frames to output.
    private byte videoFrame;

    /**
     * Constructor of VideoParam class.
     * @param fFmpeg is for getting the binary path value.
     */
    public VideoParam(String fFmpeg){
        this.fFmpeg = fFmpeg;
    }

    /**
     * Gets fFmpeg value.
     * @return fFmpeg String value.
     */
    public String getfFmpeg() {
        return fFmpeg;
    }

    /**
     * Gets aspectRatio value.
     * @return aspectRatio double value.
     */
    public double getAspectRatio() {
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
     * Gets audioCodec value.
     * @return audioCodec String value.
     */
    public String getAudioCodec() {
        return audioCodec;
    }

    /**
     * Gets videoBitRate value.
     * @return videoBitRate String value.
     */
    public String getVideoBitRate() {
        return videoBitRate;
    }

    /**
     * Gets audioBitRate value.
     * @return audioBitRate String value.
     */
    public String getAudioBitRate() {
        return audioBitRate;
    }

    /**
     * Gets quality value.
     * @return quality value in bytes.
     */
    public int getQuality() {
        return quality;
    }

    /**
     * Gets channelsNumber value.
     * @return channelsNumber value in bytes.
     */
    public int getChannelsNumber() {
        return channelsNumber;
    }

    /**
     * Gets volume value.
     * @return volume value.
     */
    public String getVolume() {
        return volume;
    }

    /**
     * Gets rotate value.
     * @return degrees of rotation.
     */
    public String getRotate() {
        return rotate;
    }

    /**
     * Gets thumbnail value.
     * @return value of thumbnail.
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * Gets videoFrame value.
     * @return videoFrame value.
     */
    public byte getVideoFrame() {
        return videoFrame;
    }

    /**
     * Sets aspectRatio value.
     * @param aspectRatio for setting this.aspectRatio.
     */
    public void setAspectRatio(double aspectRatio) {
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
     * Sets audioCodec value.
     * @param audioCodec for setting this.audioCodec.
     */
    public void setAudioCodec(String audioCodec) {
        this.audioCodec = audioCodec;
    }

    /**
     * Sets videoBitRate value.
     * @param videoBitRate for setting this.videoBitRate.
     */
    public void setVideoBitRate(String videoBitRate) {
        this.videoBitRate = videoBitRate;
    }

    /**
     * Sets audioBitRate value.
     * @param audioBitRate for setting this.audioBitRate.
     */
    public void setAudioBitRate(String audioBitRate) {
        this.audioBitRate = audioBitRate;
    }

    /**
     * Sets quality value.
     * @param quality for setting this.quality.
     */
    public void setQuality(int quality) {
        this.quality = quality;
    }

    /**
     * Sets channelsNumber value.
     * @param channelsNumber for setting this.channelsNumber.
     */
    public void setChannelsNumber(int channelsNumber) {
        this.channelsNumber = channelsNumber;
    }

    /**
     * Sets volume value.
     * @param volume for setting this.volume.
     */
    public void setVolume(String volume) {
        this.volume = volume;
    }

    /**
     * Sets rotate value.
     * @param rotate for setting this.rotate.
     */
    public void setRotate(String rotate) {
        this.rotate = rotate;
    }

    /**
     * Sets thumbnail value.
     * @param thumbnail time String value.
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * Sets videoFrame value.
     * @param videoFrame value.
     */
    public void setVideoFrame(byte videoFrame) {
        this.videoFrame = videoFrame;
    }
}
