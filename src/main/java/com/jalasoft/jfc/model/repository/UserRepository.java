/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.repository;

import com.jalasoft.jfc.model.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Defines UserRepository.
 *
 * @version 0.1 10 Jan 2020.
 *
 * @author Oscar Lopez.
 */
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    @Query("SELECT u FROM UserEntity u WHERE u.userName = :userName AND u.password = :password")
    UserEntity login(@Param("userName") String userName, @Param("password") String password);
}
