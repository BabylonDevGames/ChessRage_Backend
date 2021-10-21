package com.ibabylon.chessrage.controller.request;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.ibabylon.chessrage.dto.common.AssetVersionItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetMissingAssetsRq {

    private List<AssetVersionItem> assets;

    public GetMissingAssetsRq(){
        assets = new ArrayList<AssetVersionItem>();
    }
}



