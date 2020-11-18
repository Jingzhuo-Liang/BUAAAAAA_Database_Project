package com.evan.demo.dao;

import com.evan.demo.pojo.AdminUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminUserRoleDAO extends JpaRepository<AdminUserRole, Integer> {
    List<AdminUserRole> findAllByUid(int udi);
    void deleteAllByUid(int uid);
}
