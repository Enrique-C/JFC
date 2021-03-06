/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.controller;

import com.jalasoft.jfc.model.IConverter;
import com.jalasoft.jfc.model.entity.FileEntity;
import com.jalasoft.jfc.model.exception.CommandValueException;
import com.jalasoft.jfc.model.exception.ConvertException;
import com.jalasoft.jfc.model.exception.Md5Exception;
import com.jalasoft.jfc.model.pdf.PdfConverter;
import com.jalasoft.jfc.model.pdf.PdfParam;
import com.jalasoft.jfc.model.pptx.PptxConverter;
import com.jalasoft.jfc.model.pptx.PptxParam;
import com.jalasoft.jfc.model.repository.FileRepository;
import com.jalasoft.jfc.model.result.ErrorResponse;
import com.jalasoft.jfc.model.result.FileResponse;
import com.jalasoft.jfc.model.result.MessageResponse;
import com.jalasoft.jfc.model.result.Response;
import com.jalasoft.jfc.model.utility.*;

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

/**
 * Manages PdfConverter Requests.
 *
 * @version 0.1 09 Jan 2020.
 *
 * @author Alan Escalera.
 */
@Api(value = "PptxConverterController", description = "REST API related to PptxParam Entity")
@RestController
@RequestMapping("/api/v1")
public class PptxConverterController {

    // Inject FileRepository.
    @Autowired
    FileRepository fileRepository;

    /**
     * Receives a Pptx to convert.
     * @param file contains the image file.
     * @param md5 contains md5 value.
     * @param outputName contains the name of output file.
     * @param isThumbnail boolean of thumbnail.
     * @param thumbnailFormat format of a image.
     * @param pagesToConvertThumbNail contains number of pdf file pages.
     * @param request contains client data.
     * @param isMetadata boolean of metadata.
     * @return Response is the result of the conversion.
     */
    @PostMapping("/pptxConverterToPdf")
    @ApiOperation(value = "pptx specifications", notes = "Provides values for converting pptx file to Pdf",
            response = Response.class, authorizations = { @Authorization(value="JWT") })
    public ResponseEntity<Response> pptxConverterToPdf(
            @RequestParam("file") MultipartFile file, @RequestParam(defaultValue = "") String md5,
            @RequestParam(defaultValue = "") String outputName, @RequestParam(defaultValue = "false")
            boolean isThumbnail, @RequestParam(defaultValue = "false") boolean isMetadata, @RequestParam
            (defaultValue = ".jpg") String thumbnailFormat, @RequestParam(defaultValue = "")
            String pagesToConvertThumbNail, HttpServletRequest request) {

        PptxParam pptxParam = new PptxParam();
        final String FILE_FORMAT = ".pdf";
        FileResponse fileResponse;
        ErrorResponse errorResponse = new ErrorResponse();
        IConverter PptxConverter = new PptxConverter();

        try {
            FileEntity fileEntity = new FileEntity();
            String cleanMd5 = md5.trim();

            if (fileRepository.findByMd5(cleanMd5) != null) {
                pptxParam.setInputPathFile(fileRepository.findByMd5(cleanMd5).getFilePath());
            } else {
                String fileUploadedPath = FileServiceController.writeFile(PathJfc.getInputFilePath() + file.
                        getOriginalFilename(), file);
                cleanMd5 = Md5Checksum.getMd5(fileUploadedPath, cleanMd5);
                pptxParam.setInputPathFile(fileUploadedPath);
                fileEntity.setFilePath(fileUploadedPath);
                fileEntity.setMd5(cleanMd5);
                fileRepository.save(fileEntity);
            }

            pptxParam.setFileFormat(FILE_FORMAT);
            pptxParam.setMd5(cleanMd5);
            pptxParam.setOutputPathFile(PathJfc.getOutputFilePath());
            pptxParam.setInputName(FileServiceController.getName(file));
            pptxParam.setOutputName(outputName);
            pptxParam.setThumbnailFormat(thumbnailFormat);
            pptxParam.setPagesToConvertThumbnail(pagesToConvertThumbNail);
            pptxParam.setIsThumbnail(isThumbnail);
            pptxParam.isMetadata(isMetadata);
            pptxParam.setFolderName(cleanMd5);

            fileResponse = PptxConverter.convert(pptxParam);
            LinkGenerator linkGenerator = new LinkGenerator();
            fileResponse.setDownload(linkGenerator.linkGenerator(fileResponse.getDownload(), request));
            fileResponse.setName(pptxParam.getFolderName());
            fileResponse.setStatus(MessageResponse.SUCCESS200.getMessageResponse());

            return new ResponseEntity<>(fileResponse, HttpStatus.CREATED);
        } catch (ConvertException | Md5Exception ex) {
            errorResponse.setName(pptxParam.getOutputName());
            errorResponse.setStatus(MessageResponse.ERROR406.getMessageResponse());
            errorResponse.setError(ex.toString());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
        } catch (CommandValueException cve) {
            errorResponse.setName(pptxParam.getOutputName());
            errorResponse.setStatus(MessageResponse.ERROR400.getMessageResponse());
            errorResponse.setError(cve.toString());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            errorResponse.setName(pptxParam.getOutputName());
            errorResponse.setStatus(MessageResponse.ERROR404.getMessageResponse());
            errorResponse.setError(ex.toString());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        } finally {
            FolderRemover.removeFolder(pptxParam.getOutputPathFile() + pptxParam.getFolderName());
        }
    }

    /**
     *
     * @param file contains the image file.
     * @param md5 contains md5 value.
     * @param outputName contains the name of output file.
     * @param rotate degrees to rotate.
     * @param scale contains input Scale 1-10.
     * @param isThumbnail boolean of thumbnail.
     * @param isMetadata isMetadata boolean of metadata.
     * @param imageFormat format of a image.
     * @param width contains integer value.
     * @param height contains integer value.
     * @param pagesToConvert contains number of pdf file pages.
     * @param request contains client data.
     * @param isMetadata boolean of metadata.
     * @return Response is the result of the conversion.
     */
    @PostMapping("/pptxConverterToImage")
    @ApiOperation(value = "pptx specifications", notes = "Provides values for converting pptx file to Image",
            response = Response.class, authorizations = { @Authorization(value="JWT") })
    public ResponseEntity<Response> pptxConverterToImage(
            @RequestParam("file") MultipartFile file, @RequestParam(defaultValue = "") String md5,
            @RequestParam(defaultValue = "") String outputName, @RequestParam(defaultValue = "0") int rotate,
            @RequestParam(defaultValue = "%") String scale, @RequestParam(defaultValue = "false") boolean isThumbnail,
            @RequestParam(defaultValue = "false") boolean isMetadata, @RequestParam(defaultValue = ".png")
            String imageFormat, @RequestParam(defaultValue = "0") int width, @RequestParam(defaultValue = "0")
            int height, @RequestParam(defaultValue = "") String pagesToConvert, HttpServletRequest request) {

        PdfParam pdfParam = new PdfParam();
        PptxParam pptxParam = new PptxParam();
        FileResponse fileResponse;
        ErrorResponse errorResponse = new ErrorResponse();
        IConverter pptxConverter = new PptxConverter();
        IConverter pdfConverter = new PdfConverter();

        try {
            FileEntity fileEntity = new FileEntity();
            String cleanMd5 = md5.trim();

            if (fileRepository.findByMd5(cleanMd5) != null) {
                pptxParam.setInputPathFile(fileRepository.findByMd5(cleanMd5).getFilePath());
            } else {
                String fileUploadedPath = FileServiceController.writeFile(PathJfc.getInputFilePath() + file.
                        getOriginalFilename(), file);
                cleanMd5 = Md5Checksum.getMd5(fileUploadedPath, cleanMd5);
                pptxParam.setInputPathFile(fileUploadedPath);
                fileEntity.setFilePath(fileUploadedPath);
                fileEntity.setMd5(cleanMd5);
                fileRepository.save(fileEntity);
            }
            pptxParam.setFileFormat(imageFormat);
            pptxParam.setMd5(cleanMd5);
            pptxParam.setFolderName(cleanMd5);
            pptxParam.setInputName(FileServiceController.getName(file));
            pptxParam.setOutputName(outputName);
            pptxParam.setOutputPathFile(PathJfc.getInputFilePath());
            pptxConverter.convert(pptxParam);

            pdfParam.setMd5(cleanMd5);
            pdfParam.setInputPathFile(pptxParam.getInputPathFile());
            pdfParam.setOutputPathFile(PathJfc.getOutputFilePath());
            pdfParam.setInputName(FileServiceController.getName(file));
            pdfParam.setOutputName(outputName);
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
            FolderRemover.removeFolder(pptxParam.getOutputPathFile() + pptxParam.getFolderName());
            fileResponse.setName(pdfParam.getFolderName());
            fileResponse.setStatus(MessageResponse.SUCCESS200.getMessageResponse());

            return new ResponseEntity<>(fileResponse, HttpStatus.CREATED);
        } catch (ConvertException | Md5Exception ex) {
            errorResponse.setName(pptxParam.getOutputName());
            errorResponse.setStatus(MessageResponse.ERROR406.getMessageResponse());
            errorResponse.setError(ex.toString());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
        } catch (CommandValueException cve) {
            errorResponse.setName(pptxParam.getOutputName());
            errorResponse.setStatus(MessageResponse.ERROR400.getMessageResponse());
            errorResponse.setError(cve.toString());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            errorResponse.setName(pptxParam.getOutputName());
            errorResponse.setStatus(MessageResponse.ERROR404.getMessageResponse());
            errorResponse.setError(ex.toString());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        } finally {
            FolderRemover.removeFolder(pptxParam.getOutputPathFile() + pptxParam.getFolderName());
        }
    }
}
