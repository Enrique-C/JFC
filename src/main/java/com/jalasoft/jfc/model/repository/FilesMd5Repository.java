package com.jalasoft.jfc.model.repository;

import com.jalasoft.jfc.model.entity.FilesEntity;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * Interface defines behavior to FilesMd5Repository.
 *
 * @version 0.1 Jan 2020.
 *
 * @author Oscar Lopez.
 */
public interface FilesMd5Repository extends CrudRepository<FilesEntity, Integer> {
}
