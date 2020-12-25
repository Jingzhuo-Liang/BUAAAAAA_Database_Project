package com.evan.demo.dao;

import com.evan.demo.pojo.AdminMenu;
import com.evan.demo.pojo.AdminRoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminMenuDAO extends JpaRepository<AdminMenu, Integer> {

    AdminMenu findById(int id);
    List<AdminMenu> findAllByParentId(int parentId);
    List<AdminMenu> findAllById(int id);
}
