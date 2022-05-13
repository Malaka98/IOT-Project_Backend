package com.iotproject.iotproject.repository;

import com.iotproject.iotproject.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDeviceRepository extends JpaRepository<Device, Long> {
    Device findByDeviceId(String deviceId);
}
