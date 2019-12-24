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
import com.jalasoft.jfc.model.strategy.CommandImageMagickPath;
import com.jalasoft.jfc.model.strategy.ICommandStrategy;
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
import com.jalasoft.jfc.model.strategy.CommandImageConverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
    public FileResult convert(Param param){

        PdfParam pdfParam = (PdfParam)param;
        FileResult fileResult = new FileResult();

        try {
            List<ICommandStrategy> list = new ArrayList<>();
            list.add(new CommandImageMagickPath());
            list.add(new CommandImageConverter());
            list.add(new CommandInputFilePath(pdfParam.getInputPathFile()));
            list.add(new CommandPagesToConvert(pdfParam.getPagesToConvert()));
            list.add(new CommandImageResize(pdfParam.getWidth(), pdfParam.getHeight()));
            list.add(new CommandScale(pdfParam.getScale()));
            list.add(new CommandThumbnail(pdfParam.getThumbnail()));
            list.add(new CommandImageRotate(pdfParam.getRotate()));
            list.add(new CommandOutputFilePath(pdfParam.getOutputPathFile()));
            list.add(new CommandOutputFileName(pdfParam.getOutputFileName()));
            list.add(new CommandImageFormat(pdfParam.getImageFormat()));

            String stringCommand = getCommand(list);
            Process process = Runtime.getRuntime().exec(stringCommand);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            String line;
            while ((line = bufferedReader.readLine()) != null){

            }
            process.waitFor();
        }
        catch (NullPointerException e)
        {
            throw new NullPointerException();
        }
        finally {
            return fileResult;
        }
    }

    /**
     * This method is for getting the string command.
     * @param commandList
     * @return command concatenated.
     * @throws CommandValueException
     */
    public String getCommand(List<ICommandStrategy> commandList) throws CommandValueException {
        ContextStrategy contextStrategy = new ContextStrategy(commandList);
        String result = contextStrategy.buildCommand();
        return result;
    }
}
