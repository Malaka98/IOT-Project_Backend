package com.iotproject.iotproject.repository;

import com.iotproject.iotproject.entity.Device;
import com.iotproject.iotproject.entity.DeviceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface IDeviceDataRepository extends JpaRepository<DeviceData, Long> {
//    float countAllByValue1AndDeviceIdEquals(String value1, Long device_id);
    @Query(
            "SELECT SUM(dd.value1) FROM DeviceData dd WHERE dd.device = ?1"
    )
    float getTotalValue1ByDaviceId(Device device);
}
