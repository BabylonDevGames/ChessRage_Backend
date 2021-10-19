package com.ibabylon.chessrage.repository;

import com.ibabylon.chessrage.model.SandBoxUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SandBoxUserRepository extends JpaRepository<SandBoxUser, UUID> {

    SandBoxUser findByEmail(String email);
}