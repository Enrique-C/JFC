/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.image;

import com.jalasoft.jfc.model.FileResult;
import com.jalasoft.jfc.model.IConverter;
import com.jalasoft.jfc.model.Param;
import java.io.IOException;

/**
 * Converts a image type to another.
 *
 * @version 0.1 11 Dec 2019.
 *
 * @author Oscar Lopez.
 * */
public class ImageConverter implements IConverter {

    /**
     * Changes an Image format to another one.
     * @param param Image parameters.
     * @return Conversion status.
     */
    public FileResult convert(Param param) {
        ImageParam imageParam = (ImageParam) param;
        FileResult fileResult = null;

        final String IMAGE_MAGIC_PATH = "thirdparty/ImageMagick/magick.exe";
        final String STRING_SPACE = " ";
        final String CONVERT = "convert";

        StringBuilder command = new StringBuilder();
        String commandString;

        command.append(IMAGE_MAGIC_PATH);
        command.append(STRING_SPACE);
        command.append(CONVERT);
        command.append(STRING_SPACE);

        if (!(imageParam.getInputPathFile() == null)) {
            command.append(imageParam.getInputPathFile());
            command.append(STRING_SPACE);
            command.append(imageParam.getOutputPathFile());
            command.append(imageParam.getOutputFileName());

            imageFormatter(imageParam);

            command.append(imageParam.getImageFormat().getImageFormat());

            commandString = command.toString();
            try {
                Runtime.getRuntime().exec(commandString);
                fileResult = new FileResult();
                fileResult.setPath(imageParam.getOutputPathFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileResult;
    }

    private void imageFormatter(ImageParam imageParam) {
        if (imageParam.getImageFormat() == null) {
            imageParam.setImageFormat(ImageFormat.JPEG);
        }
    }
}
