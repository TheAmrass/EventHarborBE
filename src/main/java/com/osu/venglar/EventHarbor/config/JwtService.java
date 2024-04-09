package com.osu.venglar.EventHarbor.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

//Service for manipulation with the JWT token

@Service
public class JwtService {

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;

    @Value("${application.security.jwt.refresh-token.expiration}")
    private String refreshExpiration;

    //Extraction of the username from the token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject); //Subject = email
    }

    // <T> = generic class
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateRefreshToken(UserDetails userDetails){
        return buildToken(new HashMap<>(),userDetails, jwtExpiration);
    }

    //Generates token without extra claims
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    //Generates token with extra claims
    public String generateToken(Map<String,Object> extraClaims,UserDetails userDetails){
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }



    public String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            Long expiration
    ){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername()) //Unique part (email, but spring boot calls it username)
                .setIssuedAt(new Date(System.currentTimeMillis())) //Initial date
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256) //Signing of the token
                .compact(); //generation and return of the token
    }


    //Validation of the token (Does this token belongs to that user?)
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    //Created for use at isTokenValid
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    //Created for isTokenExpired
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    //Extracting claims from the JWT token
    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey()) //getSignInKey is my method
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes); //hmacShaKeyFor is algorithm
    }
}
