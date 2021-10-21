package com.ibabylon.chessrage.service;

import com.ibabylon.chessrage.dto.common.AssetVersionItem;
import com.ibabylon.chessrage.dto.common.MissingAssetItem;

import java.util.List;


public interface AssetVersionService {

    List<MissingAssetItem> getMissingAssets(List<AssetVersionItem> assets);
}
