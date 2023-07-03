package com.pms.PMS.Config;

import com.pms.PMS.Entity.UserDtls;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

public class CustomUserDtls implements UserDetails {

    private UserDtls u;

    public CustomUserDtls(UserDtls userDtls) {
        super();
        this.u = userDtls;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        HashSet<SimpleGrantedAuthority> set = new HashSet<SimpleGrantedAuthority>();
        set.add(new SimpleGrantedAuthority(u.getRole()));
        return set;
    }

    @Override
    public String getPassword() {
        return u.getPassword();
    }

    @Override
    public String getUsername() {
        return u.getUsername();
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
