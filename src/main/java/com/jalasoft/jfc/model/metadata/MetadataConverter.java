/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.metadata;

import com.jalasoft.jfc.model.IConverter;
import com.jalasoft.jfc.model.Param;
import com.jalasoft.jfc.model.command.ContextStrategy;
import com.jalasoft.jfc.model.command.ICommandStrategy;
import com.jalasoft.jfc.model.command.common.CommandInputFilePath;
import com.jalasoft.jfc.model.command.common.CommandOutputFileName;
import com.jalasoft.jfc.model.command.common.CommandOutputFilePath;
import com.jalasoft.jfc.model.command.exiftool.CommandExiftoolPath;
import com.jalasoft.jfc.model.command.exiftool.CommandMetadataFormat;
import com.jalasoft.jfc.model.command.exiftool.CommandMetadataTagsfromfile;
import com.jalasoft.jfc.model.exception.CommandValueException;
import com.jalasoft.jfc.model.exception.ConvertException;
import com.jalasoft.jfc.model.result.FileResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class manages Exiftool.
 *
 * @version 0.1 03 Jan 2020.
 *
 * @author Enrique Carrizales.
 */
public class MetadataConverter implements IConverter {

    // Tag thumbnail.
    final String METADATA_TAG = "metadata";

    // List of metadata command.
    List<ICommandStrategy> commandMetadataList = new ArrayList<>();

    /**
     * Generates a metadata.
     * @param param receives a file param.
     */
    private void generateMetadata(Param param) {
        commandMetadataList.add(new CommandExiftoolPath());
        commandMetadataList.add(new CommandMetadataTagsfromfile());
        commandMetadataList.add(new CommandInputFilePath(param.getInputPathFile()));
        commandMetadataList.add(new CommandOutputFilePath(param.getOutputPathFile(), param.getFolderName()));
        commandMetadataList.add(new CommandOutputFileName(METADATA_TAG, param.getFolderName()));
        commandMetadataList.add(new CommandMetadataFormat());
    }

    /**
     * Converts a file to metadata.
     * @param param it receives file params.
     * @return an FileResponse object.
     * @throws ConvertException when the conversion failed.
     * @throws CommandValueException when is a invalid command.
     * @throws IOException when file lecture is wrong.
     */
    @Override
    public FileResponse convert(Param param) throws ConvertException, CommandValueException, IOException {
        FileResponse fileResponse = new FileResponse();

        String commandString;

        generateMetadata(param);
        ContextStrategy commandMetadataContext = new ContextStrategy(commandMetadataList);

        try {
            commandString = commandMetadataContext.buildCommand();

            Process metadataConvertProcess = Runtime.getRuntime().exec(commandString);
            metadataConvertProcess.waitFor();
        } catch (Exception e) {
            throw new ConvertException("Error converting File to Metadata: " + e.getMessage(), this.getClass().getName());
        }
        return fileResponse;
    }
}
