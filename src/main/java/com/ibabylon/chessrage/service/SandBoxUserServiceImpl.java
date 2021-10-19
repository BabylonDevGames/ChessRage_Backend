package com.ibabylon.chessrage.service;

import com.ibabylon.chessrage.model.SandBoxUser;
import com.ibabylon.chessrage.repository.SandBoxUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SandBoxUserServiceImpl implements SandBoxUserService{

    @Autowired
    private SandBoxUserRepository sandBoxUserRepository;

    @Override
    public Boolean userExist(String email) {
        SandBoxUser user = sandBoxUserRepository.findByEmail(email);
        return user !=null;
    }
}
