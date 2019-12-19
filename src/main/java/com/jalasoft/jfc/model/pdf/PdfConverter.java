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
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
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

            if (pdfParam.getMagick().equals(null)){
                throw new NullPointerException();
            }

            command.append(pdfParam.getMagick());

            if (pdfParam.getInputPathFile() == null || pdfParam.getOutputPathFile()
                    == null || pdfParam.getImageFormat() == null) {
                throw new NullPointerException();
            }

            command.append(space);
            command.append(PdfCommand.CONVERT.getCommand());
            command.append(space);
            command.append(pdfParam.getInputPathFile());

            if (pdfParam.getPagesToConvert() != null){
                final Pattern pattern = Pattern.compile("[0-9][-][0-9]\\d*$");
                if (!pattern.matcher(pdfParam.getPagesToConvert()).matches()){
                    throw new IllegalArgumentException("Invalid value");
                }
                command.append(PdfCommand.OPEN_BRACKET.getCommand());
                command.append(pdfParam.getPagesToConvert());
                command.append(PdfCommand.CLOSE_BRACKET.getCommand());
            }

            if (pdfParam.getWidth() > 0 && pdfParam.getHeight() > 0){
                command.append(space);
                command.append(PdfCommand.RESIZE.getCommand());
                command.append(space);
                command.append(pdfParam.getWidth());
                command.append(PdfCommand.ASTERISK.getCommand());
                command.append(pdfParam.getHeight());
            }

            if (pdfParam.getScale() != null){
                command.append(space);
                command.append(PdfCommand.SCALE.getCommand());
                command.append(space);
                command.append(pdfParam.getScale());
            }

            if (pdfParam.getThumbnail() != null){
                command.append(space);
                command.append(PdfCommand.THUMBNAIL.getCommand());
                command.append(space);
                command.append(pdfParam.getThumbnail());
            }

            if (pdfParam.getRotate() > 0){
                command.append(space);
                command.append(PdfCommand.ROTATE.getCommand());
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
}
