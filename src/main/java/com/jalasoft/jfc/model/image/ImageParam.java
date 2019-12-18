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

    // Contains output path of Thumbnail of a Image.
    private String outputPathThumbnail;

    // Contains width of Image.
    private int widthOfFile;

    // Contains height of Image.
    private int heightOfFile;

    // Contains the percentage white and black of a Image.
    private int whiteBlankPercentage;

    // Contains the degrees to rotate a Image.
    private double degreesToRotate;

    /**
     * Sets inputPathFile.
     * @return inputPathFile.
     */
    public String getInputPathFile() {
        return this.getInputPathFile();
    }

    /**
     * Sets outputPathFile.
     * @return outputPathFile.
     */
    public String getOutputPathFile() {
        return this.getOutputPathFile();
    }

    /**
     * Sets getOutputPathThumbnail.
     * @return getOutputPathThumbnail.
     */
    public String getOutputPathThumbnail() {
        return outputPathThumbnail;
    }

    /**
     * Sets widthOfFile.
     * @return widthOfFile.
     */
    public int getWidthOfFile() {
        return widthOfFile;
    }

    /**
     * Sets heightOfFile.
     * @return heightOfFile.
     */
    public int getHeightOfFile() {
        return heightOfFile;
    }

    /**
     * Sets whiteBlankPercentage.
     * @return whiteBlankPercentage.
     */
    public int getWhiteBlankPercentage() {
        return whiteBlankPercentage;
    }

    /**
     * Sets degreesToRotate.
     * @return degreesToRotate.
     */
    public double getDegreesToRotate() {
        return degreesToRotate;
    }

    /**
     * Gets inputPathFile.
     * @param inputPathFile
     */
    public void setInputPathFile(String inputPathFile) {
        this.setInputPathFile(inputPathFile);
    }

    /**
     * Gets outputPathFile.
     * @param outputPathFile
     */
    public void setOutputPathFile(String outputPathFile) {
        this.setOutputFileName(outputPathFile);
    }

    /**
     * Gets outputPathThumbnail.
     * @param outputPathThumbnail
     */
    public void setOutputPathThumbnail(String outputPathThumbnail) {
        this.outputPathThumbnail = outputPathThumbnail;
    }

    /**
     * Gets widthOfFile.
     * @param widthOfFile
     */
    public void setWidthOfFile(int widthOfFile) {
        this.widthOfFile = widthOfFile;
    }

    /**
     * Gets heightOfFile.
     * @param heightOfFile
     */
    public void setHeightOfFile(int heightOfFile) {
        this.heightOfFile = heightOfFile;
    }

    /**
     * Gets whiteBlankPercentage.
     * @param whiteBlankPercentage
     */
    public void setWhiteBlankPercentage(int whiteBlankPercentage) {
        this.whiteBlankPercentage = whiteBlankPercentage;
    }

    /**
     * Gets degreesToRotate.
     * @param degreesToRotate
     */
    public void setDegreesToRotate(double degreesToRotate) {
        this.degreesToRotate = degreesToRotate;
    }
}
