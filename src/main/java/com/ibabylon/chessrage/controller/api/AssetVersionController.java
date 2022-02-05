package com.ibabylon.chessrage.controller.api;


import com.ibabylon.chessrage.controller.request.GetMissingAssetsRq;
import com.ibabylon.chessrage.dto.common.MissingAssetItem;
import com.ibabylon.chessrage.dto.response.BaseApiResponse;
import com.ibabylon.chessrage.helpers.ApiErrorMessageBuilder;
import com.ibabylon.chessrage.helpers.BaseApiErrorCodes;
import com.ibabylon.chessrage.service.AssetVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/assetversion")
public class AssetVersionController {

    @Autowired
    AssetVersionService assetVersionService;

    /**
     * Returns missing assets
     *
     * @param rq List of client current assets
     * @return List of assets with firestorage src links
     */
    @PostMapping("/GetMissingAssets")
    public BaseApiResponse GetMissingAssets(@RequestBody @Valid GetMissingAssetsRq rq) {

        BaseApiResponse res = new BaseApiResponse();

        try {
            List<MissingAssetItem> missingAssets = assetVersionService.getMissingAssets(rq.getAssets());
            
            res.setSuccess(true);
            res.setResult(missingAssets);
        } catch (Exception e) {
            ApiErrorMessageBuilder.buildErrorMessage(res, BaseApiErrorCodes.UnknownError);
        }

        return res;
    }
}
