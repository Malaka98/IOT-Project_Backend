package com.iotproject.iotproject.controller;

import com.iotproject.iotproject.dto.DeviceDataDTO;
import com.iotproject.iotproject.service.impl.DeviceDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class DeviceController {

    private final DeviceDataService deviceDataService;

    @PostMapping("/get_data")
    public ResponseEntity<String> setDeviceData(@RequestBody DeviceDataDTO deviceDataDTO) {
        log.info(deviceDataDTO.toString());

        return ResponseEntity.ok().body(deviceDataService.saveDeviceData(deviceDataDTO));
    }

    @GetMapping("/get_data")
    public ResponseEntity<?> getDataToDevice() {
        return ResponseEntity.ok().body("1");
    }

}
