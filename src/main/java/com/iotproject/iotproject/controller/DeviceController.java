package com.iotproject.iotproject.controller;

import com.iotproject.iotproject.dto.DeviceDataDTO;
import com.iotproject.iotproject.service.impl.DeviceDataService;
import com.iotproject.iotproject.service.impl.DeviceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class DeviceController {

    private final DeviceDataService deviceDataService;
    private final DeviceService deviceService;

    @PostMapping("/get_data")
    public ResponseEntity<String> setDeviceData(@RequestBody DeviceDataDTO deviceDataDTO) {
        log.info(deviceDataDTO.toString());

        return ResponseEntity.ok().body(deviceDataService.saveDeviceData(deviceDataDTO));
    }

    @GetMapping("/get_data")
    public ResponseEntity<?> getDataToDevice(@RequestParam("deviceId") String deviceId) {
        log.info("===================>>>>>>>>>>>>>>************ " + deviceId);
        return ResponseEntity.ok().body(deviceService.ChackStatus(deviceId));
    }

    @GetMapping("/device_on/{deviceId}")
    public ResponseEntity<?> deviceOn(@PathVariable String deviceId, HttpServletRequest request) {
        String userName = (String) request.getSession().getAttribute("USER_NAME");

        return ResponseEntity.ok().body(deviceService.deviceOn(deviceId, userName));
    }

    @GetMapping("/device_off/{deviceId}")
    public ResponseEntity<?> deviceOff(@PathVariable String deviceId, HttpServletRequest request) {
        String userName = (String) request.getSession().getAttribute("USER_NAME");

        return ResponseEntity.ok().body(deviceService.deviceOff(deviceId, userName));
    }

}
