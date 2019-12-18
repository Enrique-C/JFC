/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */
package com.jalasoft.jfc.model.pdf;

import com.jalasoft.jfc.model.Param;

/**
 * This class content attributes more relevant of a PDF.
 *
 * @version 0.1 13 Dic 2019
 *
 * @author Alan Escalera
 */
public class PdfParam extends Param {

    // Content magick path.
    private String magick;

    // Content image format value.
    private String imageFormat;

    // Content number of pages to convert
    private String pagesToConvert;

    // Content image's wight.
    private int wight;

    // Content image's height.
    private int height;

    // Input Scale 1-10.
    private float scale;

    // Input Rotate in Degrees 90, 180, 270.
    private int rotate;

    // Content thumbnail value.
    private String thumbnail;

    /**
     * It gets magick value.
     * @return magick.
     */
    public String getMagick() {
        return magick;
    }

    /**
     * It gets imageFormat value.
     * @return imageFormat.
     */
    public String getImageFormat() {
        return imageFormat;
    }

    /**
     * It gets number of pages to convert them.
     * @return pagesToConvert.
     */
    public String getPagesToConvert() {
        return pagesToConvert;
    }

    /**
     * It gets wight value.
     * @return
     */
    public int getWight() {
        return wight;
    }

    /**
     * It gets height value.
     * @return height.
     */
    public int getHeight() {
        return height;
    }

    /**
     * This method gets the scale value.
     * @return scale
     */
    public float getScale() {
        return scale;
    }

    /**
     * It gets thumbnail value.
     * @return thumbnail.
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * this method gets the degrees of rotate value.
     * @return degrees of rotation.
     */
    public int getRotate() {
        return rotate;
    }

    /**
     * It sets magick value.
     * @param magick
     */
    public void setMagick(String magick) {
        this.magick = magick;
    }

    /**
     * It sets imageMagick value.
     * @param imageFormat
     */
    public void setImageFormat(String imageFormat) {
        this.imageFormat = imageFormat;
    }

    /**
     * It sets number of pages to be converted.
     * @param pagesToConvert
     */
    public void setPagesToConvert(String pagesToConvert) {
        this.pagesToConvert = pagesToConvert;
    }

    /**
     * It sets wight value.
     * @param wight
     */
    public void setWight(int wight) {
        this.wight = wight;
    }

    /**
     * It sets height value.
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * This method sets the Scale that will be converted into JPEG, PNG, GIF, BMP and WBMP.
     * @param scale
     */
    public void setScale(float scale) {
        this.scale = scale;
    }

    /**
     * It sets thumbnail value.
     * @param thumbnail
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * this method sets degrees to rotate image.
     * @param rotate
     */
    public void setRotate(int rotate) {
        this.rotate = rotate;
    }
}
