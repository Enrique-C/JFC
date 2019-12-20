/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.video;

import com.jalasoft.jfc.model.FileResult;
import com.jalasoft.jfc.model.IConverter;
import com.jalasoft.jfc.model.Param;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This Class is used for convert videos.
 *
 * @author Juan Martinez
 * @version 0.1 13 Dic 2019
 */
public class VideoConverter implements IConverter {

    private final static Logger LOGGER = Logger.getLogger(VideoConverter.class.getName());

    /**
     * This method convert a video format to another format.
     *
     * @param param is an object of videoParam class.
     * @return boolean resultFlag confirm the video's conversion if everything was correct.
     */
    public FileResult convert(Param param) {

        // Instance of videoParam for casting param.
        VideoParam videoParam = (VideoParam) param;

        // Space between commands.
        String space = " ";

        // Flag for return value.
        FileResult fileResult = new FileResult();

        try {
            StringBuilder command = new StringBuilder();
            if (videoParam.getfFmpeg().equals(null)) {
                throw new IOException("JFC_IOException");
            }

            command.append(videoParam.getfFmpeg());
            if (videoParam.getInputPathFile().equals(null)) {
                throw new IOException("JFC_IOException");
            }

            command.append(space);
            command.append(VideoCommand.INFILE.getCommand());
            command.append(space);
            command.append(videoParam.getInputPathFile());

            if (videoParam.getAspectRatio() != 0.0) {
                command.append(space);
                command.append(VideoCommand.ASPECT_RATIO.getCommand());
                command.append(space);
                command.append(videoParam.getAspectRatio());
            }

            if (videoParam.getFrameRate() != null) {
                command.append(space);
                command.append(VideoCommand.FRAME_RATE.getCommand());
                command.append(space);
                command.append(videoParam.getFrameRate());
            }

            if (videoParam.getWidth() > 0 && videoParam.getHeight() > 0) {
                command.append(space);
                command.append(VideoCommand.FRAME_SIZE.getCommand());
                command.append(space);
                command.append(videoParam.getWidth());
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

            if (videoParam.getThumbnail() != null) {
                command.append(space);
                command.append(VideoCommand.THUMBNAIL.getCommand());
                command.append(space);
                command.append(videoParam.getThumbnail());
            }

            if (videoParam.getVideoFrame() > 0) {
                command.append(space);
                command.append(VideoCommand.V_FRAMES.getCommand());
                command.append(space);
                command.append(videoParam.getVideoFrame());
            }

            if (videoParam.getOutputPathFile().equals(null) || videoParam.getOutputFileName().equals(null)) {
                throw new NullPointerException("JFCNullPointerException");
            }

            command.append(space);
            command.append(videoParam.getOutputPathFile());
            command.append(videoParam.getOutputFileName());
            String stringCommand = command.toString();
            Process process = Runtime.getRuntime().exec(stringCommand);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            // Line variable.
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                LOGGER.log(Level.FINE, line, line);
            }
            process.waitFor();
            bufferedReader.close();
            return fileResult;
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        } catch (NullPointerException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        } finally {
            return null;
        }
    }
}
