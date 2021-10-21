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
@Table(name = "`LookUp`")
public class LookUp extends BaseEntity {

    @Column(name = "id",updatable = false, nullable = false)
    private UUID id;

    @Column(name="name",length = 100,nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "lookUp")
    private Set<LookUpItem> lookupItems;
}
