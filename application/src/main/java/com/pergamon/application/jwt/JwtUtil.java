package com.pergamon.application.jwt;

import java.security.Key;
import java.util.Date;

import com.pergamon.core.enums.AccountProfile;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {
	
	
	private final Key key = Keys.hmacShaKeyFor(
	        "@bojevtsi@sliven@".getBytes()
	    );
	
	public String generateToken(String eMail, String profileEnumString) {
        return Jwts.builder()
                .setSubject(eMail)
                .claim("role", profileEnumString)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
	}
	
	
	public String validateToken(String token) {
	    return Jwts.parserBuilder()
	            .setSigningKey(key)
	            .build()
	            .parseClaimsJws(token)
	            .getBody()
	            .getSubject();
	}
}
