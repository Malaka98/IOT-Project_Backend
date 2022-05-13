package com.iotproject.iotproject.service.impl;

import com.iotproject.iotproject.dto.DeviceDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class DeviceServiceTest {

    @Autowired
    private DeviceService deviceService;

    @Test
    void addDevice() {
        DeviceDTO deviceDTO = DeviceDTO.builder()
                .deviceId("ABC-1")
                .build();

        deviceService.addDevice(deviceDTO);
    }

    @Test
    void deviceOn() {
        String deviceId = "ABC-1";
        deviceService.deviceOn(deviceId);
    }
}