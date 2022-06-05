package com.iotproject.iotproject.service.impl;

import com.iotproject.iotproject.entity.Device;
import com.iotproject.iotproject.exception.UnknownException;
import com.iotproject.iotproject.repository.IDeviceDataRepository;
import com.iotproject.iotproject.repository.IDeviceRepository;
import com.iotproject.iotproject.service.IReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportService implements IReportService {

    private final IDeviceDataRepository iDeviceDataRepository;
    private final IDeviceRepository iDeviceRepository;

    @Override
    public float getTotalUnitByDay(String deviceId) {

        try {
            Device device = iDeviceRepository.findByDeviceId(deviceId);

            return iDeviceDataRepository.getTotalValue1ByDaviceId(device);
        } catch (Exception e) {
            throw new UnknownException(e.getMessage());
        }
    }

    @Override
    public float getTotalUnit(String deviceId) {

        try {
            Device device = iDeviceRepository.findByDeviceId(deviceId);

            return iDeviceDataRepository.getTotalValue1ByDaviceId(device);
        } catch (Exception e) {
            throw new UnknownException(e.getMessage());
        }
    }
}
