package com.evan.demo.dao;

import com.evan.demo.pojo.AdminRoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRoleMenuDAO extends JpaRepository<AdminRoleMenu,Integer> {
    List<AdminRoleMenu> findAllByRid(int id);
    List<AdminRoleMenu> findAllByRid(List<Integer> rids);
    void deleteAllByRid(int rid);
}
