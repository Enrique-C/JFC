/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.command.ffmpeg;

import com.jalasoft.jfc.model.audio.AudioFfmpegCommand;
import com.jalasoft.jfc.model.command.ICommandStrategy;
import com.jalasoft.jfc.model.exception.CommandValueException;

/**
 * Generates an audio command.
 *
 * @version 0.1 09 Jan 2020.
 *
 * @author Enrique Carrizales.
 */
public class CommandAudioChannel implements ICommandStrategy {

    // Contents an audio codec value.
    private String audioChannel;

    // Contents an audio format value.
    private String audioFormatOut;

    /**
     * Creates a new CommandAudio object.
     * @param audioChannel receives value.
     * @param audioFormatOut receives value.
     */
    public CommandAudioChannel(String audioChannel, String audioFormatOut) {
        this.audioChannel = audioChannel;
        this.audioFormatOut = audioFormatOut;
    }

    /**
     * Builds commands.
     * @return commands concatenated.
     * @throws CommandValueException when there is an invalid command.
     */
    @Override
    public String command() throws CommandValueException {
        if (audioChannel.isEmpty()) {
            return "";
        } else {
            verifyChannel(audioFormatOut);
            return this.SPACE + AudioFfmpegCommand.AUDIO_CHANNEL.getFfmpegCommand() + this.SPACE + audioChannel;
        }
    }

    /**
     * Verifies and audio channel.
     * @param outPutFormat out put audio format.
     * @throws CommandValueException when there is an invalid command.
     */
    private void verifyChannel(String outPutFormat) throws CommandValueException {
        final String MONO_CHANNEL = "1";
        final String STEREO_CHANNEL = "2";
        final String BASS_DUAL_CHANNEL = "2.1";
        final String BASS_TRIPLE_CHANNEL = "3.1";
        final String QUAD_CHANNEL = "4";
        final String BASS_FIVE_CHANNEL = "5.1";
        final String BASS_SEVEN_CHANNEL = "7.1";

        switch (outPutFormat) {
            case ".mp3":
                //Todo
                break;
            case ".wav":
                //Todo
                break;
        }
    }
}
