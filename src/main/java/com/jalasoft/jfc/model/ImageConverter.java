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
 * Converts a image type to another.
 *
 * @version 0.1 11 Dec 2019.
 *
 * @author Oscar Lopez.
 * */
public class ImageConverter {

    /**
     * Changes an Image format to another one.
     * @param imageParam Image parameters.
     * @return Conversion status.
     */
    protected boolean convertImage(ImageParam imageParam) {
        String IMAGE_MAGIC_PATH = "C:\\Program Files (x86)\\ImageMagick-6.3.9-Q8\\";
        final int THUMBNAIL_VALUE = 128;

        ProcessStarter.setGlobalSearchPath(IMAGE_MAGIC_PATH);
        verifyDataValues(imageParam);
        boolean convertResult = false;

        try {
            ConvertCmd cmd = new ConvertCmd();
            IMOperation op = new IMOperation();

            op.addImage(imageParam.getInputPathFile());
            op.resize(imageParam.getWidthOfFile(), imageParam.getHeightOfFile());
            op.rotate(imageParam.getDegreesToRotate());
            op.threshold(imageParam.getWhiteBlankPercentage());
            op.addImage(imageParam.getOutputPathFile());

            cmd.run(op);

            op = new IMOperation();
            op.size(THUMBNAIL_VALUE);
            op.addImage(imageParam.getInputPathFile());
            op.thumbnail(THUMBNAIL_VALUE);
            op.addImage(imageParam.getOutputPathFile());
            cmd.run(op);
            convertResult = true;
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }finally {
            return convertResult;
        }
    }

    /**
     * Validates parameters of a file.
     * @param imageParam Image parameters.
     */
    public void verifyDataValues(ImageParam imageParam) {
        String fileName = imageParam.getInputPathFile();
        final int NO_SET = 0;
        Info imageInfo;

        try {
            imageInfo = new Info(fileName, true);
            if (imageParam.getWidthOfFile() == NO_SET) {
                imageParam.setWidthOfFile(imageInfo.getImageWidth());
            }
            if (imageParam.getHeightOfFile() == NO_SET) {
                imageParam.setHeightOfFile(imageInfo.getImageHeight());
            }
            if (imageParam.getWhiteBlankPercentage() < NO_SET || imageParam.getWhiteBlankPercentage() > 100) {
                imageParam.setWhiteBlankPercentage(0);
            }
            if (imageParam.getDegreesToRotate() < NO_SET || imageParam.getDegreesToRotate() > 360) {
                imageParam.setDegreesToRotate(0);
            }
        } catch (InfoException e) {
            e.printStackTrace();
        }
    }
}
