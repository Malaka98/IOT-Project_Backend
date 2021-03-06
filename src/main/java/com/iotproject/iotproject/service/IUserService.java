package com.iotproject.iotproject.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.iotproject.iotproject.dto.ResponseUserDTO;
import com.iotproject.iotproject.dto.RoleDTO;
import com.iotproject.iotproject.dto.UserDTO;
import com.iotproject.iotproject.entity.User;

import java.util.Collection;
import java.util.List;

public interface IUserService {
    RoleDTO saveRole(RoleDTO role);

    Collection<RoleDTO> getAllRoles();

    Collection<RoleDTO> saveAllRole(Collection<RoleDTO> roleDTOS);

    UserDTO saveUser(UserDTO userDTO);

    void addRoleToUser(String userName, String roleName);

    JsonArray getAllUsers();

    User deleteUser(String userName);

    void updateUser(String UserName, ResponseUserDTO userDTO);

    UserDTO getUserByDeviceId(String deviceId);
}
