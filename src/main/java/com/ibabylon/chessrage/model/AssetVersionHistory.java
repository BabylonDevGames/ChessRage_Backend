package com.ibabylon.chessrage.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity(name="`AssetVersionHistory`")
public class AssetVersionHistory {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="asset_id")
    private AssetVersion asset;

    @Column(name="version",length = 100,nullable = false)
    private String version;

    @Column(name = "created_at",columnDefinition = "timestamptz not null",updatable = false)
    private ZonedDateTime created_at = ZonedDateTime.now(ZoneOffset.UTC);

    @Column(name="src_link",length = 500,nullable = false)
    private String src_link;
}
