package com.iotproject.iotproject.service.impl;

import com.iotproject.iotproject.dto.UserDTO;
import com.iotproject.iotproject.entity.Role;
import com.iotproject.iotproject.repository.IRoleRepository;
import com.iotproject.iotproject.service.IUserService;
import com.iotproject.iotproject.util.DtoConverter.RoleDtoConverter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class UserServiceTest {

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private IUserService userService;

    @Test
    void saveUser() {
        Role role = roleRepository.findByName("USER");
        Role role2 = roleRepository.findByName("WATER_BOARD");
        log.info("Role=============>" + role.getName());
        log.info("Role2=============>" + role2.getName());
        Collection<Role> roles = new ArrayList<>();
        roles.add(role);
        roles.add(role2);
        UserDTO userDTO = userService.saveUser(UserDTO.builder()
                .name("nadun")
                .username("nadun")
                .password("123")
                .email("nadun@gmail.com")
                .address("abc")
                        .deviceId("12345678")
                .role(RoleDtoConverter.roleListToRoleDto(roles))
                .build());

        assertInstanceOf(UserDTO.class, userDTO);
        assertNotNull(userDTO);

    }

}