/*
 * Copyright (c) 2019 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Jalasoft.
 */

package com.jalasoft.jfc.model.token;

import com.jalasoft.jfc.model.entity.UserEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JsonWebToken {
    public static String createJwt(UserEntity userEntity) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        String TOKEN_SECRET = "at11-01-10-2020-fundacion-jala.org";
        String token = Jwts.builder().setIssuedAt(now).signWith(SignatureAlgorithm.HS256, TOKEN_SECRET.getBytes())
                .setSubject(userEntity.getUser()).claim("rol", userEntity.getRol()).compact();
        return token;
    }
}
