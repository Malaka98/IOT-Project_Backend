package com.iotproject.iotproject.service.impl;

import com.iotproject.iotproject.dto.DeviceDTO;
import com.iotproject.iotproject.entity.Device;
import com.iotproject.iotproject.repository.IDeviceRepository;
import com.iotproject.iotproject.service.IDeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeviceService implements IDeviceService {
    private final IDeviceRepository iDeviceRepository;

    @Override
    public Device addDevice(DeviceDTO deviceDTO) {
        return iDeviceRepository.save(Device.builder()
                .deviceId(deviceDTO.getDeviceId())
                .build());
    }

    @Override
    public String deviceOn(String deviceId) {

        Device device = iDeviceRepository.findByDeviceId(deviceId);
        device.setUserOnOff(false);
        device.setAdminOnOff(false);
        iDeviceRepository.save(device);
        return "0";
    }
}
