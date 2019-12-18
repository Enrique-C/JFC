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

/**
 * This class is used to convert PDF to Image
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
                command.append(pdfParam.getPagesToConvert());
            }
            command.append(space);
            command.append(pdfParam.getOutputPathFile());
            command.append(pdfParam.getOutputFileName());
            command.append(pdfParam.getImageFormat());

            String stringCommand = command.toString();
            Process process = Runtime.getRuntime().exec(stringCommand);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            // Line variable.
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
