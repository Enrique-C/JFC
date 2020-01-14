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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.FileInputStream;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= Main.class)
public class AudioConverterControllerTest {

    private MockMvc mockMvc;
    @Autowired
    WebApplicationContext wContext;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wContext).alwaysDo(MockMvcResultHandlers.print()).build();
    }

    @Test
    public void audioConverter() throws Exception {
        String srcFilePath = "src/test/resources/audio.wav";
        File filePath = new File(srcFilePath);
        FileInputStream input = new FileInputStream(filePath);

        MockMultipartFile file = new MockMultipartFile("file", filePath.getName(),
                MediaType.APPLICATION_OCTET_STREAM_VALUE, IOUtils.toByteArray(input));


        /*MockMultipartFile file = new MockMultipartFile("file", filePath.getName(),
                "text/plain", IOUtils.toByteArray(input));

         */

        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/api/audioConverter/").file(file)
                .param("md5", "882559480156e9cddf65ed3125521b9922"))
                .andExpect(status().isUnauthorized());
    }
}