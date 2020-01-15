/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.controller;

import com.jalasoft.jfc.Main;
import com.jalasoft.jfc.model.utility.FolderRemover;
import com.jalasoft.jfc.model.utility.PathJfc;
import org.apache.pdfbox.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.FileInputStream;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Used for testing PdfConverterController.
 *
 * @version 0.1 07 Jan 2020.
 *
 * @author Juan Martinez.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes= Main.class)
public class PdfConverterControllerTest {

    // mockMvc variable.
    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext wContext;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wContext).alwaysDo(MockMvcResultHandlers.print()).build();
        PathJfc pathJfc = new PathJfc();
    }

    @Test
    public void pdfConverter_uploadPdfFileAndItsSpecifications_returnCreated201() throws Exception {
        final int EMPTY_BYTES = 22;
        String srcFilePath = "src/test/resources/pdf.pdf";
        File filePath = new File(srcFilePath);
        FileInputStream input = new FileInputStream(filePath);

        String expectedFileName = "8bd6509aba6eafe623392995b08c7047.zip";
        MockMultipartFile file = new MockMultipartFile("file", filePath.getName(),
                MediaType.APPLICATION_PDF_VALUE, IOUtils.toByteArray(input));

        String md5Value = "8bd6509aba6eafe623392995b08c7047";
        String outputNameValue = "pdfTest";
        int rotateValue = 0;
        String scaleValue = " ";
        boolean isThumbnailValue = true;
        boolean isMetadataValue = true;
        String formatValue = ".png";
        int widthValue = 0;
        int heightValue = 0;
        String pagesToConvertValue = "1, 2";

        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/api/v1/pdfConverter/").file(file)
        .param("md5",md5Value).param("outputName", outputNameValue).characterEncoding("UTF-8"))
        .andExpect(status().is(201)).andReturn();

        File zipFile = new File(PathJfc.getPublicFilePath() + "/" + expectedFileName);
        boolean expected = zipFile.exists() && zipFile.getTotalSpace() > EMPTY_BYTES;
        FolderRemover.removeFolder(zipFile.getPath());

        assertTrue(expected);
    }
}
