package com.jalasoft.jfc.model.hibernate;

import org.springframework.data.repository.CrudRepository;

/**
 *
 *  IConverter interface defines behavior to Converters.
 *
 * @version 0.1 Jan 2020.
 *
 * @author Oscar Lopez.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
}
