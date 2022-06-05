package com.iotproject.iotproject.service.impl;

import com.iotproject.iotproject.dto.DeviceDTO;
import com.iotproject.iotproject.entity.Device;
import com.iotproject.iotproject.entity.Role;
import com.iotproject.iotproject.entity.User;
import com.iotproject.iotproject.repository.IDeviceRepository;
import com.iotproject.iotproject.repository.IRoleRepository;
import com.iotproject.iotproject.repository.IUserRepository;
import com.iotproject.iotproject.service.IDeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DeviceService implements IDeviceService {
    private final IDeviceRepository iDeviceRepository;
    private final IUserRepository iUserRepository;
    private final IRoleRepository roleRepository;

    @Override
    public Device addDevice(DeviceDTO deviceDTO) {
        return iDeviceRepository.save(Device.builder()
                .deviceId(deviceDTO.getDeviceId())
                .build());
    }

    @Override
    public String deviceOn(String deviceId, String username) {

        User user = iUserRepository.findUserByUsername(username);
        Role role = roleRepository.findByName("WATER_BOARD");

        Device device = iDeviceRepository.findByDeviceId(deviceId);

        if (user.getRole().contains(role)) {

            device.setAdminOnOff(true);
        } else {
            device.setUserOnOff(true);
        }

        iDeviceRepository.save(device);
        return "1";
    }

    @Override
    public String deviceOff(String deviceId, String username) {
        User user = iUserRepository.findUserByUsername(username);
        Role role = roleRepository.findByName("WATER_BOARD");

        Device device = iDeviceRepository.findByDeviceId(deviceId);

        if (user.getRole().contains(role)) {
            device.setUserOnOff(false);
            device.setAdminOnOff(false);
        } else {
            device.setUserOnOff(false);
        }

        iDeviceRepository.save(device);
        return "0";
    }

    @Override
    public String deviceStatus(String deviceId) {
        return null;
    }
}
