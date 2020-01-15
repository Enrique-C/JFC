/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.audio;

import com.jalasoft.jfc.model.exception.CommandValueException;
import com.jalasoft.jfc.model.exception.ConvertException;
import com.jalasoft.jfc.model.exception.ZipJfcException;
import com.jalasoft.jfc.model.utility.FolderRemover;
import com.jalasoft.jfc.model.utility.PathJfc;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * Tests AudioConverter class.
 *
 * @version 0.1 07 Jan 2020.
 *
 * @author Enrique Carrizales.
 */
public class AudioConverterTest {

    final long EMPTY_BYTES = 22;

    @Test
    public void convert_AudioWAVToAudioMP3_Zip() throws ZipJfcException, CommandValueException, ConvertException, IOException {
        AudioConverter audioConverter = new AudioConverter();
        AudioParam audioParam = generateOnlyAudioWAV();

        String zipAudio = audioConverter.convert(audioParam).getDownload();

        File zipFile = new File(zipAudio);

        boolean expected = zipFile.exists() && zipFile.length() > EMPTY_BYTES;

        assertTrue(expected);
    }

    @Test
    public void convert_AudioFileNameWithSpaces_Zip() throws ZipJfcException, CommandValueException, ConvertException, IOException {
        AudioConverter audioConverter = new AudioConverter();
        AudioParam audioParam = generateOnlyAudioWAV();
        audioParam.setInputPathFile("src/test/resources/audio 01.wav");

        String zipAudio = audioConverter.convert(audioParam).getDownload();

        File zipFile = new File(zipAudio);
        boolean expected = zipFile.exists() && zipFile.length() > EMPTY_BYTES;

        assertTrue(expected);
    }

    @Test
    public void convert_AudioWAVToAudioMP3AndMetadata_Zip() throws ZipJfcException, CommandValueException, ConvertException, IOException {
        AudioConverter audioConverter = new AudioConverter();
        AudioParam audioParam = generateOnlyAudioWAV();
        audioParam.isMetadata(true);

        String zipAudio = audioConverter.convert(audioParam).getDownload();

        File zipFile = new File(zipAudio);
        boolean expected = zipFile.exists() && zipFile.length() > EMPTY_BYTES;

        assertTrue(expected);
    }

    @Test(expected = ConvertException.class)
    public void convert_AudioParamNull_ConvertException() throws ConvertException, CommandValueException, IOException, ZipJfcException {
        AudioConverter audioConverter = new AudioConverter();
        audioConverter.convert(null);
    }

    @Test(expected = CommandValueException.class)
    public void convert_AudioParamAudioFormatEmpty_ConvertException() throws ConvertException, CommandValueException, IOException, ZipJfcException {
        AudioConverter audioConverter = new AudioConverter();
        AudioParam audioParam = generateOnlyAudioWAV();
        audioParam.setFileFormat("");

        audioConverter.convert(audioParam);
    }

    private AudioParam generateOnlyAudioWAV() {
        PathJfc pathJfc = new PathJfc();

        String fileUploadedPath = "src/test/resources/audio.wav";

        String md5 = "2559480156e9cddf65ed3125521b9922";
        String outputPath = "src/test/resources/";
        String outputName = "AUD";
        String audioFormat = AudioFormat.MP3.getAudioFormat();
        int audioBitRate = 0;
        String audioCodec = "";
        int sampleRate = 0;
        int audioChannel = 0;
        boolean isMetadata = false;

        AudioParam audioParam = new AudioParam();
        audioParam.setMd5(md5);
        audioParam.setInputPathFile(fileUploadedPath);
        audioParam.setFileFormat(audioFormat);
        audioParam.setAudioCodec(audioCodec);
        audioParam.setAudioSampleRate(sampleRate);
        audioParam.setAudioChannel(audioChannel);
        audioParam.setAudioBitRate(audioBitRate);
        audioParam.setOutputPathFile(outputPath);
        audioParam.setOutputName(outputName);
        audioParam.isMetadata(isMetadata);
        audioParam.setFolderName(md5);

        return audioParam;
    }
}
