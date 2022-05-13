package com.iotproject.iotproject.service.impl;

import com.iotproject.iotproject.dto.DeviceDataDTO;
import com.iotproject.iotproject.entity.Device;
import com.iotproject.iotproject.entity.DeviceData;
import com.iotproject.iotproject.exception.BadRequestException;
import com.iotproject.iotproject.repository.IDeviceDataRepository;
import com.iotproject.iotproject.repository.IDeviceRepository;
import com.iotproject.iotproject.service.IDeviceDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class DeviceDataService implements IDeviceDataService {

    private final IDeviceDataRepository iDeviceDataRepository;
    private final IDeviceRepository iDeviceRepository;

    @Override
    public String saveDeviceData(DeviceDataDTO deviceDataDTO) {
        try {

            String response = "1";

            if (deviceDataDTO.getValue3().equals("1") || deviceDataDTO.getValue4().equals("1")) {
                response = "0";
                Device device = iDeviceRepository.findByDeviceId(deviceDataDTO.getApiKey());
                device.setAdminOnOff(true);
                device.setUserOnOff(true);
                iDeviceRepository.save(device);
            }
            Device device = iDeviceRepository.findByDeviceId(deviceDataDTO.getApiKey());

            if (device.isAdminOnOff()) {
                response = "0";
            }

            iDeviceDataRepository.save(DeviceData.builder()
                    .value1(deviceDataDTO.getValue1())
                    .value2(deviceDataDTO.getValue2())
                    .value3(deviceDataDTO.getValue3())
                    .value4(deviceDataDTO.getValue4())
                    .date(new Date())
                    .device(device)
                    .build());

            return response;
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
