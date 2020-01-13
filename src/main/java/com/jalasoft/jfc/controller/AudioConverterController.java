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
import com.jalasoft.jfc.model.audio.AudioConverter;
import com.jalasoft.jfc.model.audio.AudioParam;
import com.jalasoft.jfc.model.exception.CommandValueException;
import com.jalasoft.jfc.model.exception.ConvertException;
import com.jalasoft.jfc.model.exception.ErrorMessageJfc;
import com.jalasoft.jfc.model.exception.Md5Exception;
import com.jalasoft.jfc.model.result.ErrorResponse;
import com.jalasoft.jfc.model.result.FileResponse;
import com.jalasoft.jfc.model.result.MessageResponse;
import com.jalasoft.jfc.model.result.Response;
import com.jalasoft.jfc.model.utility.FileServiceController;
import com.jalasoft.jfc.model.utility.LinkGenerator;
import com.jalasoft.jfc.model.utility.Md5Checksum;
import com.jalasoft.jfc.model.utility.PathJfc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Manage AudioConverter Requests.
 *
 * @author Enrique Carrizales.
 *
 * @version 0.1 08 Jan 2020.
 */
@Api(value = "AudioConverterController", description = "REST API related to AudioParam Entity")
@RestController
@RequestMapping("/api")
public class AudioConverterController {

    /**
     * Receives an audio to convert
     * @param file contains an audio file.
     * @param md5 contains a String value.
     * @param outputName contains the name of converted file.
     * @param audioBitRate contains audioBitRate value.
     * @param isMetadata boolean of metadata.
     * @param audioFormat contains an audio format.
     * @param request contains client request data.
     * @return Response is the result of the conversion.
     */
    @PostMapping("/audioConverter")
    @ApiOperation(value = "Audio specifications", notes = "Provides values for converting Audio file to other one",
            response = Response.class)
    public Response audioConverter(
            @RequestParam("file")MultipartFile file, @RequestParam(defaultValue = " ") String md5,
            @RequestParam String outputName, @RequestParam(defaultValue = "") String audioCodec,
            @RequestParam int sampleRate, @RequestParam String audioChannel, @RequestParam short audioBitRate,
            @RequestParam(defaultValue = "false") boolean isMetadata, @RequestParam(defaultValue = ".mp3")
            String audioFormat, HttpServletRequest request) {

        FileResponse fileResponse;
        ErrorResponse errorResponse = new ErrorResponse();
        AudioParam audioParam = new AudioParam();
        IConverter audioConverter = new AudioConverter();

        try {
            String fileUploadedPath = FileServiceController.writeFile(PathJfc.getInputFilePath() + file
                    .getOriginalFilename(), file);

            String cleanMd5 = Md5Checksum.getMd5(fileUploadedPath, md5);

            audioParam.setMd5(cleanMd5);
            audioParam.setInputPathFile(fileUploadedPath);
            audioParam.setAudioCodec(audioCodec);
            audioParam.setAudioSampleRate(sampleRate);
            audioParam.setAudioChannel(audioChannel);
            audioParam.setAudioBitRate(audioBitRate);
            audioParam.setOutputPathFile(PathJfc.getOutputFilePath());
            audioParam.setOutputName(FileServiceController.setName(outputName, file));
            audioParam.isMetadata(isMetadata);
            audioParam.setAudioFormat(audioFormat);
            audioParam.setFolderName(cleanMd5);

            fileResponse = audioConverter.convert(audioParam);
            LinkGenerator linkGenerator = new LinkGenerator();
            fileResponse.setDownload(linkGenerator.linkGenerator(fileResponse.getDownload(), request));
        } catch (ConvertException ex) {
            errorResponse.setName(audioParam.getOutputName());
            errorResponse.setStatus(MessageResponse.ERROR406.getMessageResponse());
            errorResponse.setError(ex.toString());
            return errorResponse;
        } catch (CommandValueException cve) {
            errorResponse.setName(audioParam.getOutputName());
            errorResponse.setStatus(MessageResponse.ERROR400.getMessageResponse());
            errorResponse.setError(cve.toString());
            return errorResponse;
        } catch (IOException ex) {
            errorResponse.setName(audioParam.getOutputName());
            errorResponse.setStatus(MessageResponse.ERROR404.getMessageResponse());
            errorResponse.setError(ex.toString());
            return errorResponse;
        } catch (Md5Exception ex) {
            errorResponse.setName(outputName);
            errorResponse.setStatus(MessageResponse.ERROR406.getMessageResponse());
            errorResponse.setError(ex.toString());
            return errorResponse;
        } catch (Exception ex) {
            errorResponse.setName(audioParam.getOutputName());
            errorResponse.setStatus(MessageResponse.ERROR404.getMessageResponse());
            errorResponse.setError(ex.toString());
            return errorResponse;
        }
        return fileResponse;
    }
}
