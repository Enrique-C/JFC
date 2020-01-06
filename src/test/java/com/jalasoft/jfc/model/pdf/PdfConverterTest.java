/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.pdf;

import com.jalasoft.jfc.controller.PdfConverterController;
import com.jalasoft.jfc.model.utility.PathJfc;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Executes pdfConvert's unit tests.
 *
 * @version 0.1 06 Jan 2020.
 *
 * @author Juan Martinez.
 */
public class PdfConverterTest {

    private InputStream is;
    private MockMvc mockMvc;
    private PdfConverterController controller = new PdfConverterController();
    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        is = controller.getClass().getClassLoader().getResourceAsStream("excel.xlsx");
    }

    @Test
    public void convertPdfFileToImage() {
        PdfConverter pdfConverter = new PdfConverter();
        PdfParam pdfParam = new PdfParam();
        pdfParam.setInputPathFile("src/test/resources/pdf.pdf");
//        pdfParam.setOutputPathFile();
    }

    private PdfParam generatePdfParamObject() {
        PathJfc pathJfc = new PathJfc();
        String pathOfUploadedFile = "src/test/resources/pdf.pdf";
        File fileUploaded = new File(pathOfUploadedFile);
        String outputPath = "src/test/resources/";
        String outputName = "fileone";
        String imageFormat = ".jpg";
        String pagesToConvert = "2";

    }
}
