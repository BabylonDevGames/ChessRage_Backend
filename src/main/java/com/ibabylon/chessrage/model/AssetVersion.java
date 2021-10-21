package com.ibabylon.chessrage.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name="\"AssetVersion\"")
public class AssetVersion {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(name="name",length = 100,nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="type_id")
    private LookUpItem type_lookUpItem;

    @Column(name="version",length = 50,nullable = false)
    private String version;

    @Column(name = "last_update_dt",columnDefinition = "timestamptz not null")
    private ZonedDateTime last_update_dt = ZonedDateTime.now(ZoneOffset.UTC);

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "asset")
    private Set<AssetVersionHistory> assetVersionHistories;

}
