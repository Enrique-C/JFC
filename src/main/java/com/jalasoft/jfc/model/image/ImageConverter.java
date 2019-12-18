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

        imageConverter(param);

        return null;
    }

    private void imageConverter(Param param) {

        String IMAGE_MAGIC_PATH = "C:\\Users\\Luis\\Downloads\\ImageMagick-7.0.9-9-portable-Q16-x64\\magick.exe";
        String convertCommand = " magick ";

        try {
            ProcessBuilder process = new ProcessBuilder(IMAGE_MAGIC_PATH, convertCommand, param.getInputPathFile(), param.getOutputFileName());
            String prueba = process.toString();
            Process p = process.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
