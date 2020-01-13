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

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.Charset;

import java.util.Date;

/**
 * Manages JsonWebToken.
 *
 * @version 0.1 13 Jan 2020.
 *
 * @author Juan Martinez.
 */
public class JsonWebToken {

    // Time of valid token.
    private long validityInMilliseconds = 3600000;

    // Content super secret key.
    private String secretKey = "at11_01_10_2020_fundacion_jala_org";

    /**
     * Allows to create a new Json Web Token.
     * @param userEntity object.
     * @return token created.
     */
    public String createJwt(UserEntity userEntity) {
        Claims claims = Jwts.claims().setSubject(userEntity.getUser());
        claims.put("rol", userEntity.getRol());

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        String token = Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes()).compact();
        return token;
    }

    /**
     * Allows to validate token.
     * @param token value.
     * @return true or false.
     */
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey.getBytes(Charset.forName("UTF-8")))
                    .parseClaimsJws(token);
            if (claims.getBody().getExpiration().before(new Date())) {
             return false;
            }
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtException("Expired or invalid JWT");
        }
    }
}
