package com.ibabylon.chessrage.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "`LookUpItem`")

public class LookUpItem  extends  BaseEntity{

    @Column(name="name",length = 100,nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="lookup_id")
    private LookUp lookUp;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "type_lookUpItem")
    private Set<AssetVersion> assetVersions;
}
