package com.iotproject.iotproject.controller;

import com.iotproject.iotproject.dto.RoleDTO;
import com.iotproject.iotproject.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class UserControllerTest {

    @Autowired
    private UserService userService;

    @Test
    void saveRole() {
        userService.saveRole(RoleDTO.builder()
                .id(1L)
                .name("USER")
                .build());
        userService.saveRole(RoleDTO.builder()
                .id(2L)
                .name("WATER_BOARD")
                .build());
    }
}