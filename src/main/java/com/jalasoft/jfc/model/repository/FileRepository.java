/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */
package com.jalasoft.jfc.model.repository;

import com.jalasoft.jfc.model.entity.FileEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Defines behavior to FileRepository.
 *
 * @version 0.1 Jan 2020.
 *
 * @author Oscar Lopez.
 */
public interface FileRepository extends CrudRepository<FileEntity, Integer> {
}
