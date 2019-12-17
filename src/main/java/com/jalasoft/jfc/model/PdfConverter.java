/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 *  ("Confidential Information"). You shall not disclose such Confidential
 *  Information and shall use it only in accordance with the terms of the
 *  license agreement you entered into with Jalasoft.
 */
package com.jalasoft.jfc.model;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This class is used to convert PDF to Image
 *
 * @version 0.1 13 Dic 2019
 *
 * @author Alan Escalera
 */
public class PdfConverter {

    /**
     * This method convert a PDF to Image JPEG, PNG, GIF, BMP and WBMP.
     * @param pdf
     * @return boolean value.
     * @throws IOException
     */
    public Boolean convert(PdfParam pdf){

        final int DPI_BY_DEFECT = 100;
        final int INIT_VALUE = 0;
        Boolean isConverted = false;

        try {
            if (pdf.getInputPathFile() == null || pdf.getOutputPathFile() == null) {
                throw new IOException();
            }
            PDDocument documentToImage = PDDocument.load(new File(pdf.getInputPathFile()));
            PDDocument documentRotated = new PDDocument();
            PDFRenderer renderer;
            BufferedImage image;
            String pathName;

            boolean rotated = false;
            int totalPages = documentToImage.getNumberOfPages();

            // Just rotate 90, 180, 270 degrees.
            if (pdf.getRotate() > INIT_VALUE) {
                for (int page = INIT_VALUE; page < totalPages; page++) {
                    PDPage pageToRotate = documentToImage.getPage(page);
                    pageToRotate.setRotation(pdf.getRotate());
                    documentRotated.addPage(pageToRotate);
                }
                renderer = new PDFRenderer(documentRotated);
                rotated = true;
            } else {
                renderer = new PDFRenderer(documentToImage);
            }
            for (int page = INIT_VALUE; page < totalPages; page++) {
                pathName = pdf.getOutputPathFile() + pdf.getOutputFileName() +
                        page + "." + pdf.getPdfFormatImage().toString();
                if (pdf.getDpi() != DPI_BY_DEFECT) {
                    image = renderer.renderImageWithDPI(page, pdf.getDpi(), pdf.getImageType());
                } else {
                    image = renderer.renderImage(page, pdf.getScale(), pdf.getImageType());
                }
                ImageIO.write(image, pdf.getPdfFormatImage().toString(), new File(pathName));
            }
            if(rotated)
                documentRotated.close();
            documentToImage.close();
            isConverted = true;
        }
        catch (IOException e)
        {
            throw new IOException();
        }
        finally {
            return isConverted;
        }
    }
}
