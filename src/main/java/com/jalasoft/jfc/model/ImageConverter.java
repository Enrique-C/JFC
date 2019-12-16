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
 * This class has attribute, fileNamePath that has a path for program
 * and variable THUMBNAILVALUE constant for Thumbnail size;
 * ImageMagick.
 *
 * @version 0.1 11 Dec 2019.
 * @author Oscar Lopez.
 * */
public class ImageConverter {

    //path where ImageMagick is installed.
    private String fileNamePath = "C:\\Program Files (x86)\\ImageMagick-6.3.9-Q8\\";

    //default value for creating thumbnail.
    private final int THUMBNAILVALUE = 128;

    /**
     * convertImage Method for changing an Image format to
     * another one, resize it rotate, blank and white, 
     * and create thumbnail.
     * @param image variable of type Image.
     * @return true if conversion is successful, false if fail.
     */
    protected boolean convertImage(ImageParam image) {
        ProcessStarter.setGlobalSearchPath(fileNamePath);
        verifyDataValues(image);
        boolean resultAccion = false;
        try {
            ConvertCmd cmd = new ConvertCmd();
            IMOperation op = new IMOperation();

            op.addImage(image.getInputPathFile());
            op.resize(image.getWidthOfFile(), image.getHeightOfFile());
            op.rotate(image.getDegreesToRotate());
            op.threshold(image.getWhiteBlankPercentage());
            op.addImage(image.getOutputPathFile());

            cmd.run(op);

            op = new IMOperation();
            op.size(THUMBNAILVALUE);
            op.addImage(image.getInputPathFile());
            op.thumbnail(THUMBNAILVALUE);
            op.addImage(image.getOutputPathFile());
            cmd.run(op);
            resultAccion = true;
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }finally {
            return resultAccion;
        }
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

        //filename variable obtain input path of file.
        String fileName = image.getInputPathFile();
        //Info is class to obtain information about of File
        Info imageInfo = null;
        try {
            imageInfo = new Info(fileName, true);
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
