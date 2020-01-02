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
import com.jalasoft.jfc.model.exception.ConvertException;
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

    // List of commands.
    List<ICommandStrategy> commandsList;

    // Variable of CommandStrategy.
    ContextStrategy contextStrategy;
    /**
     * This method converts a PDF to Image.
     * @param param
     * @return FileResult object or null value.
     */
    public FileResult convert(Param param) throws CommandValueException, ConvertException {
        FileResult fileResult = new FileResult();
        PdfParam pdfParam = (PdfParam)param;
        StringBuilder stringCommand = new StringBuilder();
        if (!pdfParam.isThumbnail() && !pdfParam.isMetadata()) {
            stringCommand.append(generateImage(pdfParam));
            runCommand(stringCommand.toString());
        }
        if (pdfParam.isThumbnail()) {
            stringCommand = new StringBuilder();
            stringCommand.append(generateImage(pdfParam));
            runCommand(stringCommand.toString());
            stringCommand = new StringBuilder();
            stringCommand.append(generateThumbnail(pdfParam));
            runCommand(stringCommand.toString());
        }
        if (pdfParam.isMetadata()) {
            stringCommand = new StringBuilder();
            stringCommand.append(generateImage(pdfParam));
            runCommand(stringCommand.toString());
            generateMetadata(pdfParam);
        }
        System.out.println(stringCommand);
        return fileResult;
    }

    /**
     * This method is for generate image string command.
     * @param pdfParam
     * @return command concatenated.
     * @throws CommandValueException
     */
    private String generateImage(PdfParam pdfParam) throws CommandValueException {
        commandsList = new ArrayList<>();
        commandsList.add(new CommandImageMagickPath());
        commandsList.add(new CommandImageConverter());
        commandsList.add(new CommandImageDensity());
        commandsList.add(new CommandImageAlpha());
        commandsList.add(new CommandImageBackground());
        commandsList.add(new CommandInputFilePath(pdfParam.getInputPathFile()));
        commandsList.add(new CommandPagesToConvert(pdfParam.getPagesToConvert(), pdfParam.getQuantityOfPage()));
        commandsList.add(new CommandImageResize(pdfParam.getWidth(), pdfParam.getHeight()));
        commandsList.add(new CommandScale(pdfParam.getScale()));
        commandsList.add(new CommandImageRotate(pdfParam.getRotate()));
        commandsList.add(new CommandOutputFilePath(pdfParam.getOutputPathFile(), pdfParam.getInputFileName()));
        commandsList.add(new CommandOutputFileName(pdfParam.getOutputFileName(), pdfParam.getInputFileName()));
        commandsList.add(new CommandImageFormat(pdfParam.getImageFormat()));
        contextStrategy = new ContextStrategy(commandsList);
        String result = contextStrategy.buildCommand();
        return result;
    }

    /**
     * This method is for generate thumbnail string command.
     * @param pdfParam
     * @return command concatenated.
     * @throws CommandValueException
     */
    private String generateThumbnail(PdfParam pdfParam) throws CommandValueException {
        commandsList = new ArrayList<>();
        commandsList.add(new CommandImageMagickPath());
        commandsList.add(new CommandImageConverter());
        commandsList.add(new CommandImageDensity());
        commandsList.add(new CommandImageAlpha());
        commandsList.add(new CommandImageBackground());
        commandsList.add(new CommandInputFilePath(pdfParam.getInputPathFile()));
        commandsList.add(new CommandPagesToConvert(pdfParam.getPagesToConvert(), pdfParam.getQuantityOfPage()));
        commandsList.add(new CommandThumbnail(pdfParam.isThumbnail()));
        commandsList.add(new CommandOutputFilePath(pdfParam.getOutputPathFile(), pdfParam.getInputFileName()));
        commandsList.add(new CommandOutputFileName(pdfParam.getOutputFileName() + "_t",
                pdfParam.getInputFileName() + "_t"));
        commandsList.add(new CommandImageFormat(pdfParam.getImageFormat()));
        contextStrategy = new ContextStrategy(commandsList);
        String result = contextStrategy.buildCommand();
        return result;
    }

    private void generateMetadata(PdfParam pdfParam) {
        commandsList = new ArrayList<>();
        commandsList.add(new CommandInputFilePath(pdfParam.getInputPathFile()));
        // execute XMP or use XMP.
    }

    /**
     * Runs string command.
     * @param stringCommand value of command.
     * @return 0 when the process was executed successfully.
     */
    private void runCommand(String stringCommand) throws ConvertException {
        try {
            Process process = Runtime.getRuntime().exec(stringCommand);
            process.waitFor();
        } catch (InterruptedException | IOException e) {
            throw new ConvertException(e.getMessage(), this.getClass().getName());
        }
    }
}
