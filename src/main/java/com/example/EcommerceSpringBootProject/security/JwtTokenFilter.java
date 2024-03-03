package com.example.EcommerceSpringBootProject.security;

import com.example.EcommerceSpringBootProject.entity.Role;
import com.example.EcommerceSpringBootProject.entity.User;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    private  JwtUtils jwtUtils;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!hasAuthorizationBearer(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = getAccessToken(request);

        if (!jwtUtils.validateAccessToken(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        setAuthenticationContext(token, request);
        filterChain.doFilter(request, response);
    }

    private Boolean hasAuthorizationBearer(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        if(ObjectUtils.isEmpty(header) || !header.startsWith("Bearer")){
            return false;
        }
        return true;
    }
    private void setAuthenticationContext(String token ,HttpServletRequest request){
        UserDetails userDetails = getUserDetails(token);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    public UserDetails getUserDetails(String token){
        User user = new User();
        Claims claims = jwtUtils.parseClaims(token);
        String subject = (String) claims.get(Claims.SUBJECT);
        String roles = (String) claims.get("roles");
        roles = roles.replace("[", "").replace("]", "");
        String[] roleNames = roles.split(",");

        for (String aRoleName : roleNames) {
            user.addRole(new Role(aRoleName));
        }

        String[] jwtSubject = subject.split(",");

        user.setId(Long.parseLong(jwtSubject[0]));
        user.setEmail(jwtSubject[1]);

        return user;
    }

    private String getAccessToken(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        String token = header.split(" ")[1].trim();
        return token;
    }
}
