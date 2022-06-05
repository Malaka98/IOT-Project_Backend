package com.iotproject.iotproject.repository;

import com.iotproject.iotproject.entity.Role;
import com.iotproject.iotproject.entity.SideNavItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ISideNavItemRepository extends JpaRepository<SideNavItem, Long> {
    Collection<SideNavItem> findAllByRolesIn(Collection<Role> roles);
//    SideNavItem findAllById(Long id);

}
