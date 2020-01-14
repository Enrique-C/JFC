/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.controller;

import com.jalasoft.jfc.model.entity.FileEntity;
import com.jalasoft.jfc.model.exception.Md5Exception;
import com.jalasoft.jfc.model.repository.FileRepository;
import com.jalasoft.jfc.model.result.MessageResponse;
import com.jalasoft.jfc.model.result.ErrorResponse;
import com.jalasoft.jfc.model.result.FileResponse;
import com.jalasoft.jfc.model.IConverter;
import com.jalasoft.jfc.model.result.Response;
import com.jalasoft.jfc.model.utility.FileServiceController;
import com.jalasoft.jfc.model.utility.LinkGenerator;
import com.jalasoft.jfc.model.utility.Md5Checksum;
import com.jalasoft.jfc.model.exception.CommandValueException;
import com.jalasoft.jfc.model.exception.ConvertException;
import com.jalasoft.jfc.model.pdf.PdfConverter;
import com.jalasoft.jfc.model.pdf.PdfParam;
import com.jalasoft.jfc.model.utility.PathJfc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

/**
 * Manage PdfConverter Requests.
 *
 * @author Enrique Carrizales.
 *
 * @version 0.1 14 Dic 2019.
 */
@Api(value = "PdfConverterController", description = "REST API related to PdfParam Entity")
@RestController
@RequestMapping("/api")
public class PdfConverterController {

    // Inject FileRepository.
    @Autowired
    FileRepository fileRepository;

    /**
     * This method receives a PDF to convert.
     * @param file contains the image file
     * @param md5 contains md5 value.
     * @param outputName contains the name of output file.
     * @param rotate degrees to rotate.
     * @param scale contains input Scale 1-10.
     * @param isThumbnail boolean of thumbnail.
     * @param imageFormat format of a image.
     * @param width contains integer value.
     * @param height contains integer value.
     * @param pagesToConvert contains number of pdf file pages.
     * @param request contains client data.
     * @param isMetadata boolean of metadata.
     * @return Response is the result of the conversion.
     */
    @PostMapping("/pdfConverter")
    @ApiOperation(value = "Pdf specifications", notes = "Provides values for converting Pdf file to Image",
            response = Response.class, authorizations = { @Authorization(value="JWT") })
    public ResponseEntity<Response> pdfConverter(
            @RequestParam("file") MultipartFile file, @RequestParam(defaultValue = " ") String md5,
            @RequestParam String outputName, @RequestParam(defaultValue = "0") int rotate,
            @RequestParam(defaultValue = "%") String scale, @RequestParam(defaultValue = "false") boolean isThumbnail,
            @RequestParam(defaultValue = "false") boolean isMetadata, @RequestParam(defaultValue = ".png")
            String imageFormat, @RequestParam(defaultValue = "0") int width, @RequestParam(defaultValue = "0")
            int height, @RequestParam(defaultValue = "") String pagesToConvert, HttpServletRequest request) {

        PdfParam pdfParam = new PdfParam();
        FileResponse fileResponse = new FileResponse();
        ErrorResponse errorResponse = new ErrorResponse();
        IConverter pdfConverter = new PdfConverter();

        try {
            FileEntity fileEntity = new FileEntity();
            String cleanMd5 = md5.trim();

            if (fileRepository.findByMd5(cleanMd5) != null) {
                pdfParam.setInputPathFile(fileRepository.findByMd5(cleanMd5).getFilePath());
            } else {
                String fileUploadedPath = FileServiceController.writeFile(PathJfc.getInputFilePath() + file.
                        getOriginalFilename(), file);
                cleanMd5 = Md5Checksum.getMd5(fileUploadedPath, cleanMd5);
                pdfParam.setInputPathFile(fileUploadedPath);
                fileEntity.setFilePath(fileUploadedPath);
                fileEntity.setMd5(cleanMd5);
                fileRepository.save(fileEntity);
            }

            pdfParam.setMd5(cleanMd5);
            pdfParam.setOutputPathFile(PathJfc.getOutputFilePath());
            pdfParam.setOutputName(FileServiceController.setName(outputName, file));
            pdfParam.setImageFormat(imageFormat);
            pdfParam.setPagesToConvert(pagesToConvert);
            pdfParam.setThumbnail(isThumbnail);
            pdfParam.isMetadata(isMetadata);
            pdfParam.setWidth(width);
            pdfParam.setScale(scale);
            pdfParam.setHeight(height);
            pdfParam.setRotate(rotate);
            pdfParam.setFolderName(cleanMd5);

            fileResponse = pdfConverter.convert(pdfParam);
            LinkGenerator linkGenerator = new LinkGenerator();
            fileResponse.setDownload(linkGenerator.linkGenerator(fileResponse.getDownload(), request));
            fileResponse.setName(pdfParam.getFolderName());
            fileResponse.setStatus(MessageResponse.SUCCESS200.getMessageResponse());

            return new ResponseEntity<>(fileResponse, HttpStatus.OK);
        } catch (ConvertException | Md5Exception ex) {
            errorResponse.setName(pdfParam.getOutputName());
            errorResponse.setStatus(MessageResponse.ERROR406.getMessageResponse());
            errorResponse.setError(ex.toString());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
        } catch (CommandValueException cve) {
            errorResponse.setName(pdfParam.getOutputName());
            errorResponse.setStatus(MessageResponse.ERROR400.getMessageResponse());
            errorResponse.setError(cve.toString());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } catch (IOException ex) {
            errorResponse.setName(pdfParam.getOutputName());
            errorResponse.setStatus(MessageResponse.ERROR404.getMessageResponse());
            errorResponse.setError(ex.toString());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            errorResponse.setName(pdfParam.getOutputName());
            errorResponse.setStatus(MessageResponse.ERROR404.getMessageResponse());
            errorResponse.setError(ex.toString());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }
}
