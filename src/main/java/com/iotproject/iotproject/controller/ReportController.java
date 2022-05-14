package com.iotproject.iotproject.controller;

import com.google.gson.JsonObject;
import com.iotproject.iotproject.service.impl.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/get_total_unit_by_day/{deviceID}")
    ResponseEntity<?> getTotalUnitByDay(@PathVariable String deviceID) {
        JsonObject response = new JsonObject();

        float value = reportService.getTotalUnitByDay(deviceID);
        response.addProperty("totalValue", String.valueOf(value));

        return ResponseEntity.ok().contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                .body(response);
    }
}
