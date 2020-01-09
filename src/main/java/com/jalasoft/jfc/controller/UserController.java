/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.controller;

import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Manages user request.
 *
 * @version 0.1 09 Jan 2020.
 *
 * @author Juan Martinez.
 */
@RestController
public class UserController {

    /**
     * Allows to user login.
     * @param userName credential value.
     * @param password credential value.
     * @return token generated.
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        String TOKEN_SECRET = "at11";
        final String EMPTY_VALUE = "";
        String token = EMPTY_VALUE;
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

}
