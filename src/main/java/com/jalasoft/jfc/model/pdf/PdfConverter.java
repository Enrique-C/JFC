/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */
package com.jalasoft.jfc.model.pdf;

import com.jalasoft.jfc.model.FileResult;
import com.jalasoft.jfc.model.IConverter;
import com.jalasoft.jfc.model.Param;
import com.jalasoft.jfc.model.exception.CommandValueException;
import com.jalasoft.jfc.model.strategy.ICommandStrategy;
import com.jalasoft.jfc.model.strategy.CommandImageMagickPath;
import com.jalasoft.jfc.model.strategy.CommandImageConverter;
import com.jalasoft.jfc.model.strategy.CommandImageDensity;
import com.jalasoft.jfc.model.strategy.CommandImageAlpha;
import com.jalasoft.jfc.model.strategy.CommandImageBackground;
import com.jalasoft.jfc.model.strategy.CommandInputFilePath;
import com.jalasoft.jfc.model.strategy.CommandPagesToConvert;
import com.jalasoft.jfc.model.strategy.CommandImageResize;
import com.jalasoft.jfc.model.strategy.CommandScale;
import com.jalasoft.jfc.model.strategy.CommandThumbnail;
import com.jalasoft.jfc.model.strategy.CommandImageRotate;
import com.jalasoft.jfc.model.strategy.CommandOutputFilePath;
import com.jalasoft.jfc.model.strategy.CommandOutputFileName;
import com.jalasoft.jfc.model.strategy.CommandImageFormat;
import com.jalasoft.jfc.model.strategy.ContextStrategy;


import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

/**
 * This class converts PDF to Image
 *
 * @version 0.1 13 Dic 2019
 *
 * @author Alan Escalera
 */
public class PdfConverter implements IConverter {

    /**
     * This method converts a PDF to Image.
     * @param param
     * @return FileResult object or null value.
     */
    public FileResult convert(Param param) throws CommandValueException, IOException {
        FileResult fileResult = new FileResult();
        String stringCommand = getCommand(param);
        System.out.println(stringCommand);
        int value = runCommand(stringCommand);
        return fileResult;
    }

    /**
     * This method is for getting the string command.
     * @param param
     * @return command concatenated.
     * @throws CommandValueException
     */
    public String getCommand(Param param) throws CommandValueException, IOException {
        try {
            PdfParam pdfParam = (PdfParam)param;
            List<ICommandStrategy> list = new ArrayList<>();
            list.add(new CommandImageMagickPath());
            list.add(new CommandImageConverter());
            list.add(new CommandImageDensity());
            list.add(new CommandImageAlpha());
            list.add(new CommandImageBackground());
            list.add(new CommandInputFilePath(pdfParam.getInputPathFile()));
            list.add(new CommandPagesToConvert(pdfParam.getPagesToConvert()));
            list.add(new CommandImageResize(pdfParam.getWidth(), pdfParam.getHeight()));
            list.add(new CommandScale(pdfParam.getScale()));
            list.add(new CommandThumbnail(pdfParam.getThumbnail()));
            list.add(new CommandImageRotate(pdfParam.getRotate()));
            list.add(new CommandOutputFilePath(pdfParam.getOutputPathFile()));
            list.add(new CommandOutputFileName(pdfParam.getOutputFileName()));
            list.add(new CommandImageFormat(pdfParam.getImageFormat()));
            ContextStrategy contextStrategy = new ContextStrategy(list);
            String result = contextStrategy.buildCommand();
            return result;
        } catch (CommandValueException cve) {
            throw new CommandValueException(cve.getMessage(), this.getClass().getName());
        }
    }

    /**
     * Runs string command.
     * @param stringCommand value of command.
     * @return 0 when the process was executed successfully.
     */
    private int runCommand(String stringCommand){
        int returnValue = -1;
        try {
            Process process = Runtime.getRuntime().exec(stringCommand);
            process.waitFor();
            returnValue = process.exitValue();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return returnValue;
    }
}
