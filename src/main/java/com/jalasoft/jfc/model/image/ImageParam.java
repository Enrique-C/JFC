/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.image;

import com.jalasoft.jfc.model.Param;

/**
 * Manages params of an image.
 *
 * @version 0.1 11 Dec 2019.
 *
 * @author Oscar Lopez.
 * */
public class ImageParam extends Param {

    //Contains format of an image.
    private  String imageFormat;

    // Contains a boolean value for building a Thumbnail.
    private boolean isThumbnail;

    // Contains width of Image.
    private int imageWidth;

    // Contains height of Image.
    private int imageHeight;

    // Contains the degrees to rotate an Image.
    private float degreesToRotate;

    /**
     * Gets format of an image.
     * @return image format.
     */
        public String getImageFormat() {
        return imageFormat;
    }

    /**
     * Gets getOutputPathThumbnail.
     * @return getOutputPathThumbnail.
     */
    public boolean isThumbnail() {
        return isThumbnail;
    }

    /**
     * Gets widthOfFile.
     * @return widthOfFile.
     */
    public int getImageWidth() {
        return imageWidth;
    }

    /**
     * Gets heightOfFile.
     * @return heightOfFile.
     */
    public int getImageHeight() {
        return imageHeight;
    }

    /**
     * Gets degreesToRotate.
     * @return degreesToRotate.
     */
    public float getDegreesToRotate() {
        return degreesToRotate;
    }

    /**
     * Sets image format.
     * @param imageFormat contains a image format.
     */
    public void setImageFormat(String imageFormat) {
        this.imageFormat = imageFormat;
    }

    /**
     * Sets ifThumbnail.
     * @param ifThumbnail receives path.
     */
    public void isThumbnail(boolean ifThumbnail) {
        this.isThumbnail = ifThumbnail;
    }

    /**
     * Sets widthOfFile.
     * @param imageWidth receives width.
     */
    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    /**
     * Sets heightOfFile.
     * @param imageHeight receives height.
     */
    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    /**
     * Sets degreesToRotate.
     * @param degreesToRotate receives degrees.
     */
    public void setDegreesToRotate(float degreesToRotate) {
        this.degreesToRotate = degreesToRotate;
    }
}
