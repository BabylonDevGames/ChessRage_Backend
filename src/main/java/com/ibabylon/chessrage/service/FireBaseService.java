package com.ibabylon.chessrage.service;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

public interface FireBaseService {

    FirebaseToken decodeToken(String token) throws FirebaseAuthException;

}
