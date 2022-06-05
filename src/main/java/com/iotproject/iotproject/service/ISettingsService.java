package com.iotproject.iotproject.service;

import com.iotproject.iotproject.entity.SideNavItem;

import java.util.Collection;

public interface ISettingsService {
    Collection<SideNavItem> getSideNavItemByUser(String userName);
}
