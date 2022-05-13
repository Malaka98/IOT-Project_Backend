package com.iotproject.iotproject.util.DtoConverter;

import com.iotproject.iotproject.dto.UserDTO;
import com.iotproject.iotproject.entity.User;
import com.iotproject.iotproject.exception.UnknownException;

import java.util.ArrayList;
import java.util.List;

public class UserDtoConverter {

    public static User dtoToUser(UserDTO userDTO) {
        if (userDTO != null) {
            return User.builder()
                    .id(userDTO.getId())
                    .name(userDTO.getName())
                    .username(userDTO.getUsername())
                    .password(userDTO.getPassword())
                    .address(userDTO.getAddress())
                    .email(userDTO.getEmail())
                    .role(RoleDtoConverter.dtoRoleListToRole(userDTO.getRole()))
                    .deviceId(userDTO.getDeviceId())
                    .build();
        } else {
            throw new UnknownException(String.format("Unknown : %s entity\n", userDTO.getClass().getName()));
        }
    }

    public static UserDTO userToUserDTO(User user) {
        if (user != null) {
            return UserDTO.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .address(user.getAddress())
                    .email(user.getEmail())
                    .role(RoleDtoConverter.roleListToRoleDto(user.getRole()))
                    .deviceId(user.getDeviceId())
                    .build();
        } else {
            throw new UnknownException(String.format("Unknown : %s entity\n", user.getClass().getName()));
        }
    }

    public static List<User> userListToUserDTOList(List<UserDTO> userDTOS) {
        if (userDTOS != null) {
            List<User> userList = new ArrayList<>();
            for (UserDTO userDTO : userDTOS) {
                userList.add(UserDtoConverter.dtoToUser(userDTO));
            }
            return userList;
        } else {
            throw new UnknownException(String.format("Unknown : %s entity\n", userDTOS.getClass().getName()));
        }
    }

    public static List<UserDTO> userDTOListToUserList(List<User> users) {
        if (users != null) {
            List<UserDTO> userDTOS = new ArrayList<>();
            for (User user : users) {
                userDTOS.add(UserDtoConverter.userToUserDTO(user));
            }
            return userDTOS;
        } else {
            throw new UnknownException(String.format("Unknown : %s entity\n", users.getClass().getName()));
        }
    }
}
