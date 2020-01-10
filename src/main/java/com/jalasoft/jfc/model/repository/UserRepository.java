package com.jalasoft.jfc.model.repository;

import com.jalasoft.jfc.model.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * Interface defines behavior to UserRepository.
 *
 * @version 0.1 Jan 2020.
 *
 * @author Oscar Lopez.
 */
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
}
