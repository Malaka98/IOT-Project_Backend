package com.iotproject.iotproject.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ReportServiceTest {

    @Autowired
    private ReportService reportService;

    @Test
    void getTotalUnitByDay() {
        log.info("======================+>>>>>>>>>>>>>>" + reportService.getTotalUnitByDay("ABC-1"));
    }
}