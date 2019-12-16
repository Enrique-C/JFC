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
 *
 * This class is used to convert PDF to Image
 *
 * @version 1.0 13 Dic 2019
 *
 * @author Alan Escalera
 */
public class PdfConverter {

    /**
     * This method convert a PDF to Image JPEG, PNG, GIF, BMP and WBMP.
     * @param pdf
     * @return boolean
     * @throws IOException
     */
    public Boolean convert(PdfParam pdf){

        Boolean isConverted = false;
        try {
            if (pdf.getPathInput() == null || pdf.getPathOuput() == null) {
                throw new IllegalArgumentException();
            }
            PDDocument documenttoimage = PDDocument.load(
                    new File(pdf.getPathInput()));
            PDDocument documentRotated = new PDDocument();
            PDFRenderer renderer;
            BufferedImage image;
            String pathName;

            boolean rotated = false;
            int pages = documenttoimage.getNumberOfPages();
            // Just rotate 90, 180, 270 degrees
            if (pdf.getRotate() > 0) {
                for (int i = 0; i < pages; i++) {
                    PDPage pageToRotate = documenttoimage.getPage(i);
                    pageToRotate.setRotation(pdf.getRotate());
                    documentRotated.addPage(pageToRotate);
                }
                renderer = new PDFRenderer(documentRotated);
                rotated = true;
            } else {
                renderer = new PDFRenderer(documenttoimage);
            }
            for (int i = 0; i < pages; i++) {
                pathName = pdf.getPathOuput().toString() + pdf.getNameFile() +
                        i + "." + pdf.getFormatImage().toString();
                if (pdf.getDpi() != 100) {
                    image = renderer.renderImageWithDPI(i, pdf.getDpi(), pdf.getImageType());
                } else {
                    image = renderer.renderImage(i, pdf.getScale(), pdf.getImageType());
                }
                ImageIO.write(image, pdf.getFormatImage().toString(), new File(pathName));
            }
            if(rotated)
                documentRotated.close();
            documenttoimage.close();
            isConverted = true;
        }
        catch (IllegalArgumentException e)
        {
            throw new IllegalArgumentException();
        }
        finally {
            return isConverted;
        }
    }
}

