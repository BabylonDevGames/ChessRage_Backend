package com.ibabylon.chessrage.dto.common;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class MissingAssetItem {

    private UUID id;

    private UUID asset_id;

    private String version;

    private  String src_link;

}
