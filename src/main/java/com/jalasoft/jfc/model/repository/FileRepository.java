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
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Defines behavior to FileRepository.
 *
 * @version 0.1 Jan 2020.
 *
 * @author Oscar Lopez.
 */
public interface FileRepository extends CrudRepository<FileEntity, Integer> {
    @Query(
            value = "SELECT o FROM FileEntity fe WHERE fe.md5 = :md5 and fe.filePath = :filePath",
            nativeQuery = true)
    FileEntity findByNd5(@Param("md5") String md5, @Param("filePath") String filePath);
}
