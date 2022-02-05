package com.ibabylon.chessrage.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "`SandBoxUser`")
public class SandBoxUser {

    @Id
//    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(
//            name = "UUID",
//            strategy = "org.hibernate.id.UUIDGenerator"
//    )
    @Column(name = "id",updatable = false, nullable = false)
    private String id;

    @Column(name="email",length = 50,nullable = false)
    private String email;

    @Column(name = "created_at",columnDefinition = "timestamptz not null")
    private ZonedDateTime created_at = ZonedDateTime.now(ZoneOffset.UTC);


}
