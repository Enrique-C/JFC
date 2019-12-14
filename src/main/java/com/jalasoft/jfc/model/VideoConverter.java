/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 *  ("Confidential Information"). You shall not disclose such Confidential
 *  Information and shall use it only in accordance with the terms of the
 *  license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 *  VideoConverter Class is used for convert videos.
 *
 * @version 1.0 13 Dic 2019
 *
 * @author Juan Martinez
 */
public class VideoConverter {

    /**
     * This method convert a video format to another format.
     * @param videoParam is an object of videoParam class.
     * @return boolean resultFlag confirm the video's conversion
     * if everything was correct.
     * @throws IOException is throws when occurs some problem with
     * the file.
     * @throws InterruptedException is throws when occurs some
     * interruption at the moment of conversion.
     */
    public boolean convert(VideoParam videoParam) throws IOException, InterruptedException {
        String space = " ";             //space between commands.
        boolean resultFlag = false;     //flag for return value.
        try {
            StringBuilder command = new StringBuilder();
            if (videoParam.getfFmpeg().equals(null)) {
                throw new IOException("JFC_IOException");
            }
            
            command.append(videoParam.getfFmpeg());
            if (videoParam.getInputPathFile().equals(null)) {
                throw new IOException("JFC_IOException");
            }

            /**
             * This statement adds space to command string. */
            command.append(space);

            /**
             * This statement adds INFILE VideoCommand to command string. */
            command.append(VideoCommand.INFILE.getCommand());

            /**
             * This statement adds space to command string. */
            command.append(space);

            /**
             * This statement adds InputPathFile value to command string. */
            command.append(videoParam.getInputPathFile());

            if (videoParam.getAspectRatio() != 0.0) {
                command.append(space);
                command.append(VideoCommand.ASPECT_RATIO.getCommand());
                command.append(space);
                command.append(videoParam.getAspectRatio());
            }

            if (!videoParam.getFrameRate().equals(null)) {
                command.append(space);
                command.append(VideoCommand.FRAME_RATE.getCommand());
                command.append(space);
                command.append(videoParam.getFrameRate());
            }

            if (videoParam.getWight() > 0 && videoParam.getHeight() > 0) {
                command.append(space);
                command.append(VideoCommand.FRAME_SIZE.getCommand());
                command.append(space);
                command.append(videoParam.getWight());
                command.append(VideoCommand.ASTERISK.getCommand());
                command.append(videoParam.getHeight());
            }

            if (videoParam.getVideoCodec() != null) {
                command.append(space);
                command.append(VideoCommand.AUDIO_CODEC.getCommand());
                command.append(space);
                command.append(videoParam.getVideoCodec());
            }

            if (videoParam.getAudioCodec() != null) {
                command.append(space);
                command.append(VideoCommand.AUDIO_CODEC.getCommand());
                command.append(space);
                command.append(videoParam.getAudioCodec());
            }

            if (videoParam.getVideoBitRate() != null) {
                command.append(space);
                command.append(VideoCommand.VIDEO_BITRATE.getCommand());
                command.append(space);
                command.append(videoParam.getVideoBitRate());
            }

            if (videoParam.getAudioBitRate() != null) {
                command.append(space);
                command.append(VideoCommand.AUDIO_BITRATE.getCommand());
                command.append(space);
                command.append(videoParam.getAudioBitRate());
            }

            if (videoParam.getQuality() > -1) {
                command.append(space);
                command.append(VideoCommand.SCALE.getCommand());
                command.append(space);
                command.append(videoParam.getQuality());
                command.append(VideoCommand.EMPTY.getCommand());
            }

            if (videoParam.getChannelsNumber() > 0) {
                command.append(space);
                command.append(VideoCommand.CHANNELS.getCommand());
                command.append(space);
                command.append(videoParam.getChannelsNumber());
                command.append(VideoCommand.EMPTY.getCommand());
            }

            if (videoParam.getVolume() != null) {
                command.append(space);
                command.append(VideoCommand.VOLUME.getCommand());
                command.append(space);
                command.append(videoParam.getVolume());
            }

            if (videoParam.getRotate() != null) {
                command.append(space);
                command.append(VideoCommand.ROTATE.getCommand());
                command.append(space);
                command.append(videoParam.getRotate());
            }

            if (videoParam.getOutputPathFile().equals(null) &&
                    videoParam.getOutputFileName().equals(null)) {
                throw new NullPointerException("JFCNullPointerException");
            }

            /**
             * This statement adds space to command string. */
            command.append(space);

            /**
             * This statement adds OutputPathFile value to command string. */
            command.append(videoParam.getOutputPathFile());

            /**
             * This statement adds OutputFileName value to command string. */
            command.append(videoParam.getOutputFileName());

            /**
             * This is an declaration of ProcessBuilder variable. */
            ProcessBuilder processBuilder = new ProcessBuilder();

            /**
             * This is an declaration of stringCommand variable. */
            String stringCommand = command.toString();

            /**
             * This is an array for splitting stringCommand into commands
             * by " ". */
            String[] newCommand = stringCommand.split(" ");

            /**
             * Here processBuilder adds array of commands. */
            processBuilder.command(newCommand);

            /**
             * Here Process start processBuilder commands and
             * starts the conversion process. */
            Process process = processBuilder.start();

            /**
             * Here InputStream gets Process error stream. */
            InputStream inputStream = process.getErrorStream();

            /**
             * InputStreamReader read inputStream. */
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            /**
             * BufferedReader gets inputStreamReader. */
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;        //line variable

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);       //prints lines
            }
            process.waitFor();       //return the successful result
            resultFlag = true;
        }
        catch (IOException ex){
            System.out.println("JFCIOException");
        }
        catch (NullPointerException e){
            System.out.println("JFCNullPointerException");
        }
        finally {
            return resultFlag;
        }
    }
}
