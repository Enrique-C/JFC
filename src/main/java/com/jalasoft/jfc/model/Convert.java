/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 *  ("Confidential Information"). You shall not disclose such Confidential
 *  Information and shall use it only in accordance with the terms of the
 *  license agreement you entered into with Jalasoft.
 */
package com.jalasoft.jfc.model;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;
import org.im4java.core.Info;
import org.im4java.core.InfoException;
import org.im4java.process.ProcessStarter;

/**
 * This class has attribute, mypath that has a path for program
 * ImageMggick.
 *
 * @version 0.1 11 Dec 2019.
 * @author Oscar Lopez.
 * */
public class Convert {
    /**
     *  myPath = path where ImageMagick is installed.
     */
    protected String myPath = "C:\\Program Files (x86)\\ImageMagick-6.3.9-Q8\\";
    /**
     *  thumbnailValue = default value for creating thumbnail.
     */
    private final int THUMBNAILVALUE = 128;

    /**
     * convertImage Method por change a Image format, resize,
     * rotate, blank and white, and create thumbnail.
     * @param image variable of type Image.
     * @return true if conversion is successful, false if fail.
     */
    protected boolean convertImage(ImageParam image) {
        ProcessStarter.setGlobalSearchPath(myPath);
        verifyDataValues(image);
        try {
            /**
             *Create varialbe command cmd.
            */
            ConvertCmd cmd = new ConvertCmd();
            /**
            *Create the operation, add images and operators/options
             *resize, rotate, threshold is for change the Image to
             *White and Black, and thumbnail to create the thumbnail
             *of the image.
             */
            IMOperation op = new IMOperation();
            op.addImage(image.getInputPathFile());
            op.resize(image.getWidthOfFile(), image.getHeightOfFile());
            op.rotate(image.getDegreesToRotate());
            op.threshold(image.getWhiteBlankPercentage());
            op.addImage(image.getOutputPathFile());
            // execute the operation
            cmd.run(op);
            op = new IMOperation();
            op.size(THUMBNAILVALUE);
            op.addImage(image.getInputPathFile());
            op.thumbnail(THUMBNAILVALUE);
            op.addImage(image.getOutputPathFile());
            cmd.run(op);
        }catch(Exception e) {
            return false;
        }
        return true;
    }

    /**
     * verifyDataValues are the method that validates data of file,
     * if getWidthOfFile is equals 0 change this value for the width
     * of file, if getHeightOfFile is equals 0 change this value for
     * the height of file, getWhiteBlankPercentage must be a number
     * between 0 and 100 if not setWhiteBlankPercentage takes the
     * value of 0, DegreesToRotate must be a number between 0 and
     * 360, if DegreesToRotate takes the value of 0.
     * @param image variable of type Image.
     */
    public void verifyDataValues(ImageParam image) {
        /**
         * filename variable obtain input path of file.
         */
        String filename = image.getInputPathFile();
        /**
         * Info is class to obtain information about of File
         * imageInfo is variable to save the path of File.
         */
        Info imageInfo = null;
        try {
            /**
             * Get data of file Image Width, Height.
             * Give new values to getWidthOfFile, setWidthOfFile,
             * getWhiteBlankPercentage, getDegreesToRotate to control
             * values that are not within the permitted range.
             */
            imageInfo = new Info(filename,true);
            if (image.getWidthOfFile() == 0) {
                image.setWidthOfFile(imageInfo.getImageWidth());
            }
            if (image.getHeightOfFile() == 0) {
                image.setHeightOfFile(imageInfo.getImageHeight());
            }
            if (image.getWhiteBlankPercentage() < 0 || image.getWhiteBlankPercentage() > 100) {
                image.setWhiteBlankPercentage(0);
            }
            if (image.getDegreesToRotate() < 0 || image.getDegreesToRotate() > 360) {
                image.setDegreesToRotate(0);
            }
        } catch (InfoException e) {
            e.printStackTrace();
        }
    }
}
