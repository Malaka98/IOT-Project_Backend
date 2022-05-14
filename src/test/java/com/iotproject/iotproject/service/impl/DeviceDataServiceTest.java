package com.iotproject.iotproject.service.impl;

import com.iotproject.iotproject.dto.DeviceDataDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class DeviceDataServiceTest {

    @Autowired
    private DeviceDataService deviceDataService;

    @Test
    void saveDeviceData() {
        deviceDataService.saveDeviceData(DeviceDataDTO.builder()
                        .apiKey("ABC-1")
                        .value1("3.02")
                        .value2("0")
                        .value3("0")
                        .value4("0")
                .build());
    }
}