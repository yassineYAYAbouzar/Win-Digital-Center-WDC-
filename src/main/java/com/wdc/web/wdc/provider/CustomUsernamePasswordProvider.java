package com.wdc.web.wdc.provider;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomUsernamePasswordProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public CustomUsernamePasswordProvider(@Lazy UserDetailsService userDetailsService,@Lazy PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());

        UserDetails u = userDetailsService.loadUserByUsername(username);

        if (u == null) {
            if (passwordEncoder.matches(password, u.getPassword())) {
                var a = new UsernamePasswordAuthenticationToken(username, password, u.getAuthorities());
                return a;
            }
        }
        throw new BadCredentialsException("Error!");
    }

    @Override
    public boolean supports(Class<?> authType) { // type of Authentication
        return UsernamePasswordAuthenticationToken.class.equals(authType);
    }

}
