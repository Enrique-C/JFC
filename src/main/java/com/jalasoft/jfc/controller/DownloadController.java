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
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
//
//import org.apache.commons.fileupload.FileItem;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.IOUtils;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

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
        } catch (Exception e) {
            throw new Exception("APPLICATION_OCTET_STREAM");
        }

        File file = new File(PathJfc.getPublicFilePath() + fileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentType(mediaType)
                .contentLength(file.length())
                .body(resource);
    }
}
