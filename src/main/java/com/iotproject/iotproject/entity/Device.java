package com.iotproject.iotproject.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(
            unique = true,
            nullable = false
    )
    private String deviceId;

    private boolean userOnOff = true;

    private boolean adminOnOff = true;

}
