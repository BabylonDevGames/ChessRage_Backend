package com.ibabylon.chessrage.service;

import com.ibabylon.chessrage.dto.common.AssetVersionItem;
import com.ibabylon.chessrage.dto.common.MissingAssetItem;
import com.ibabylon.chessrage.model.AssetVersion;
import com.ibabylon.chessrage.model.AssetVersionHistory;
import com.ibabylon.chessrage.repository.AssetVersionRepository;
import org.apache.maven.artifact.versioning.ComparableVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AssetVersionImpl implements AssetVersionService {

    @Autowired
    private AssetVersionRepository assetVersionRepository;

    @Override
    public List<MissingAssetItem> getMissingAssets(List<AssetVersionItem> assets) {

        List<AssetVersion> currentAssetVersions = assetVersionRepository.findAll();

        List<MissingAssetItem> result = new ArrayList<MissingAssetItem>();

        List<AssetVersion> missingAssets = currentAssetVersions.stream()
                .filter(x -> assets.stream()
                        .allMatch(a -> a.getId().equals(x.getId()) && !a.getVersion().equals(x.getVersion()))
                        || !assets.stream().allMatch(a -> x.getId().equals(a.getId()))
                )
                .collect(Collectors.toList());

        List<AssetVersionHistory> assetHistories = missingAssets.stream().flatMap(x -> x.getAssetVersionHistories().stream()).collect(Collectors.toList());

        for (AssetVersionHistory item : assetHistories) {
            Optional<AssetVersionItem> assetVersion = assets.stream().filter(x -> x.getId().equals(item.getAsset().getId())).findAny();

            if (assetVersion.isPresent()) {
                if (compareVersions(assetVersion.get().getVersion(), item.getVersion())) {
                    result.add(new MissingAssetItem(item.getId(), item.getAsset().getId(), item.getVersion(), item.getSrc_link()));
                }
            } else {
                result.add(new MissingAssetItem(item.getId(), item.getAsset().getId(), item.getVersion(), item.getSrc_link()));
            }
        }

        return result;
    }

    private Boolean compareVersions(String neededV, String currentV) {
        ComparableVersion needed = new ComparableVersion(neededV);
        ComparableVersion current = new ComparableVersion(currentV);

        return current.compareTo(needed) > 0;
    }

}
