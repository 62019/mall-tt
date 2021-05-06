package com.example.mall.admin.service;

import com.example.mall.admin.entity.SysAdmin;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author D_Richard
 * @since 2021-04-05
 */
public interface SysAdminService extends IService<SysAdmin> {

    public String login(String u,String p);

    //    获得返回userdetail类型 token要用
    UserDetails loadUserDetail(String username);

    //根据名字查id   有bug 通过Principal查询id
    SysAdmin getAdminByUsername(String namename);

//    boolean allocRoles(Long adminid, List<Long> roles);
}
