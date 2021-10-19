package com.ibabylon.chessrage.security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class FirebaseUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;

    private final boolean enabled = true;
    private String email = null;
    private String id = null;

    public FirebaseUserDetails(String email,String uid){
        this.email = email;
        this.id = uid;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
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
        return enabled;
    }

}
