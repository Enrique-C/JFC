
/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 *  ("Confidential Information"). You shall not disclose such Confidential
 *  Information and shall use it only in accordance with the terms of the
 *  license agreement you entered into with Jalasoft.
 */
package com.jalasoft.jfc.model;
import org.apache.pdfbox.rendering.ImageType;

/**
 *
 *  This class contend attributes more relevant of a PDF.
 *
 * @version 1.0 13 Dic 2019
 *
 * @author Alan Escalera
 */

public class PdfParam {
    private String inputPathFile;
    private String outputPathFile;
    private String outputFileName;
    private int rotate;                 // Input Rotate in Degrees 90, 180, 270.
    private float scale;                // Input Scale 1-10.
    private int dpi;                    // Input DPI 0-500.
    private ImageType imageType;        // Input Type of image  JPEG, PNG, GIF, BMP and WBMP.
    private FormatImage formatImage;    // Input Format Image.

    public PdfParam(){
        rotate = 0;
        scale = 1;
        dpi = 100;
        formatImage = FormatImage.JPEG;
    }

    /**
     * this method get the path input.
     * @return inputPathFile
     */
    public String getPathInput() {
        return inputPathFile;
    }

    /**
     * this method set the path input.
     * @param filePathIn
     */
    public void setPathInput(String filePathIn) {
        this.inputPathFile = filePathIn;
    }

    /**
     * this method get the path output.
     * @return outputPathFile
     */
    public String getPathOuput() {
        return outputPathFile;
    }

    /**
     * this method set the path output.
     * @param filePathOut
     */
    public void setPathOuput(String filePathOut) {
        this.outputPathFile = filePathOut;
    }

    /**
     * this method get the degrees to rotate.
     * @return rotate
     */
    public int getRotate() {
        return rotate;
    }

    /**
     * this method set the degrees to rotate.
     * @param rotate
     */
    public void setRotate(int rotate) {
        this.rotate = rotate;
    }

    /**
     * This method get the scale set.
     * @return
     */
    public float getScale() {
        return scale;
    }

    /**
     * This method set the Scale that will be converted JPEG, PNG, GIF, BMP and WBMP.
     * @param scale
     */
    public void setScale(float scale) {
        this.scale = scale;
    }

    /**
     * This method get the the DPI set.
     * @return dpi
     */
    public int getDpi() {
        return dpi;
    }

    /**
     * This method set the DPI that will be converted.
     * @param dpi
     */
    public void setDpi(int dpi) {
        this.dpi = dpi;
    }

    /**
     * This method get the image type that will be converted.
     * @return imageType
     */
    public ImageType getImageType() {
        return imageType;
    }

    /**
     * This method set the image type that will be converted.
     * @param imageType
     */
    public void setImageType(ImageType imageType) {
        this.imageType = imageType;
    }

    /**
     * This method get the Format Image to be converted.
     * @return formatImage
     */
    public FormatImage getFormatImage() {
        return formatImage;
    }

    /**
     * This method set the Format image to be converted.
     * @param formatimage
     */
    public void setFormatImage(FormatImage formatimage) {
        this.formatImage = formatimage;
    }

    /**
     * This method get the name file given to be converted.
     * @return outputFileName
     */
    public String getNameFile() {
        return outputFileName;
    }

    /**
     * This method set the name file given to be converted.
     * @param nameFile
     */
    public void setOutputFileName(String nameFile) {
        this.outputFileName = "/"+ nameFile;
    }
}
