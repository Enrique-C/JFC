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
    private  ImageFormat imageFormat;

    // Contains output path of Thumbnail of an Image.
    private String outputPathThumbnail;

    // Contains width of Image.
    private int widthOfFile;

    // Contains height of Image.
    private int heightOfFile;

    // Contains the percentage white and black of an Image.
    private int whiteBlankPercentage;

    // Contains the degrees to rotate an Image.
    private double degreesToRotate;

    /**
     * Gets format of an image.
     * @return image format.
     */
    public ImageFormat getImageFormat() {
        return imageFormat;
    }

    /**
     * Gets getOutputPathThumbnail.
     * @return getOutputPathThumbnail.
     */
    public String getOutputPathThumbnail() {
        return outputPathThumbnail;
    }

    /**
     * Gets widthOfFile.
     * @return widthOfFile.
     */
    public int getWidthOfFile() {
        return widthOfFile;
    }

    /**
     * Gets heightOfFile.
     * @return heightOfFile.
     */
    public int getHeightOfFile() {
        return heightOfFile;
    }

    /**
     * Gets whiteBlankPercentage.
     * @return whiteBlankPercentage.
     */
    public int getWhiteBlankPercentage() {
        return whiteBlankPercentage;
    }

    /**
     * Gets degreesToRotate.
     * @return degreesToRotate.
     */
    public double getDegreesToRotate() {
        return degreesToRotate;
    }

    /**
     * Sets image format.
     * @param imageFormat contains a image format.
     */
    public void setImageFormat(ImageFormat imageFormat) {
        this.imageFormat = imageFormat;
    }

    /**
     * Sets outputPathThumbnail.
     * @param outputPathThumbnail receives path.
     */
    public void setOutputPathThumbnail(String outputPathThumbnail) {
        this.outputPathThumbnail = outputPathThumbnail;
    }

    /**
     * Sets widthOfFile.
     * @param widthOfFile receives width.
     */
    public void setWidthOfFile(int widthOfFile) {
        this.widthOfFile = widthOfFile;
    }

    /**
     * Sets heightOfFile.
     * @param heightOfFile receives height.
     */
    public void setHeightOfFile(int heightOfFile) {
        this.heightOfFile = heightOfFile;
    }

    /**
     * Sets whiteBlankPercentage.
     * @param whiteBlankPercentage receives percentage.
     */
    public void setWhiteBlankPercentage(int whiteBlankPercentage) {
        this.whiteBlankPercentage = whiteBlankPercentage;
    }

    /**
     * Sets degreesToRotate.
     * @param degreesToRotate receives degrees.
     */
    public void setDegreesToRotate(double degreesToRotate) {
        this.degreesToRotate = degreesToRotate;
    }
}
