package com.example.mall.admin.mapper;

import com.example.mall.admin.entity.SysResource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 后台资源表 Mapper 接口
 * </p>
 *
 * @author D_Richard
 * @since 2021-04-05
 */
public interface SysResourceMapper extends BaseMapper<SysResource> {
    List<SysResource> getResByAdminId(Long adminid);
}
