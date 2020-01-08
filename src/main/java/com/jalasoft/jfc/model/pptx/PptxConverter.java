/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.pptx;

import com.jalasoft.jfc.model.Param;
import com.jalasoft.jfc.model.command.ContextStrategy;
import com.jalasoft.jfc.model.command.ICommandStrategy;
import com.jalasoft.jfc.model.command.imagick.CommandImageAlpha;
import com.jalasoft.jfc.model.command.imagick.CommandImageBackground;
import com.jalasoft.jfc.model.command.imagick.CommandImageConverter;
import com.jalasoft.jfc.model.command.imagick.CommandImageDensity;
import com.jalasoft.jfc.model.command.imagick.CommandImageMagickPath;
import com.jalasoft.jfc.model.exception.CommandValueException;
import com.jalasoft.jfc.model.exception.ConvertException;
import com.jalasoft.jfc.model.exception.ZipJfcException;
import com.jalasoft.jfc.model.result.FileResponse;
import com.jalasoft.jfc.model.result.MessageResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Converts pptx to pdf.
 *
 * @version 0.1 07 Jan 2020.
 *
 * @author Alan Escalera.
 */
public class PptxConverter {

    // List of commands.
    List<ICommandStrategy> commandsList;

    // Variable of CommandStrategy.
    ContextStrategy contextStrategy;

    // Assigns the zip's Path.
    private String zipPath;

    public FileResponse convert(Param param) throws CommandValueException, ConvertException, ZipJfcException{
        if (param == null) {
            throw new ConvertException("Parameter param is null", this.getClass().getName());
        }

        FileResponse fileResponse = new FileResponse();

        StringBuilder stringCommand = new StringBuilder();
        stringCommand.append(generatePdf(param));
        runCommand(stringCommand.toString());

        fileResponse.setName(param.getOutputName());
        fileResponse.setStatus(MessageResponse.SUCCESS200.getMessageResponse());
        fileResponse.setDownload(zipPath);
        return fileResponse;
    }
    private String generatePdf (Param param) throws CommandValueException {
        commandsList = new ArrayList<>();
        commandsList.add(new CommandImageMagickPath());
        commandsList.add(new CommandImageConverter());
        commandsList.add(new CommandImageDensity());
        commandsList.add(new CommandImageAlpha());
        commandsList.add(new CommandImageBackground());
        contextStrategy = new ContextStrategy(commandsList);
        String result = contextStrategy.buildCommand();
        return result;
    }
    /**
     * Runs string command.
     * @param stringCommand value of command.
     * @return 0 when the process was executed successfully.
     * @throws ConvertException when the conversion was not completed.
     */
    private void runCommand (String stringCommand) throws ConvertException {
        try {
            Process process = Runtime.getRuntime().exec(stringCommand);
            process.waitFor();
        } catch (InterruptedException | IOException e) {
            throw new ConvertException(e.getMessage(), this.getClass().getName());
        }
    }
}
