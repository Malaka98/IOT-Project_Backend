package com.iotproject.iotproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iotproject.iotproject.dto.ResponseUserDTO;
import com.iotproject.iotproject.dto.RoleDTO;
import com.iotproject.iotproject.dto.UserDTO;
import com.iotproject.iotproject.entity.User;
import com.iotproject.iotproject.service.impl.UserService;
import com.iotproject.iotproject.util.DtoConverter.RoleDtoConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/validate")
    public void validateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String> res = new HashMap<>();
        res.put("User", (String) request.getSession().getAttribute("USER_NAME"));
        new ObjectMapper().writeValue(response.getOutputStream(), res);
    }

    @PostMapping("/user")
    public ResponseEntity<ResponseUserDTO> saveUser(@RequestBody UserDTO user) {

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user").toUriString());
        UserDTO userDTO = userService.saveUser(user);
        return ResponseEntity.created(uri).body(ResponseUserDTO.builder()
                .id(userDTO.getId())
                .name(userDTO.getName())
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .address(userDTO.getAddress())
                .role(userDTO.getRole())
                .build());
    }

    @PutMapping("/user/{userName}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody ResponseUserDTO responseUserDTO, @PathVariable String userName) {

        userService.updateUser(userName, responseUserDTO);

        return ResponseEntity.ok().body(responseUserDTO);
    }

    @DeleteMapping("/user/{userName}")
    public ResponseEntity<?> deleteUser(@PathVariable String userName) {

        User user = userService.deleteUser(userName);

        return ResponseEntity.ok().body(ResponseUserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .address(user.getAddress())
                .role(RoleDtoConverter.roleListToRoleDto(user.getRole()))
                .build());
    }

    @PostMapping("/role")
    ResponseEntity<Collection<RoleDTO>> saveRole(@Valid @RequestBody Collection<RoleDTO> roleDTOS) {

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role").toUriString());
        return ResponseEntity.created(uri).body(userService.saveAllRole(
                RoleDtoConverter.roleListToRoleDto(RoleDtoConverter.dtoRoleListToRole(roleDTOS))));
    }

    @GetMapping("/role")
    ResponseEntity<Collection<RoleDTO>> getAllRoles() {

        return ResponseEntity.ok().body(userService.getAllRoles());
    }
}
