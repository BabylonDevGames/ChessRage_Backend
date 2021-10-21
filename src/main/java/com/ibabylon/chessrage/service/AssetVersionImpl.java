package com.ibabylon.chessrage.service;

import com.ibabylon.chessrage.dto.common.AssetVersionItem;
import com.ibabylon.chessrage.dto.common.MissingAssetItem;
import com.ibabylon.chessrage.model.AssetVersion;
import com.ibabylon.chessrage.model.AssetVersionHistory;
import com.ibabylon.chessrage.repository.AssetVersionHistoryRepository;
import com.ibabylon.chessrage.repository.AssetVersionRepository;
import org.apache.maven.artifact.versioning.ComparableVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class AssetVersionImpl implements AssetVersionService {

    @Autowired
    AssetVersionHistoryRepository assetVersionHistoryRepository;
    @Autowired
    private AssetVersionRepository assetVersionRepository;

    @Override
    public List<MissingAssetItem> getMissingAssets(List<AssetVersionItem> assets) {

        List<AssetVersion> currentAssetVersions = assetVersionRepository.findAll();

        List<AssetVersion> missingAssets = currentAssetVersions.stream()
                .filter(x -> assets.stream()
                        .anyMatch(a -> a.getId().equals(x.getId()) && !a.getVersion().equals(x.getVersion())))
                .collect(Collectors.toList());

        List<UUID> missingAssetIds = missingAssets.stream().map(x -> x.getId()).collect(Collectors.toList());


        List<AssetVersionHistory> assetHistories = assetVersionHistoryRepository.getAllAssetVersionHistoriesByAssetIdS(missingAssetIds);

        List<MissingAssetItem> missingAssetItems = assetHistories.stream()
                .filter(x -> missingAssets.stream().anyMatch(a -> a.getId().equals(x.getAsset().getId())
                        && compareVersions(a.getVersion(), x.getVersion())
                )).map(a ->
                        new MissingAssetItem(a.getId(), a.getAsset().getId(), a.getVersion(), a.getSrc_link()))
                .collect(Collectors.toList());


        return missingAssetItems;
    }


    private Boolean compareVersions(String neededV, String currentV) {
        ComparableVersion needed = new ComparableVersion(neededV);
        ComparableVersion current = new ComparableVersion(currentV);

        return current.compareTo(needed) >= 0;
    }

}
