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
 * This class has the common attributes, Setters, and Getters of an
 * image.
 *
 * @version 0.1 11 Dec 2019.
 * @author Oscar Lopez.
 * */
public class ImageParam {

    //Variable for obtain input path of Image.
    private String inputPathFile;

    //Variable of output path of Image.
    private String outputPathFile;

    //Variable of output path of Thumbnail of a Image.
    private String outputPathThumbnail;

    //Variable for change the width of Image.
    private int widthOfFile;

    //Variable for change the height of Image.
    private int heightOfFile;

    //This is for changing the percentage
    //of white and black of a Image.
    private int whiteBlankPercentage;

    //Variable for rotate a Image.
    private double degreesToRotate;

    /**
     * Setter of inputPathFile.
     * @return inputPathFile.
     */
    public String getInputPathFile() {
        return inputPathFile;
    }

    /**
     * Setter of outputPathFile.
     * @return outputPathFile.
     */
    public String getOutputPathFile() {
        return outputPathFile;
    }

    /**
     * Setter of getOutputPathThumbnail.
     * @return getOutputPathThumbnail.
     */
    public String getOutputPathThumbnail() {
        return outputPathThumbnail;
    }

    /**
     * Setter of widthOfFile.
     * @return widthOfFile.
     */
    public int getWidthOfFile() {
        return widthOfFile;
    }

    /**
     * Setter of heightOfFile.
     * @return heightOfFile.
     */
    public int getHeightOfFile() {
        return heightOfFile;
    }

    /**
     * Setter of whiteBlankPercentage.
     * @return whiteBlankPercentage.
     */
    public int getWhiteBlankPercentage() {
        return whiteBlankPercentage;
    }

    /**
     * Setter of degreesToRotate.
     * @return degreesToRotate.
     */
    public double getDegreesToRotate() {
        return degreesToRotate;
    }

    /**
     * Getter of inputPathFile.
     * @param inputPathFile .
     */
    public void setInputPathFile(String inputPathFile) {
        this.inputPathFile = inputPathFile;
    }

    /**
     * Getter of outputPathFile.
     * @param outputPathFile
     */
    public void setOutputPathFile(String outputPathFile) {
        this.outputPathFile = outputPathFile;
    }

    /**
     * Getter of outputPathThumbnail.
     * @param outputPathThumbnail
     */
    public void setOutputPathThumbnail(String outputPathThumbnail) {
        this.outputPathThumbnail = outputPathThumbnail;
    }

    /**
     * Getter of widthOfFile.
     * @param widthOfFile
     */
    public void setWidthOfFile(int widthOfFile) {
        this.widthOfFile = widthOfFile;
    }

    /**
     * Getter of heightOfFile.
     * @param heightOfFile
     */
    public void setHeightOfFile(int heightOfFile) {
        this.heightOfFile = heightOfFile;
    }

    /**
     * Getter of whiteBlankPercentage.
     * @param whiteBlankPercentage
     */
    public void setWhiteBlankPercentage(int whiteBlankPercentage) {
        this.whiteBlankPercentage = whiteBlankPercentage;
    }

    /**
     * Getter of degreesToRotate.
     * @param degreesToRotate
     */
    public void setDegreesToRotate(double degreesToRotate) {
        this.degreesToRotate = degreesToRotate;
    }
}
