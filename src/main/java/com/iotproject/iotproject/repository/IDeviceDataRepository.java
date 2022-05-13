package com.iotproject.iotproject.repository;

import com.iotproject.iotproject.entity.DeviceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDeviceDataRepository extends JpaRepository<DeviceData, Long> {
}
