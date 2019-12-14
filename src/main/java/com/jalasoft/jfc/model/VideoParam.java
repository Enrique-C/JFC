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
 *  VideoParam Class is used for specify attributes of video.
 *
 * @version 1.0 13 Dic 2019
 *
 * @author Juan Martinez
 */
public class VideoParam {

    private String fFmpeg;          //binary variable of FFmpeg.
    private String inputPathFile;   //Content path io input file.
    private String outputPathFile;  //Content path of output file.
    private String outputFileName;  //Content path of output file name.
    private double aspectRatio;     //Content aspect ratio value.
    private String frameRate;       //Content frameRate value.
    private int wight;              //Content video's wight.
    private int height;             //Content video's height.
    private String videoCodec;      //Content videoCodec value.
    private String audioCodec;      //Content audioCodec value.
    private String videoBitRate;    //Content videoBitRate value.
    private String audioBitRate;    //Content audioBitRate value.
    private byte quality;           //Content quality of video.
    private byte channelsNumber;    //Content number of audio channels
    private String volume;          //Content audio volume value.
    private String rotate;          //Content rotate value

    /**
     * Constructor of VideoParam class.
     * @param fFmpeg is for getting the binary path value.
     */
    public VideoParam(String fFmpeg){
        this.fFmpeg = fFmpeg;
    }

    /**
     * getfFmpeg gets fFmpeg value.
     * @return fFmpeg String value.
     */
    public String getfFmpeg() {
        return fFmpeg;
    }

    /**
     * getInputPathFile gets inputPathFile value.
     * @return inputPathFile String value.
     */
    public String getInputPathFile() {
        return inputPathFile;
    }

    /**
     * getInputPathFile gets inputPathFile value.
     * @return inputPathFile String value.
     */
    public String getOutputPathFile() {
        return outputPathFile;
    }

    /**
     * getOutputFileName gets outputFileName value.
     * @return outputFileName String value.
     */
    public String getOutputFileName() {
        return outputFileName;
    }

    /**
     * getAspectRatio gets aspectRatio value.
     * @return aspectRatio double value.
     */
    public double getAspectRatio() {
        return aspectRatio;
    }

    /**
     * getFrameRate gets frameRate value.
     * @return frameRate String value.
     */
    public String getFrameRate() {
        return frameRate;
    }

    /**
     * getWight gets wight value.
     * @return wight int value.
     */
    public int getWight() {
        return wight;
    }

    /**
     * getHeight gets height value.
     * @return height int value.
     */
    public int getHeight() {
        return height;
    }

    /**
     * getVideoCodec gets videoCodec value.
     * @return videoCodec String value.
     */
    public String getVideoCodec() {
        return videoCodec;
    }

    /**
     * getAudioCodec gets audioCodec value.
     * @return audioCodec String value.
     */
    public String getAudioCodec() {
        return audioCodec;
    }

    /**
     * getVideoBitRate gets videoBitRate value.
     * @return videoBitRate String value.
     */
    public String getVideoBitRate() {
        return videoBitRate;
    }

    /**
     * getAudioBitRate gets audioBitRate value.
     * @return audioBitRate String value.
     */
    public String getAudioBitRate() {
        return audioBitRate;
    }

    /**
     * getQuality gets quality value.
     * @return quality value in bytes.
     */
    public byte getQuality() {
        return quality;
    }

    /**
     * getChannelsNumber gets channelsNumber value.
     * @return channelsNumber value in bytes.
     */
    public byte getChannelsNumber() {
        return channelsNumber;
    }

    /**
     * getVolume gets volume value.
     * @return volume value.
     */
    public String getVolume() {
        return volume;
    }

    /**
     * getRotate gets rotate value.
     * @return degrees of rotation.
     */
    public String getRotate() {
        return rotate;
    }

    /**
     * setInputPathFile sets inputPathFile value.
     * @param inputPathFile for setting in this.inputPathFile.
     */
    public void setInputPathFile(String inputPathFile) {
        this.inputPathFile = inputPathFile;
    }

    /**
     * setOutputPathFile sets outputPathFile value.
     * @param outputPathFile for setting in this.outputPathFile.
     */
    public void setOutputPathFile(String outputPathFile) {
        this.outputPathFile = outputPathFile;
    }

    /**
     * setOutputFileName sets outputFileName value.
     * @param outputFileName for setting this.outputFileName.
     */
    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }

    /**
     * setAspectRatio sets aspectRatio value.
     * @param aspectRatio for setting this.aspectRatio.
     */
    public void setAspectRatio(double aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    /**
     * setFrameRate sets frameRate value.
     * @param frameRate for setting this.frameRate.
     */
    public void setFrameRate(String frameRate) {
        this.frameRate = frameRate;
    }

    /**
     * setWight sets wight value.
     * @param wight for setting this.wight.
     */
    public void setWight(int wight) {
        this.wight = wight;
    }

    /**
     * setHeight sets height value.
     * @param height for setting this.height.
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * setVideoCodec sets videoCodec value.
     * @param videoCodec for setting this.videoCodec.
     */
    public void setVideoCodec(String videoCodec) {
        this.videoCodec = videoCodec;
    }

    /**
     * setAudioCodec sets audioCodec value.
     * @param audioCodec for setting this.audioCodec.
     */
    public void setAudioCodec(String audioCodec) {
        this.audioCodec = audioCodec;
    }

    /**
     * setVideoBitRate sets videoBitRate value.
     * @param videoBitRate for setting this.videoBitRate.
     */
    public void setVideoBitRate(String videoBitRate) {
        this.videoBitRate = videoBitRate;
    }

    /**
     * setAudioBitRate sets audioBitRate value.
     * @param audioBitRate for setting this.audioBitRate.
     */
    public void setAudioBitRate(String audioBitRate) {
        this.audioBitRate = audioBitRate;
    }

    /**
     * setQuality sets quality value.
     * @param quality for setting this.quality.
     */
    public void setQuality(byte quality) {
        this.quality = quality;
    }

    /**
     * setChannelsNumber sets channelsNumber value.
     * @param channelsNumber for setting this.channelsNumber.
     */
    public void setChannelsNumber(byte channelsNumber) {
        this.channelsNumber = channelsNumber;
    }

    /**
     * setVolume sets volume value.
     * @param volume for setting this.volume.
     */
    public void setVolume(String volume) {
        this.volume = volume;
    }

    /**
     * setRotate sets rotate value.
     * @param rotate for setting this.rotate.
     */
    public void setRotate(String rotate) {
        this.rotate = rotate;
    }
}
