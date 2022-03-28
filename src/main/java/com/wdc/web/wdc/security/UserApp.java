package com.wdc.web.wdc.security;

import com.wdc.web.wdc.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserApp  implements UserDetails {
    private User user;

    public UserApp(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getAuthoritiesList().forEach(p -> {
            GrantedAuthority authority = new SimpleGrantedAuthority(p.getAuthoritiesName());
            authorities.add(authority);
        });
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+user.getRole().getName());
        authorities.add(authority);
        return authorities;
    }
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
