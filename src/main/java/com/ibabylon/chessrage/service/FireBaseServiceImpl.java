package com.ibabylon.chessrage.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.stereotype.Component;

@Component
public class FireBaseServiceImpl implements  FireBaseService{

    private FirebaseAuth _firebase = FirebaseAuth.getInstance();

    @Override
    public FirebaseToken decodeToken(String token) throws FirebaseAuthException {
        return _firebase.verifyIdToken(token);
    }
}
