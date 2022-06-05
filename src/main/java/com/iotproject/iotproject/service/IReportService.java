package com.iotproject.iotproject.service;

public interface IReportService {
    float getTotalUnitByDay(String deviceId);

    float getTotalUnit(String deviceId);
}
