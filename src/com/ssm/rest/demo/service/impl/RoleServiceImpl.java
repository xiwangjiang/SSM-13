package com.ssm.rest.demo.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.rest.demo.dao.IRoleDao;
import com.ssm.rest.demo.entity.Role;
import com.ssm.rest.demo.service.IResourceService;
import com.ssm.rest.demo.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {
	
	@Autowired
	private IRoleDao roleDao;
	@Autowired
	private IResourceService resourceService;

	@Override
	public Role createRole(Role role) {
		roleDao.insert(role);
		return role;
	}

	@Override
	public Role updateRole(Role role) {
		roleDao.updateByPrimaryKey(role);
		return role;
	}

	@Override
	public void deleteRole(Long roleId) {
		roleDao.deleteByPrimaryKey(roleId);
	}

	@Override
	public Role findOne(Long roleId) {
		return roleDao.selectByPrimaryKey(roleId);
	}

	@Override
	public List<Role> findAll() {
		return roleDao.findAll();
	}

	@Override
	public Set<String> findRoles(Long... roleIds) {
		Set<String> roles = new HashSet<String>();
        for(Long roleId : roleIds) {
            Role role = findOne(roleId);
            if(role != null) {
                roles.add(role.getRole());
            }
        }
        return roles;
	}

	@Override
	public Set<String> findPermissions(Long[] roleIds) {
		Set<Long> resourceIds = new HashSet<Long>();
        for(Long roleId : roleIds) {
            Role role = findOne(roleId);
            if(role != null) {
                resourceIds.addAll(role.getResourceIds());
            }
        }
        return resourceService.findPermissions(resourceIds);
	}

}
