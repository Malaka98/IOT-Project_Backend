package com.iotproject.iotproject.service;

import com.iotproject.iotproject.dto.DeviceDTO;
import com.iotproject.iotproject.entity.Device;

public interface IDeviceService {
    Device addDevice(DeviceDTO deviceDTO);
    String deviceOn(String deviceId);
}
