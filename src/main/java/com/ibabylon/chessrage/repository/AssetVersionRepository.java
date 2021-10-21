package com.ibabylon.chessrage.repository;

import com.ibabylon.chessrage.model.AssetVersion;

import org.springframework.data.jpa.repository.JpaRepository;



import java.util.UUID;

public interface AssetVersionRepository extends JpaRepository<AssetVersion, UUID> {


}