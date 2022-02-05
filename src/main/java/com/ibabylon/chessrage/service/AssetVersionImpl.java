package com.ibabylon.chessrage.service;

import com.ibabylon.chessrage.dto.common.AssetVersionItem;
import com.ibabylon.chessrage.dto.common.MissingAssetItem;
import com.ibabylon.chessrage.helpers.Constants;
import com.ibabylon.chessrage.model.AssetVersion;
import com.ibabylon.chessrage.model.AssetVersionHistory;
import com.ibabylon.chessrage.repository.AssetVersionRepository;
import org.apache.maven.artifact.versioning.ComparableVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
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
                ).collect(Collectors.toList());

        List<AssetVersionHistory> assetHistories = missingAssets.stream().flatMap(x -> x.getAssetVersionHistories().stream()).collect(Collectors.toList());

        for (AssetVersionHistory item : assetHistories) {
            Optional<AssetVersionItem> assetVersion = assets.stream().filter(x -> x.getId().equals(item.getAsset().getId())).findAny();

            if (assetVersion.isPresent()) {
                if (compareVersions(assetVersion.get().getVersion(), item.getVersion())) {
                    result.add(new MissingAssetItem(item.getId(), item.getAsset().getId(), item.getVersion(), item.getSrc_link(),item.getAsset().getType_lookUpItem().getId()));
                }
            } else {
                result.add(new MissingAssetItem(item.getId(), item.getAsset().getId(), item.getVersion(), item.getSrc_link(),item.getAsset().getType_lookUpItem().getId()));
            }
        }

        return sortResult(result);
    }

    private Boolean compareVersions(String neededV, String currentV) {
        ComparableVersion needed = new ComparableVersion(neededV);
        ComparableVersion current = new ComparableVersion(currentV);

        return current.compareTo(needed) > 0;
    }

    private List<MissingAssetItem> sortResult(List<MissingAssetItem> items){

        List<MissingAssetItem> databaseRecords = items.stream().filter(x->x.getAsset_type().equals(Constants.DYNAMIC_ASSET_DATABASE_RECORD_TYPE_ID)).collect(Collectors.toList());
        List<MissingAssetItem> otherAssets = items.stream().filter(x->!x.getAsset_type().equals(Constants.DYNAMIC_ASSET_DATABASE_RECORD_TYPE_ID)).collect(Collectors.toList());


        for(int i=0;i<databaseRecords.size()-1;i++){

            MissingAssetItem first = databaseRecords.get(i);
            MissingAssetItem second = databaseRecords.get(i+1);

            if(new ComparableVersion(first.getVersion()).compareTo(new ComparableVersion(second.getVersion()))>0){

                MissingAssetItem temp = second;

                second = first;

                first = temp;

            }
        }

        databaseRecords.addAll(otherAssets);

        return databaseRecords;
    }
}
