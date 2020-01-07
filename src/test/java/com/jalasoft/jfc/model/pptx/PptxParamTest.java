/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.pptx;

import com.jalasoft.jfc.model.exception.CommandValueException;
import com.jalasoft.jfc.model.exception.ConvertException;
import com.jalasoft.jfc.model.exception.ZipJfcException;
import org.junit.Test;

import static org.junit.Assert.*;

public class PptxParamTest {

    @Test
    public void Param_PptxParamSetInputPathFile_getInputPathFile() throws ZipJfcException, CommandValueException, ConvertException {
        PptxParam pptxParam = new PptxParam();
        String InputPathFile = "src/test/resources/Designpatters.pptx";
        pptxParam.setInputPathFile(InputPathFile);
        boolean expected = pptxParam.getInputPathFile().equals(InputPathFile);

        assertTrue(expected);
    }
}
