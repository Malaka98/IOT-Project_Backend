package com.iotproject.iotproject.repository;


import com.iotproject.iotproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String userName);
    User findUserByDeviceId(String deviceId);

    @Query(
            "SELECT COUNT(u)  FROM User u"
    )
    int getAllUsers();
}
