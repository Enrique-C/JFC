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
import com.jalasoft.jfc.model.exception.ConvertException;
import com.jalasoft.jfc.model.strategy.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Converts a image type to another.
 *
 * @version 0.1 11 Dec 2019.
 *
 * @author Oscar Lopez.
 * */
public class ImageConverter implements IConverter {

    // List of commands.
    List<ICommandStrategy> commandStrategyList = new ArrayList<>();

    /**
     * Changes an Image format to another one.
     * @param param Image parameters.
     * @return Conversion status.
     */
    public FileResult convert(Param param) throws ConvertException {
        ImageParam imageParam = (ImageParam) param;

        FileResult fileResult;

        String commandString;

        if (imageParam.isThumbnail()) {
            generateImage(imageParam);
            generateThumbnail(imageParam);
        } else {
            generateImage(imageParam);
        }

        ContextStrategy comContext = new ContextStrategy(commandStrategyList);

        try {
            commandString = comContext.buildCommand();

            Runtime.getRuntime().exec(commandString);
            fileResult = new FileResult();
            fileResult.setPath(imageParam.getOutputPathFile());
        } catch (Exception e) {
            throw new ConvertException("The conversion Image failed", "Command image converter");
        }
        return fileResult;
    }

    private void generateImage(ImageParam imageParam) {
        commonCommandImage(imageParam);

        commandStrategyList.add(new CommandImageRotate(imageParam.getDegreesToRotate()));
        commandStrategyList.add(new CommandImageResize(imageParam.getImageWidth(), imageParam.getImageHeight()));
        commandStrategyList.add(new CommandOutputFilePath(imageParam.getOutputPathFile()));
        commandStrategyList.add(new CommandOutputFileName(imageParam.getOutputFileName()));
        commandStrategyList.add(new CommandImageFormat(imageParam.getImageFormat().getImageFormat()));
    }

    private void generateThumbnail(ImageParam imageParam) {
        commonCommandImage(imageParam);

        commandStrategyList.add(new CommandThumbnail(imageParam.isThumbnail()));
        commandStrategyList.add(new CommandOutputFilePath(imageParam.getOutputPathFile()));
        commandStrategyList.add(new CommandOutputFileName(imageParam.getOutputFileName()));
        commandStrategyList.add(new CommandImageFormat(imageParam.getImageFormat().getImageFormat()));
    }

    private void commonCommandImage(ImageParam imageParam) {
        commandStrategyList.add(new CommandImageMagickPath());
        commandStrategyList.add(new CommandImageConverter());
        commandStrategyList.add(new CommandInputFilePath(imageParam.getInputPathFile()));
    }
}
