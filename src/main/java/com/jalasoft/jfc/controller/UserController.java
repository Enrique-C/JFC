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
import com.jalasoft.jfc.model.result.Response;

import io.jsonwebtoken.Jwts;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.SignatureAlgorithm;

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

    /**
     * Allows to user login.
     * @param userName credential value.
     * @param password credential value.
     * @return ResponseEntity with token generated.
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam("userName") String userName, @RequestParam("password")
            String password) {
        UserEntity userEntity = new UserEntity();
        String TOKEN_SECRET = "at11";
        String token;
        try {
            token = Jwts.builder()
                    .signWith(SignatureAlgorithm.HS256, TOKEN_SECRET.getBytes())
                    .setSubject(userName)
                    .claim("ROLE", "admin")
                    .compact();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(token);
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
            response = Response.class)
    public ResponseEntity<?> addUser(@RequestParam String userName, @RequestParam String
             password, @RequestParam String rol, @RequestParam String email) {
        try {
            // implement user Entity.
            return new ResponseEntity<Object>("usermodel", HttpStatus.CREATED);
        } catch (Exception ex) {
            // implement user Entity.
            return new ResponseEntity<Object>("usermodel", HttpStatus.NOT_ACCEPTABLE);
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
            response = Response.class)
    public ResponseEntity<?> updateUser(@RequestParam int id, @RequestParam String userName, @RequestParam String
            password, @RequestParam String rol, @RequestParam String email) {
        try {
            // implement user Entity.
            return new ResponseEntity<Object>("usermodel", HttpStatus.OK);
        } catch (Exception ex) {
            // implement user Entity.
            return new ResponseEntity<Object>("usermodel", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    /**
     * Finds user by Id.
     * @param id for finding user.
     * @return Response object.
     */
    @GetMapping("/findById")
    @ApiOperation(value = "User Id", notes = "Provides user values by Id",
            response = Response.class)
    public ResponseEntity<?> findUserById(@RequestParam("Id") int id) {
        if (true) {
            // implement user Entity.
            return new ResponseEntity<Object>("userModel", HttpStatus.FOUND);
        }
        return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
    }

    /**
     * Gets all users.
     * @return Response object.
     */
    @GetMapping("/getAllUsers")
    @ApiOperation(value = "Authorization", notes = "Provides user values all users",
            response = Response.class)
    public ResponseEntity<?> getAllUsers() {
        try {
            // implement user Entity.
            return new ResponseEntity<Object>("userModel", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<Object>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Deletes user by Id.
     * @param id to delete user.
     * @return Response object.
     */
    @DeleteMapping("/deleteById")
    @ApiOperation(value = "Authorization", notes = "Delete user values by Id",
            response = Response.class)
    public ResponseEntity<?> deleteById(@RequestParam("Id") int id) {
        if (true) {
            // implement user Entity.
            return new ResponseEntity<Object>("userModel", HttpStatus.OK);
        }
        return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
    }
}
