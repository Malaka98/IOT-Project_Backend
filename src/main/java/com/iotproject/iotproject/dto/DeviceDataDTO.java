package com.iotproject.iotproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DeviceDataDTO {
    private String apiKey;
    private String value1;
    private String value2;
    private String value3;
    private String value4;
}
