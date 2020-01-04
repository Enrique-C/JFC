/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.controller;

import com.jalasoft.jfc.model.utility.PathJfc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;

import java.io.File;
import java.io.FileInputStream;

/**
 * This class allows to download zip file.
 *
 * @version 0.1 02 Jan 2020
 *
 * @author Juan Martinez
 */
@RestController
public class DownloadController {

    /**
     * It is servletContext variable.
     */
    @Autowired
    private ServletContext servletContext;

    /**
     * It allows to download zip file.
     * @param fileName value.
     * @return ResponseEntity.
     * @throws Exception
     */
    @GetMapping(path = "/download/{fileName:.+}")
    public ResponseEntity download(@PathVariable String fileName) throws Exception {
        MediaType mediaType;
        String mineType = servletContext.getMimeType(fileName);

        try {
            mediaType = MediaType.parseMediaType(mineType);
            File file = new File(PathJfc.getPublicFilePath() + fileName);
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                    .contentType(mediaType)
                    .contentLength(file.length())
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.badRequest().header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment;filename=" + fileName).body(e.getCause());
        }


    }
}
