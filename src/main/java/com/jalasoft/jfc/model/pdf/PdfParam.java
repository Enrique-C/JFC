/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 *  ("Confidential Information"). You shall not disclose such Confidential
 *  Information and shall use it only in accordance with the terms of the
 *  license agreement you entered into with Jalasoft.
 *
 */
package com.jalasoft.jfc.model.pdf;

import com.jalasoft.jfc.model.Param;
import com.jalasoft.jfc.model.pdf.PdfFormatImage;
import org.apache.pdfbox.rendering.ImageType;

/**
 * This class content attributes more relevant of a PDF.
 *
 * @version 0.1 13 Dic 2019
 *
 * @author Alan Escalera
 */
public class PdfParam extends Param {

    // Input Rotate in Degrees 90, 180, 270.
    private int rotate;

    // Input Scale 1-10.
    private float scale;

    // Input DPI 0-500.
    private int dpi;

    // Input Type of image  JPEG, PNG, GIF, BMP and WBMP.
    private ImageType imageType;

    // Input Format Image.
    private PdfFormatImage pdfFormatImage;

    public PdfParam(){
        rotate = 0;
        scale = 1;
        dpi = 100;
        pdfFormatImage = PdfFormatImage.JPEG;
    }

    /**
     * this method gets the degrees of rotate value.
     * @return degrees of rotation.
     */
    public int getRotate() {
        return rotate;
    }

    /**
     * this method sets degrees to rotate.
     * @param rotate
     */
    public void setRotate(int rotate) {
        this.rotate = rotate;
    }

    /**
     * This method gets the scale value.
     * @return scale
     */
    public float getScale() {
        return scale;
    }

    /**
     * This method sets the Scale that will be converted into JPEG, PNG, GIF, BMP and WBMP.
     * @param scale
     */
    public void setScale(float scale) {
        this.scale = scale;
    }

    /**
     * This method gets the DPI value.
     * @return dpi
     */
    public int getDpi() {
        return dpi;
    }

    /**
     * This method sets the DPI that will be converted.
     * @param dpi
     */
    public void setDpi(int dpi) {
        this.dpi = dpi;
    }

    /**
     * This method gets the image type that will be converted.
     * @return imageType
     */
    public ImageType getImageType() {
        return imageType;
    }

    /**
     * This method sets the image type that will be converted.
     * @param imageType
     */
    public void setImageType(ImageType imageType) {
        this.imageType = imageType;
    }

    /**
     * This method gets the Format Image to be converted.
     * @return formatImage
     */
    public PdfFormatImage getPdfFormatImage() {
        return pdfFormatImage;
    }

    /**
     * This method sets the Format image to be converted.
     * @param formatImage
     */
    public void setPdfFormatImage(PdfFormatImage formatImage) {
        this.pdfFormatImage = formatImage;
    }
}
