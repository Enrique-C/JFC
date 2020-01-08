/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.pptx;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Executes PptxParams's unit tests.
 *
 * @version 0.1 07 Jan 2020.
 *
 * @author Alan Escalera.
 */
public class PptxParamTest {

    @Test
    public void Param_PptxParamSetInputPathFile_getInputPathFile() {
        PptxParam pptxParam = new PptxParam();
        String InputPathFile = "src/test/resources/Designpatters.pptx";
        pptxParam.setInputPathFile(InputPathFile);
        boolean expected = pptxParam.getInputPathFile().equals(InputPathFile);

        assertTrue(expected);
    }
}
