/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.strategy;

/**
 *
 * Defines behavior to Commands classes.
 *
 * @version 0.1 18 Dic 2019.
 *
 * @author Enrique Carrizales.
 */
public interface ICommandStrategy {
    
    String SPACE = " ";

    /**
     * Builds a command.
     * @return String of a command.
     */
    String command();
}
