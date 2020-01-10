/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model;

import com.jalasoft.jfc.model.exception.CommandValueException;
import com.jalasoft.jfc.model.exception.ConvertException;
import com.jalasoft.jfc.model.exception.ZipJfcException;
import com.jalasoft.jfc.model.result.FileResponse;

import java.io.IOException;

/**
 *
 * IConverter interface defines behavior to Converters.
 *
 * @version 0.1 Dic 2019.
 *
 * @author Enrique Carrizales.
 */
public interface IConverter {

    /**
     * Convert files to another type file.
     * @param param it receives file params.
     * @return state of conversion.
     * @throws CommandValueException this.
     * @throws ConvertException when the convert process is not completed.
     */

    FileResponse convert(Param param) throws ConvertException, CommandValueException, ZipJfcException, IOException;
}
