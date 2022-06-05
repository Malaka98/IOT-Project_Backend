package com.iotproject.iotproject.repository;

import com.iotproject.iotproject.entity.Role;
import com.iotproject.iotproject.entity.SideNavItem;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ISideNavItemRepositoryTest {

    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private ISideNavItemRepository iSideNavItemRepository;

    @Test
    void addSideNavItem() {
        Collection<Role> roles = new ArrayList<>();

        Role role = roleRepository.findByName("USER");
        Role role2 = roleRepository.findByName("POLICE_USER");

        roles.add(role);
        roles.add(role2);

        SideNavItem sideNavItem = iSideNavItemRepository.save(SideNavItem.builder()
                .name("Add Report Accident")
                .label("Add Report Accident")
                .path("/app/accidentreport")
                .roles(roles)
                .build());

        SideNavItem sideNavItem2 = iSideNavItemRepository.save(SideNavItem.builder()
                .name("All Accident List")
                .label("All Accident List")
                .path("/app/accident_list")
                .roles(roles)
                .build());

        log.info("=============>>>>>>>>>>> " + sideNavItem);
    }

}