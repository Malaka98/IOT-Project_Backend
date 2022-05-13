package com.iotproject.iotproject.service;

import com.iotproject.iotproject.dto.DeviceDataDTO;
import com.iotproject.iotproject.entity.DeviceData;

public interface IDeviceDataService {
    String saveDeviceData(DeviceDataDTO deviceDataDTO);
}
