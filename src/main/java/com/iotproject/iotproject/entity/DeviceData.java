package com.iotproject.iotproject.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DeviceData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(
            nullable = false
    )
    private float value1;

    @Column(
            nullable = false
    )
    private String value2;

    @Column(
            nullable = false
    )
    private String value3;

    @Column(
            nullable = false
    )
    private String value4;

    @Column(
            nullable = false
    )
    private Date date;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "device_id",
            referencedColumnName = "id",
            nullable = false,
            updatable = false
    )
    private Device device;
}
