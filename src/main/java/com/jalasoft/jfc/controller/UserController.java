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
import org.springframework.web.bind.annotation.PostMapping;
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
@RestController
public class UserController {

    /**
     * Allows to user login.
     * @param userName credential value.
     * @param password credential value.
     * @return token generated.
     */
    @PostMapping("/login")
    public String loginUser(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        String TOKEN_SECRET = "at11";
        final String TOKEN_PREFIX = "Bearer ";
        final String HEADER_STRING = "Authorization";

        String token = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, TOKEN_SECRET.getBytes())
                .setSubject(userName)
                .claim("ROLE", "admin")
                .compact();

        return TOKEN_PREFIX + token;
    }
}