package com.ibabylon.chessrage.security;


import com.google.api.core.ApiFuture;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.ibabylon.chessrage.security.model.FirebaseAuthToken;
import com.ibabylon.chessrage.security.model.FirebaseUserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class FirebaseAuthProvider extends AbstractUserDetailsAuthenticationProvider {

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    public boolean supports(Class<?> authentication){
        return (FirebaseAuthToken.class.isAssignableFrom(authentication));
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {}

    @Override
    protected UserDetails retrieveUser(String email, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        FirebaseAuthToken authToken = (FirebaseAuthToken) authentication;

        ApiFuture<FirebaseToken> task = firebaseAuth.verifyIdTokenAsync(authToken.getToken());

        try {
            FirebaseToken token = task.get();
            return new FirebaseUserDetails(token.getEmail(), token.getUid());
        } catch (InterruptedException | ExecutionException e) {
            throw new SessionAuthenticationException(e.getMessage());
        }
    }
}
