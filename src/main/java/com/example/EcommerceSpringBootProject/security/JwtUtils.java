package com.example.EcommerceSpringBootProject.security;

import com.example.EcommerceSpringBootProject.entity.User;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {
    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000;
    @Autowired
    private Environment env;

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtils.class);

    public String generateAccessToken(User user){
        return Jwts
                .builder()
                .setSubject(String.format("%s,%s", user.getId(), user.getEmail()))
                .setIssuer("thinhnd")
                .claim("roles",user.getRoles().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + this.EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS256,env.getProperty("spring.jwt.secret-key"))
                .compact();
    }


    public Boolean validateAccessToken(String token){
        try{
            Jwts.parser().setSigningKey(env.getProperty("spring.jwt.secret-key")).parseClaimsJws(token);
            return true;
        }catch (ExpiredJwtException ex) {
            LOGGER.error("JWT expired", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            LOGGER.error("Token is null, empty or only whitespace", ex.getMessage());
        } catch (MalformedJwtException ex) {
            LOGGER.error("JWT is invalid", ex);
        } catch (UnsupportedJwtException ex) {
            LOGGER.error("JWT is not supported", ex);
        } catch (SignatureException ex) {
            LOGGER.error("Signature validation failed");
        }
        return false;
    }

    public Claims parseClaims(String token){
        return Jwts.parser().
                setSigningKey(env.getProperty("spring.jwt.secret-key"))
                .parseClaimsJws(token).getBody();
    }
}
