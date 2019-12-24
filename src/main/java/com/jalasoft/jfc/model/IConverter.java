/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model;

import com.jalasoft.jfc.model.exception.ConvertException;
import org.im4java.utils.BatchConverter;

import java.io.IOException;

/**
 *
 *  IConverter interface defines behavior to Converters.
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
     */
    public FileResult convert(Param param) throws ConvertException, IOException;
}
