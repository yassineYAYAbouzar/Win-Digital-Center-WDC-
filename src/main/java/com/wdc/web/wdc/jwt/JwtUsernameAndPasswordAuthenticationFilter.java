package com.wdc.web.wdc.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wdc.web.wdc.entities.User;
import com.wdc.web.wdc.services.UserPrincipalService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final UserPrincipalService userPrincipalService;
    private final AuthenticationManager authenticationManager;


    public JwtUsernameAndPasswordAuthenticationFilter(UserPrincipalService userPrincipalService, AuthenticationManager authenticationManager) {
        this.userPrincipalService = userPrincipalService;
        this.authenticationManager = authenticationManager;
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        try {
            UsernameAndPasswordAuthenticationRequest authenticationRequest = new ObjectMapper()
                    .readValue(request.getInputStream(), UsernameAndPasswordAuthenticationRequest.class);
            Authentication authenticate = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()
            );

            Authentication authenticated = authenticationManager.authenticate(authenticate);

            return authenticated;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        String secureKey = "secureKeysecureKeysecureKeysecureKeysecureKeysecureKeysecureKey";
        User user = userPrincipalService.findUserByName(authResult.getName());
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .claim("id",user.getId())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))
                .signWith(Keys.hmacShaKeyFor(secureKey.getBytes()))
                .compact();


        response.addHeader("Authorization" , "Bearer " + token);
        response.getWriter().write("{\"token\": \"" + token + "\", \"id\": \""+ user.getId() + "\"}");
    }

}
