/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.controller;

import com.jalasoft.jfc.model.entity.UserEntity;
import com.jalasoft.jfc.model.repository.UserRepository;
import com.jalasoft.jfc.model.result.Response;

import com.jalasoft.jfc.model.token.JsonWebToken;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Manages user request.
 *
 * @version 0.1 09 Jan 2020.
 *
 * @author Juan Martinez.
 */
@Api(value = "UserController", description = "REST API related to User Entity")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    /**
     * Allows to user login.
     * @param userName credential value.
     * @param password credential value.
     * @return ResponseEntity with token generated.
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam("userName") String userName, @RequestParam("password")
            String password) {
        UserEntity userEntity = userRepository.login(userName, password);
        if (userEntity != null) {
            JsonWebToken jwt = new JsonWebToken();
            String token = jwt.createJwt(userEntity);
            userEntity.setToken(token);
            userRepository.save(userEntity);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(token);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(HttpStatus.BAD_REQUEST);
    }

    /**
     * Allows to add new user.
     * @param userName for adding name.
     * @param password for adding password.
     * @param rol for adding rol.
     * @param email for adding email address.
     * @return Response object.
     */
    @PostMapping("/addUser")
    @ApiOperation(value = "User specifications", notes = "Provides values for adding new user",
            response = Response.class, authorizations = { @Authorization(value="JWT") })
    public ResponseEntity<?> addUser(@RequestParam String userName, @RequestParam String
             password, @RequestParam String rol, @RequestParam String email) {
        UserEntity userEntity = new UserEntity();
        try {
            userEntity.setUser(userName);
            userEntity.setPassword(password);
            userEntity.setRol(rol);
            userEntity.setEmail(email);
            userRepository.save(userEntity);
            return new ResponseEntity<Object>(userEntity, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<Object>(userEntity, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    /**
     * Allows to update user value.
     * @param userName for adding name.
     * @param password for adding password.
     * @param rol for adding rol.
     * @param email for adding email address.
     * @return Response object.
     */
    @PutMapping("/updateUser")
    @ApiOperation(value = "User specifications", notes = "Provides values for updating user values",
            response = Response.class, authorizations = { @Authorization(value="JWT") })
    public ResponseEntity<UserEntity> updateUser(@RequestParam int id, @RequestParam String userName,
            @RequestParam String password, @RequestParam String rol, @RequestParam String email) {
        UserEntity userEntity = userRepository.findOne(id);
        
        if (userEntity != null) {
            userEntity.setUser(userName);
            userEntity.setPassword(password);
            userEntity.setRol(rol);
            userEntity.setEmail(email);
            userRepository.save(userEntity);
            return new ResponseEntity<>(userEntity, HttpStatus.OK);
        }
        return new ResponseEntity<>(userEntity, HttpStatus.NOT_MODIFIED);
    }

    /**
     * Finds user by Id.
     * @param id for finding user.
     * @return Response object.
     */
    @GetMapping("/findById")
    @ApiOperation(value = "User Id", notes = "Provides user values by Id",
            response = Response.class, authorizations = { @Authorization(value="JWT") })
    public ResponseEntity<?> findUserById(@RequestParam("Id") int id) {
        UserEntity userEntity = userRepository.findOne(id);
        if (userEntity != null) {
            return new ResponseEntity<>(userEntity, HttpStatus.FOUND);
        }
        return new ResponseEntity<Object>("Id " + id, HttpStatus.NOT_FOUND);
    }

    /**
     * Gets all users.
     * @return Response a list of users.
     */
    @GetMapping("/getAllUsers")
    @ApiOperation(value = "Authorization", notes = "Provides user values all users",
            response = Response.class, authorizations = { @Authorization(value="JWT") })
    public ResponseEntity<List<?>> getAllUsers() {
        try {
            return new ResponseEntity<>((List<?>) userRepository.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Deletes user by Id.
     * @param id to delete user.
     * @return Response object.
     */
    @DeleteMapping("/deleteById")
    @ApiOperation(value = "Authorization", notes = "Delete user values by Id",
            response = Response.class, authorizations = { @Authorization(value="JWT") })
    public ResponseEntity<?> deleteById(@RequestParam("Id") int id) {
        UserEntity userEntity = userRepository.findOne(id);
        if (userEntity != null) {
            userRepository.delete(id);
            return new ResponseEntity<>(userEntity, HttpStatus.MOVED_PERMANENTLY);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
