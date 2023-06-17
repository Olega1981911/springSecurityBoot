package com.project.springsecurityboot.security;


import com.project.springsecurityboot.models.Role;
import com.project.springsecurityboot.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class PersonDetails implements UserDetails {
    private final User user;

    public PersonDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        //return Collections.singletonList(new SimpleGrantedAuthority(user.getRoles().toString() + user.getName()));
        Set<SimpleGrantedAuthority> set = new HashSet<>();
        for (Role role : user
                .getRoles()) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.getName());
            set.add(simpleGrantedAuthority);
        }
        return set;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getLogin();
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

    //Получение даных атентифицированных пользователей
    public User getUser() {
        return this.user;
    }
}
