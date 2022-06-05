package com.iotproject.iotproject.service.impl;

import com.iotproject.iotproject.entity.SideNavItem;
import com.iotproject.iotproject.entity.User;
import com.iotproject.iotproject.repository.ISideNavItemRepository;
import com.iotproject.iotproject.repository.IUserRepository;
import com.iotproject.iotproject.service.ISettingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class SettingsService implements ISettingsService {

    private final ISideNavItemRepository iSideNavItemRepository;
    private final IUserRepository iUserRepository;

    @Override
    public Collection<SideNavItem> getSideNavItemByUser(String userName) {

        User user = iUserRepository.findUserByUsername(userName);

        return iSideNavItemRepository.findAllByRolesIn(user.getRole());
    }
}
