package com.iotproject.iotproject.service.impl;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.iotproject.iotproject.dto.ResponseUserDTO;
import com.iotproject.iotproject.dto.RoleDTO;
import com.iotproject.iotproject.dto.UserDTO;
import com.iotproject.iotproject.entity.Device;
import com.iotproject.iotproject.entity.Role;
import com.iotproject.iotproject.entity.User;
import com.iotproject.iotproject.exception.BadRequestException;
import com.iotproject.iotproject.exception.NotFoundException;
import com.iotproject.iotproject.repository.IDeviceRepository;
import com.iotproject.iotproject.repository.IRoleRepository;
import com.iotproject.iotproject.repository.IUserRepository;
import com.iotproject.iotproject.security.CustomUserDetails;
import com.iotproject.iotproject.service.IUserService;
import com.iotproject.iotproject.util.DtoConverter.RoleDtoConverter;
import com.iotproject.iotproject.util.DtoConverter.UserDtoConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService implements IUserService, UserDetailsService {

    private final IRoleRepository roleRepository;
    private final IUserRepository iUserRepository;
    private final PasswordEncoder passwordEncoder;

    private final IDeviceRepository iDeviceRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            User user = iUserRepository.findUserByUsername(username);
            if (user == null) {
                log.error("User not found in the database");
                throw new UsernameNotFoundException("User not found in the database");
            } else {
                log.info("User found in the database");
            }

            return new CustomUserDetails(user);
        } catch (Exception ex) {
            throw new NotFoundException(ex.getMessage() + " ⚠⚠⚠");
        }
    }


    @Override
    public RoleDTO saveRole(RoleDTO role) {

        try {
            return RoleDtoConverter.roleToDto(roleRepository.save(RoleDtoConverter.dtoToRole(role)));
        } catch (Exception ex) {
            throw new BadRequestException(ex.getMessage() + " ⚠⚠⚠");
        }
    }

    @Override
    public Collection<RoleDTO> getAllRoles() {
        try {
            return RoleDtoConverter.roleListToRoleDto(roleRepository.findAll());
        } catch (Exception ex) {
            throw new BadRequestException(ex.getMessage() + " ⚠⚠⚠");
        }
    }

    @Override
    public Collection<RoleDTO> saveAllRole(Collection<RoleDTO> roleDTOS) {
        try {
            return RoleDtoConverter.roleListToRoleDto(roleRepository.saveAll(RoleDtoConverter.dtoRoleListToRole(roleDTOS)));
        } catch (Exception ex) {
            throw new BadRequestException(ex.getMessage() + " ⚠⚠⚠");
        }
    }

    @Override
    @Transactional
    public UserDTO saveUser(UserDTO userDTO) {

        try {
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            User user = iUserRepository.save(UserDtoConverter.dtoToUser(userDTO));
            iDeviceRepository.save(Device.builder()
                    .deviceId(user.getDeviceId())
                    .build());
            return UserDtoConverter.userToUserDTO(user);
        } catch (Exception ex) {
            throw new BadRequestException(ex.getMessage() + " ⚠⚠⚠");
        }
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {

        try {
            User user = iUserRepository.findUserByUsername(userName);

            if (user == null) {
                log.warn(userName + " User Not Found");
                throw new NotFoundException(userName + " User Not Found ⚠⚠⚠");
            }

            Role role = roleRepository.findByName(roleName);
            user.getRole().add(role);
        } catch (Exception ex) {
            throw new BadRequestException(ex.getMessage());
        }
    }

    @Override
    public JsonArray getAllUsers() {

        try {
            JsonArray userList = new JsonArray();


            List<UserDTO> userDTOS = UserDtoConverter.userDTOListToUserList(iUserRepository.findAll());

            userDTOS.forEach(userDTO -> {
                JsonObject jsonObject = new JsonObject();
                Device device = iDeviceRepository.findByDeviceId(userDTO.getDeviceId());

                jsonObject.addProperty("name", userDTO.getName());
                jsonObject.addProperty("username", userDTO.getUsername());
                jsonObject.addProperty("email", userDTO.getEmail());
                jsonObject.addProperty("address", userDTO.getAddress());
                jsonObject.addProperty("deviceId", userDTO.getDeviceId());
                jsonObject.addProperty("status", device.isUserOnOff() ? "Off" : "ON");

                userList.add(jsonObject);
            });

            return userList;
        } catch (Exception ex) {
            throw new BadRequestException(ex.getMessage() + " ⚠⚠⚠");
        }
    }

    @Override
    @Transactional
    public User deleteUser(String userName) {

        try {
            User user = iUserRepository.findUserByUsername(userName);
            iUserRepository.delete(user);

            return user;
        } catch (Exception ex) {
            throw new BadRequestException(ex.getMessage() + " ⚠⚠⚠");
        }
    }

    @Override
    @Transactional
    public void updateUser(String UserName, ResponseUserDTO userDTO) {

        try {
            UserDTO user = UserDtoConverter.userToUserDTO(iUserRepository.findUserByUsername(UserName));
            iUserRepository.save(UserDtoConverter.dtoToUser(UserDTO.builder()
                    .id(user.getId())
                    .name(userDTO.getName())
                    .username(userDTO.getUsername())
                    .password(user.getPassword())
                    .email(userDTO.getEmail())
                    .address(userDTO.getAddress())
                    .role(userDTO.getRole())
                    .build()));

        } catch (Exception ex) {
            throw new BadRequestException(ex.getMessage() + " ⚠⚠⚠");
        }
    }
}