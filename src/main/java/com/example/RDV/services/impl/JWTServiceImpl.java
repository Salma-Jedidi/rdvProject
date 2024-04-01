package com.example.RDV.services.impl;

import com.example.RDV.services.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static io.jsonwebtoken.io.Decoders.BASE64;

@Service
@Primary
public class JWTServiceImpl implements JWTService{



    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        return Jwts.builder().setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSiginKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    public String generateRefreshToken(Map<String, Object> extractClaims, UserDetails userDetails) {

        return Jwts.builder().setClaims(extractClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 684800000 ))
                .signWith(getSiginKey(), SignatureAlgorithm.HS256)
                .compact();

    }


    public String extractUserName(String token){
        return extractClaim(token, Claims::getSubject);
    }


    private <T> T extractClaim(String token, Function<Claims, T>claimsResolvers){
        final Claims claims =extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }



    private Key getSiginKey(){
        byte[] key = BASE64.decode("peritheslayer1234peritheslayer1234peritheslayer1234peritheslayer1234peritheslayer1234peritheslayer1234");
        return Keys.hmacShaKeyFor(key);
    }


    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getSiginKey()).build().parseClaimsJwt(token).getBody();

    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired (String token){
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

}