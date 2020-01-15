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
 * Tests metadata converter controller.
 *
 * @version 0.1 08 Jan 2020.
 *
 * @author Juan Martinez.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes= Main.class)
public class MetadataConverterControllerTest {

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
    public void metadataConverter_uploadMultipartFile_getItsMetadata() throws Exception {
        final int EMPTY_BYTES = 22;
        String srcFilePath = "src/test/resources/pdf.pdf";
        File filePath = new File(srcFilePath);
        FileInputStream input = new FileInputStream(filePath);

        String expectedFileName = "8bd6509aba6eafe623392995b08c7047.zip";
        MockMultipartFile file = new MockMultipartFile("file", filePath.getName(),
                MediaType.APPLICATION_PDF_VALUE, IOUtils.toByteArray(input));

        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/api/v1/metadataConverter/").file(file)
        .param("md5","8bd6509aba6eafe623392995b08c7047").characterEncoding("UTF-8"))
        .andExpect(status().is(201)).andReturn();

        File zipFile = new File(PathJfc.getPublicFilePath() + "/" + expectedFileName);
        boolean expected = zipFile.exists() && zipFile.getTotalSpace() > EMPTY_BYTES;
        FolderRemover.removeFolder(zipFile.getPath());

        assertTrue(expected);
    }

    @Test
    public void metadataConverter_nullMultipartFileNameAndMd5ValueEmpty_notFound() throws Exception {
        String srcFilePath = "src/test/resources/pdf.pdf";
        File filePath = new File(srcFilePath);
        FileInputStream input = new FileInputStream(filePath);

        MockMultipartFile file = new MockMultipartFile("file", null,
        MediaType.APPLICATION_PDF_VALUE, IOUtils.toByteArray(input));

        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/api/v1/metadataConverter/").file(file)
        .param("md5","  ").characterEncoding("UTF-8"))
        .andExpect(status().is(404)).andReturn();
    }

    @Test
    public void metadataConverter_emptyMultipartFileNameAndMd5ValueEmpty_notFound() throws Exception {
        String srcFilePath = "src/test/resources/pdf.pdf";
        File filePath = new File(srcFilePath);
        FileInputStream input = new FileInputStream(filePath);

        MockMultipartFile file = new MockMultipartFile("file", " ",
                MediaType.IMAGE_JPEG_VALUE, IOUtils.toByteArray(input));

        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/api/v1/metadataConverter/").file(file)
        .param("md5","  ").characterEncoding("UTF-8"))
        .andExpect(status().is(404)).andReturn();
    }

    @Test
    public void metadataConverter_Md5ValueEmpty_notAcceptable() throws Exception {
        String srcFilePath = "src/test/resources/pdf.pdf";
        File filePath = new File(srcFilePath);
        FileInputStream input = new FileInputStream(filePath);

        MockMultipartFile file = new MockMultipartFile("file", "pdf.pdf",
                MediaType.IMAGE_JPEG_VALUE, IOUtils.toByteArray(input));

        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/api/v1/metadataConverter/").file(file)
                .param("md5","  ").characterEncoding("UTF-8"))
                .andExpect(status().isNotAcceptable());
    }
}
