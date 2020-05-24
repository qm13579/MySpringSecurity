package cn.people.service.impl;

import cn.people.dao.DepMapper;
import cn.people.dao.RoleMapper;
import cn.people.dao.UserMapper;
import cn.people.domain.Role;
import cn.people.domain.vo.RoleVO;
import cn.people.service.RoleService;
import cn.people.utils.common.RoleUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private DepMapper depMapper;

    @Override
    public void increaseRole(RoleVO role) {

        role.setId(RoleUtils.getLower(role.getAuthority()));
        role.setAuthority(RoleUtils.getUpper(role.getAuthority()));
        roleMapper.increaseRole(role);
    }

    @Override
    public void updateRole(RoleVO role) {
        roleMapper.updateRole(role);
    }

    @Override
    public Role findRole(String rid) {

        return roleMapper.findRole(rid);
    }

    @Override
    public List<Role> findRoles() {

        return roleMapper.findRoles();
    }

    @Override
    public void deleteRoleById(String id) {
        if ("admin".equals(id)){
            return;
        }

        List<String> userFindRole = userMapper.userFindRole(id);
        if (userFindRole == null){
            roleMapper.deleteUserMRole(id);
        }
        long count = roleMapper.findRoleMDep(id);
        if (count > 0 ){
            roleMapper.deleteRoleMDep(id);
        }
        roleMapper.deleteRole(id);


    }

    @Override
    public void initRole() {
        long count = roleMapper.countRole();
        if (count > 0){
            return;
        }
        RoleVO roleAdmin = new RoleVO("admin", "ROLE_ADMIN", "管理员", "1");
        RoleVO roleUser = new RoleVO("user", "ROLE_USER", "一般用户", "3");
        RoleVO roleDepartment = new RoleVO("department", "ROLE_DEPARTMENT", "部门人员", "4");
        List<RoleVO> roleVOS = Arrays.asList(roleAdmin, roleUser, roleDepartment);

        roleVOS.forEach(roleMapper::increaseRole);
    }

    @Override
    public void creatJoinRoleForDep(RoleVO role) {
        roleMapper.deleteUserMRole(role.getId());
        role.getDeps().forEach(depVO -> {
            roleMapper.increaseRoleMDep(role.getId(),depVO.getId());
        });
    }
}
