/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.audio;

import com.jalasoft.jfc.model.IConverter;
import com.jalasoft.jfc.model.Param;
import com.jalasoft.jfc.model.exception.CommandValueException;
import com.jalasoft.jfc.model.exception.ConvertException;
import com.jalasoft.jfc.model.exception.ZipJfcException;
import com.jalasoft.jfc.model.result.FileResponse;

import java.io.IOException;

/**
 * Converts an audio format to another audio format.
 *
 * @version 0.1 07 Jan 2020.
 *
 * @author Enrique Carrizales.
 */
public class AudioConverter implements IConverter {

    /**
     * Changes an Audio format to another one.
     * @param param it receives file params.
     * @return FileResponse object.
     * @throws ConvertException when the conversion failed.
     * @throws CommandValueException when is an invalid command.
     * @throws IOException when there is a wrong input.
     * @throws ZipJfcException when zip process was wrong.
     */
    @Override
    public FileResponse convert(Param param) throws ConvertException, CommandValueException, IOException, ZipJfcException {
        return null;
    }
}
