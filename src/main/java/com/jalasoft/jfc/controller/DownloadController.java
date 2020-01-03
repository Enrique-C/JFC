/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jalasoft.jfc.model.FileResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * This class allows to download zip file.
 *
 * @version 0.1 02 Jan 2020
 *
 * @author Juan Martinez
 */

@RestController
public class DownloadController {

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

        String fileBasePath = "C:/Users/Admin/IdeaProjects/JFC/files/input";
        MediaType mediaType;
        String mineType = servletContext.getMimeType(fileName);

        try {
            mediaType = MediaType.parseMediaType(mineType);
        } catch (Exception e) {
            throw new Exception("APPLICATION_OCTET_STREAM");
        }

        File file = new File(fileBasePath + "/" + fileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentType(mediaType)
                .contentLength(file.length())
                .body(resource);
    }

    /**
     * Alloows to generate link for downloading zip file.
     * @param file path.
     * @return ResponseEntity.
     */
    @GetMapping("/generateLink")
    public ResponseEntity generateLink(@RequestParam("file") MultipartFile file) throws IOException {

        String fileBasePath = "C:/Users/Admin/IdeaProjects/JFC/files/input/";
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path path = Paths.get(fileBasePath + fileName);

        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(fileName)
                .toUriString();
        return ResponseEntity.ok(fileDownloadUri);
    }
}
