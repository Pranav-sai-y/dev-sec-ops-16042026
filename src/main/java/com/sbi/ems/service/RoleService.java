package com.sbi.ems.service;

import com.sbi.ems.dto.role.RoleRequest;
import com.sbi.ems.dto.role.RoleResponse;
import java.util.List;

public interface RoleService {
    List<RoleResponse> getAllRoles();
    RoleResponse       getRoleById(Long id);
    RoleResponse       createRole(RoleRequest request);
    RoleResponse       updateRole(Long id, RoleRequest request);
    void               deleteRole(Long id);
}
