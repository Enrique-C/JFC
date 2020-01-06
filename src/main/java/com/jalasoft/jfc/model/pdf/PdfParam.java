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

    // Content quantity of pdf pages.
    private int quantityOfPage;

    // Content image's width.
    private int width;

    // Content image's height.
    private int height;

    // Input Scale 1-10.
    private String scale;

    // Input Rotate in Degrees 90, 180, 270.
    private int rotate;

    // Content thumbnail value.
    private boolean thumbnail;

    // Content background value.
    private String background;

    // Content alpha value.
    private String alpha;

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
     * Gets quantity of pdf file pages.
     * @return
     */
    public int getQuantityOfPage() {
        return quantityOfPage;
    }

    /**
     * It gets width value.
     * @return width.
     */
    public int getWidth() {
        return width;
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
    public String getScale() {
        return scale;
    }

    /**
     * It gets thumbnail value.
     * @return thumbnail.
     */
    public boolean isThumbnail() {
        return thumbnail;
    }

    /**
     * This method gets the degrees of rotate value.
     * @return degrees of rotation.
     */
    public int getRotate() {
        return rotate;
    }

    /**
     * This method gets background value.
     * @return background.
     */
    public String getBackground() {
        return background;
    }

    /**
     * This method gets alpha value.
     * @return alpha.
     */
    public String getAlpha() {
        return alpha;
    }

    /**
     * It sets magick value.
     * @param magick value.
     */
    public void setMagick(String magick) {
        this.magick = magick;
    }

    /**
     * It sets imageMagick value.
     * @param imageFormat value.
     */
    public void setImageFormat(String imageFormat) {
        this.imageFormat = imageFormat;
    }

    /**
     * It sets number of pages to be converted.
     * @param pagesToConvert value.
     */
    public void setPagesToConvert(String pagesToConvert) {
        this.pagesToConvert = pagesToConvert;
    }

    /**
     * Sets quantity of pdf file pages.
     * @param quantityOfPage value.
     */
    public void setQuantityOfPage(int quantityOfPage) {
        this.quantityOfPage = quantityOfPage;
    }

    /**
     * It sets wight value.
     * @param width value.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * It sets height value.
     * @param height value.
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * This method sets the Scale that will be converted into JPEG, PNG, GIF, BMP and WBMP.
     * @param scale value.
     */
    public void setScale(String scale) {
        this.scale = scale;
    }

    /**
     * It sets thumbnail value.
     * @param thumbnail value.
     */
    public void setThumbnail(boolean thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * This method sets degrees to rotate image.
     * @param rotate value.
     */
    public void setRotate(int rotate) {
        this.rotate = rotate;
    }
}
