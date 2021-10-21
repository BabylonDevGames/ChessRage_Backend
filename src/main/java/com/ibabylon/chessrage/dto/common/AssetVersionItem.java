package com.ibabylon.chessrage.dto.common;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class AssetVersionItem {

    private UUID id;

    private String version;

}
