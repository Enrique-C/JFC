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
import com.jalasoft.jfc.model.exception.ErrorMessageJfc;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

/**
 * Manages PdfConverter Requests.
 *
 * @version 0.1 09 Jan 2020.
 *
 * @author Alan Escalera.
 */
@Api(value = "PptxConverterController", description = "REST API related to PptxParam Entity")
@RestController
@RequestMapping("/api")
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
            response = Response.class)
    public Response pptxConverterToPdf(
            @RequestParam("file") MultipartFile file, @RequestParam(defaultValue = "") String md5,
            @RequestParam String outputName, @RequestParam(defaultValue = "false") boolean isThumbnail,
            @RequestParam(defaultValue = "false") boolean isMetadata, @RequestParam(defaultValue = ".jpg")
            String thumbnailFormat, @RequestParam(defaultValue = "") String pagesToConvertThumbNail,
            HttpServletRequest request) {

        PptxParam pptxParam = new PptxParam();
        final String FILE_FORMAT = ".pdf";
        FileResponse fileResponse = new FileResponse();
        ErrorResponse errorResponse = new ErrorResponse();
        IConverter PptxConverter = new PptxConverter();

        try {
            String fileUploadedPath = FileServiceController.writeFile(PathJfc.getInputFilePath() + file.
                    getOriginalFilename(), file);

            FileEntity fileEntity = new FileEntity();

            if (Md5Checksum.getMd5(fileUploadedPath, md5)) {
                if (fileRepository.findByMd5(md5) != null) {
                    pptxParam.setInputPathFile(fileRepository.findByMd5(md5).getFilePath());
                } else {
                    pptxParam.setInputPathFile(fileUploadedPath);
                    fileEntity.setFilePath(fileUploadedPath);
                    fileEntity.setMd5(md5);
                    fileRepository.save(fileEntity);
                }
                pptxParam.setFileFormat(FILE_FORMAT);
                pptxParam.setMd5(md5);
                pptxParam.setOutputPathFile(PathJfc.getOutputFilePath());
                pptxParam.setOutputName(FileServiceController.setName(outputName, file));
                pptxParam.setThumbnailFormat(thumbnailFormat);
                pptxParam.setPagesToConvertThumbnail(pagesToConvertThumbNail);
                pptxParam.setIsThumbnail(isThumbnail);
                pptxParam.isMetadata(isMetadata);
                pptxParam.setFolderName(md5);

                fileResponse = PptxConverter.convert(pptxParam);
                LinkGenerator linkGenerator = new LinkGenerator();
                fileResponse.setDownload(linkGenerator.linkGenerator(fileResponse.getDownload(), request));
            } else {
                throw new Md5Exception(ErrorMessageJfc.MD5_ERROR.getErrorMessageJfc(), pptxParam.getMd5());
            }
        } catch (ConvertException ex) {
            errorResponse.setName(pptxParam.getOutputName());
            errorResponse.setStatus(MessageResponse.ERROR406.getMessageResponse());
            errorResponse.setError(ex.toString());
            return errorResponse;
        } catch (CommandValueException cve) {
            errorResponse.setName(pptxParam.getOutputName());
            errorResponse.setStatus(MessageResponse.ERROR400.getMessageResponse());
            errorResponse.setError(cve.toString());
            return errorResponse;
        } catch (IOException ex) {
            errorResponse.setName(pptxParam.getOutputName());
            errorResponse.setStatus(MessageResponse.ERROR404.getMessageResponse());
            errorResponse.setError(ex.toString());
            return errorResponse;
        } catch (Md5Exception ex) {
            errorResponse.setName(pptxParam.getOutputName());
            errorResponse.setStatus(MessageResponse.ERROR406.getMessageResponse());
            errorResponse.setError(ex.toString());
            return errorResponse;
        } catch (Exception ex) {
            errorResponse.setName(pptxParam.getOutputName());
            errorResponse.setStatus(MessageResponse.ERROR404.getMessageResponse());
            errorResponse.setError(ex.toString());
            return errorResponse;
        }
        return fileResponse;
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
            response = Response.class)
    public Response pdfConverter(
            @RequestParam("file") MultipartFile file, @RequestParam(defaultValue = "") String md5,
            @RequestParam String outputName, @RequestParam(defaultValue = "0") int rotate,
            @RequestParam(defaultValue = "%") String scale, @RequestParam(defaultValue = "false") boolean isThumbnail,
            @RequestParam(defaultValue = "false") boolean isMetadata, @RequestParam(defaultValue = ".png")
            String imageFormat, @RequestParam(defaultValue = "0") int width, @RequestParam(defaultValue = "0")
            int height, @RequestParam(defaultValue = "") String pagesToConvert, HttpServletRequest request) {

        PdfParam pdfParam = new PdfParam();
        PptxParam pptxParam = new PptxParam();
        FileResponse fileResponse = new FileResponse();
        ErrorResponse errorResponse = new ErrorResponse();
        IConverter pptxConverter = new PptxConverter();
        IConverter pdfConverter = new PdfConverter();

        try {
            String fileUploadedPath = FileServiceController.writeFile(PathJfc.getInputFilePath() + file.
                    getOriginalFilename(), file);

            if (Md5Checksum.getMd5(fileUploadedPath, md5)) {
                pptxParam.setFileFormat(imageFormat);
                pptxParam.setMd5(md5);
                pptxParam.setFolderName(md5);
                pptxParam.setOutputName(outputName);
                pptxParam.setInputPathFile(fileUploadedPath);
                pptxParam.setOutputPathFile(PathJfc.getInputFilePath());
                pptxConverter.convert(pptxParam);

                pdfParam.setMd5(md5);
                pdfParam.setInputPathFile(pptxParam.getInputPathFile());
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
                pdfParam.setFolderName(md5);

                fileResponse = pdfConverter.convert(pdfParam);
                LinkGenerator linkGenerator = new LinkGenerator();
                fileResponse.setDownload(linkGenerator.linkGenerator(fileResponse.getDownload(), request));
                FolderRemover.removeFolder(pptxParam.getOutputPathFile() + pptxParam.getFolderName());
            }
            else {
                throw new Md5Exception(ErrorMessageJfc.MD5_ERROR.getErrorMessageJfc(), pdfParam.getMd5());
            }
        } catch (ConvertException ex) {
            errorResponse.setName(pdfParam.getOutputName());
            errorResponse.setStatus(MessageResponse.ERROR406.getMessageResponse());
            errorResponse.setError(ex.toString());
            return errorResponse;
        } catch (CommandValueException cve) {
            errorResponse.setName(pdfParam.getOutputName());
            errorResponse.setStatus(MessageResponse.ERROR400.getMessageResponse());
            errorResponse.setError(cve.toString());
            return errorResponse;
        } catch (IOException ex) {
            errorResponse.setName(pdfParam.getOutputName());
            errorResponse.setStatus(MessageResponse.ERROR404.getMessageResponse());
            errorResponse.setError(ex.toString());
            return errorResponse;
        }catch (Md5Exception ex) {
            errorResponse.setName(pdfParam.getOutputName());
            errorResponse.setStatus(MessageResponse.ERROR406.getMessageResponse());
            errorResponse.setError(ex.toString());
            return errorResponse;
        } catch (Exception ex) {
            errorResponse.setName(pdfParam.getOutputName());
            errorResponse.setStatus(MessageResponse.ERROR404.getMessageResponse());
            errorResponse.setError(ex.toString());
            return errorResponse;
        }
        return fileResponse;
    }
}
