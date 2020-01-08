/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.controller;

import com.jalasoft.jfc.model.Param;
import com.jalasoft.jfc.model.exception.ConvertException;
import com.jalasoft.jfc.model.exception.Md5Exception;
import com.jalasoft.jfc.model.exception.ZipJfcException;
import com.jalasoft.jfc.model.metadata.MetadataConverter;
import com.jalasoft.jfc.model.result.FileResponse;
import com.jalasoft.jfc.model.result.MessageResponse;
import com.jalasoft.jfc.model.result.Response;
import com.jalasoft.jfc.model.utility.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Extracts md5 of a file.
 *
 * @version 0.1 07 Jan 2020.
 *
 * @author Juan Martinez.
 */

@RestController
public class MetadataConverterController {
    /**
     * Generates metadata from multipart file.
     * @param file is multipart value.
     * @return Response
     * @throws Md5Exception
     */
    @PostMapping("/metadataConverter")
    public Response metadataConverter(@RequestParam("file") MultipartFile file, HttpServletRequest request)
            throws Md5Exception {
        FileResponse fileResponse = new FileResponse();
        PathJfc pathJfc = new PathJfc();

        try {
            String fileUploaded = FileServiceController.writeFile(pathJfc.getInputFilePath() +
            file.getOriginalFilename(), file);
            Param param = new Param();
            param.setInputPathFile(fileUploaded);
            param.setOutputPathFile(PathJfc.getOutputFilePath());
            param.setFolderName(Md5Checksum.getMd5(fileUploaded));

            MetadataConverter metadataConverter = new MetadataConverter();
            fileResponse = metadataConverter.convert(param);

            String zipFilePath = zipFile(param);
            FolderRemover.removeFolder(param.getOutputPathFile() + param.getFolderName());

            LinkGenerator linkGenerator = new LinkGenerator();
            fileResponse.setDownload(linkGenerator.linkGenerator(zipFilePath, request));
            fileResponse.setName(param.getFolderName());
            fileResponse.setStatus(MessageResponse.SUCCESS200.getMessageResponse());
            return fileResponse;
        } catch (IOException | ConvertException | ZipJfcException ioe) {
            throw new Md5Exception(ioe.getMessage(), this.getClass().getName());
        }
    }
    private String zipFile(Param param) throws ZipJfcException {

        // Assigns the zip's Path.
        String zipPath;

        // Constant slash variable.
        final String SLASH = "/";

        // Constant zip extension variable.
        final String ZIP_TAG = ".zip";

        ZipFolder zipFolder = new ZipFolder();

        File [] files = new File(param.getOutputPathFile() + SLASH + param.getFolderName() +
        SLASH).listFiles();

        File fileZip = new File(PathJfc.getPublicFilePath() + param.getFolderName() + ZIP_TAG);
        zipPath = fileZip.getAbsolutePath();

        zipFolder.zipFolderFile(files, fileZip);
        return zipPath;
    }
}
