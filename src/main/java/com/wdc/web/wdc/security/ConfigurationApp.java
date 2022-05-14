package com.wdc.web.wdc.security;

import com.wdc.web.wdc.jwt.JwtTokenVerifier;
import com.wdc.web.wdc.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import com.wdc.web.wdc.security.provider.CustomUsernamePasswordProvider;
import com.wdc.web.wdc.services.UserPrincipalService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class ConfigurationApp extends WebSecurityConfigurerAdapter {
    private final UserPrincipalService userPrincipalService;
    private final CustomUsernamePasswordProvider customUsernamePasswordProvider;
    public ConfigurationApp(UserPrincipalService userPrincipalService, CustomUsernamePasswordProvider customUsernamePasswordProvider) {
        this.userPrincipalService = userPrincipalService;
        this.customUsernamePasswordProvider = customUsernamePasswordProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                authenticationProvider(customUsernamePasswordProvider);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter( userPrincipalService,authenticationManager() ))
                .addFilterAfter(new JwtTokenVerifier(),JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeHttpRequests()
                .mvcMatchers("/login").permitAll()
                .mvcMatchers("/api/v1/auth/**").permitAll()
                .mvcMatchers("/api/v1/responsable/**").hasRole("RESPONSABLE")
                .mvcMatchers("/api/v1/admin/**").hasRole("ADMIN")
                .mvcMatchers("/api/v1/participant/**").hasRole("PARTICIPANT")
                .anyRequest()
                .authenticated();
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    ModelMapper modelMapper(){
        return new ModelMapper();
    }



}