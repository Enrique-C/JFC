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
import com.jalasoft.jfc.model.exception.ConvertException;
import com.jalasoft.jfc.model.strategy.ContextStrategy;
import com.jalasoft.jfc.model.strategy.ICommandStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * This class converts PDF to Image
 *
 * @version 0.1 13 Dic 2019
 *
 * @author Alan Escalera
 */
public class PdfConverter implements IConverter {

    /**
     * This method convert a PDF to Image.
     * @param param
     * @return FileResult object or null value.
     * @throws IOException
     */
    public FileResult convert(Param param){

        PdfParam pdfParam = (PdfParam)param;
        FileResult fileResult = new FileResult();
        String space = " ";

        try {
            StringBuilder command = new StringBuilder();
            List<ICommandStrategy> list = new ArrayList<>();


            if (pdfParam.getMagick().equals(null)){
                throw new ConvertException("Aqui el nuevo mensaje","Aqui dondeen que lugar se genera");
            }

            command.append(pdfParam.getMagick());

            if (pdfParam.getInputPathFile() == null || pdfParam.getOutputPathFile()
                    == null || pdfParam.getImageFormat() == null) {
                throw new ConvertException("Aqui el nuevo mensaje","Aqui dondeen que lugar se genera");
            }

            command.append(space);
            command.append(ImageMagickCommand.CONVERT.getCommand());
            command.append(space);
            command.append(pdfParam.getInputPathFile());

            if (pdfParam.getPagesToConvert() != null){
                final Pattern pattern = Pattern.compile("[0-9][-][0-9]\\d*$");
                if (!pattern.matcher(pdfParam.getPagesToConvert()).matches()){
                    throw new ConvertException("Aqui el nuevo mensaje","Aqui dondeen que lugar se genera");
                }
                command.append(ImageMagickCommand.OPEN_BRACKET.getCommand());
                command.append(pdfParam.getPagesToConvert());
                command.append(ImageMagickCommand.CLOSE_BRACKET.getCommand());
            }

            if (pdfParam.getWidth() > 0 && pdfParam.getHeight() > 0){
                command.append(space);
                command.append(ImageMagickCommand.RESIZE.getCommand());
                command.append(space);
                command.append(pdfParam.getWidth());
                command.append(ImageMagickCommand.ASTERISK.getCommand());
                command.append(pdfParam.getHeight());
            }

            if (pdfParam.getScale() != null){
                command.append(space);
                command.append(ImageMagickCommand.SCALE.getCommand());
                command.append(space);
                command.append(pdfParam.getScale());
            }

            if (pdfParam.getThumbnail() != null){
                command.append(space);
                command.append(ImageMagickCommand.THUMBNAIL.getCommand());
                command.append(space);
                command.append(pdfParam.getThumbnail());
            }

            if (pdfParam.getRotate() > 0){
                command.append(space);
                command.append(ImageMagickCommand.ROTATE.getCommand());
                command.append(space);
                command.append(pdfParam.getRotate());
            }

            command.append(space);
            command.append(pdfParam.getOutputPathFile());
            command.append(pdfParam.getOutputFileName());
            command.append(pdfParam.getImageFormat());

            String stringCommand = command.toString();
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

    public String getCommand(List<ICommandStrategy> commandList){
        ContextStrategy contextStrategy = new ContextStrategy(commandList);
        String result = contextStrategy.buildCommand();
        return result;
    }
}
