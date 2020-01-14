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
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.FileInputStream;

/**
 * Tests md5 extractor controller.
 *
 * @version 0.1 08 Jan 2020.
 *
 * @author Juan Martinez.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes= Main.class)
public class Md5ExtractorControllerTest {

    private MockMvc mockMvc;
    @Autowired
    WebApplicationContext wContext;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wContext).alwaysDo(MockMvcResultHandlers.print()).build();
    }

    @Test
    public void extractMd5_GetsMd5OfFileUploaded_md5String() throws Exception {
        String srcFilePath = "src/test/resources/pdf.pdf";
        File filePath = new File(srcFilePath);
        FileInputStream input = new FileInputStream(filePath);

        String expected = "8bd6509aba6eafe623392995b08c7047";

        MockMultipartFile file = new MockMultipartFile("file", filePath.getName(),
        MediaType.APPLICATION_PDF_VALUE, IOUtils.toByteArray(input));

        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/api/extractMd5/").file(file)
        .characterEncoding("UTF-8")).andExpect(MockMvcResultMatchers.content().string(expected));
    }

    @Test
    public void extractMd5_WhenNullMultipartFileNameIsUploaded_BadRequest() throws Exception {
        String srcFilePath = "src/test/resources/pdf.pdf";
        File filePath = new File(srcFilePath);
        FileInputStream input = new FileInputStream(filePath);

        ResultMatcher badRequest = MockMvcResultMatchers.status().isBadRequest();
        MockMultipartFile file = new MockMultipartFile("file", null,
        MediaType.APPLICATION_PDF_VALUE, IOUtils.toByteArray(input));

        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/api/extractMd5/").file(file)
        .characterEncoding("UTF-8")).andExpect(badRequest);
    }
}
