package com.example.mall.admin.mapper;

import com.example.mall.admin.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 Mapper 接口
 * </p>
 *
 * @author D_Richard
 * @since 2021-04-05
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
    List<SysRole> getRoleByAdminID(Long adminid);
}
