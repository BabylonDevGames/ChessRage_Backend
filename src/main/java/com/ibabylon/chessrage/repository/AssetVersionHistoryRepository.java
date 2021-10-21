package com.ibabylon.chessrage.repository;

import com.ibabylon.chessrage.model.AssetVersionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface AssetVersionHistoryRepository extends JpaRepository<AssetVersionHistory, UUID> {

    @Query(
            value = "SELECT *  FROM \"AssetVersionHistory\"  WHERE asset_id IN :ids",
            nativeQuery = true)
    List<AssetVersionHistory> getAllAssetVersionHistoriesByAssetIdS(@Param("ids") List<UUID> ids);
}