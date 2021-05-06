package com.example.mall.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mall.admin.entity.SysAdmin;
import com.example.mall.admin.entity.SysResource;
import com.example.mall.admin.mapper.SysAdminMapper;
import com.example.mall.admin.mapper.SysAdminRoleRelationMapper;
import com.example.mall.admin.mapper.SysResourceMapper;
import com.example.mall.admin.mapper.SysRoleMapper;
import com.example.mall.admin.service.SysAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.exception.Asserts;
import com.example.security.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author D_Richard
 * @since 2021-04-05
 */
@Service
public class SysAdminServiceImpl extends ServiceImpl<SysAdminMapper, SysAdmin> implements SysAdminService {

    //好像不是bean啊 不应该new或者直接static
    @Resource
    JwtTokenUtil jwtTokenUtil;

    @Resource
    PasswordEncoder passwordEncoder;

    @Resource
    public SysAdminMapper sysAdminMapper;

    @Autowired
    public SysRoleMapper sysRoleMapper;

    @Autowired
    public SysAdminRoleRelationMapper sysAdminRoleRelationMapper;

    @Resource
    public SysResourceMapper resourceMapper;

    @Override
//    1st
    public String login(String u, String p) {
        //从数据库加载admin 但是为什么要加上权限

        String token = null;
        try {
            //2nd
            UserDetails userDetails = loadUserDetail(u);

            if (!passwordEncoder.matches(p, userDetails.getPassword())) {
                Asserts.fail("密码不正确");  //可以抛出异常所以
            }
            if (!userDetails.isEnabled()) {
                Asserts.fail("帐号已被禁用");
            }
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {

        }
        return token;

        //DIY的垃圾代码
//        if (passwordEncoder.encode(p).equals(userDetails.getPassword())) {
//            String s = jwtTokenUtil.generateToken(userDetails);
//            return s;
//        } else
//            return null;
    }

    //获得用户信息和资源
    @Override
    public UserDetails loadUserDetail(String username) {

        SysAdmin admin = getAdminByUsername(username);

        List<SysResource> ress = resourceMapper.getResByAdminId(admin.getId());

        if (ress != null) {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            ress.forEach(e -> {
                //why e.getId():e.getName())
                grantedAuthorities.add(new SimpleGrantedAuthority(e.getId() + ":" + e.getName()));
            });
            return new User(admin.getUsername(), admin.getPassword(), grantedAuthorities);
        } else
            return new User(admin.getUsername(), admin.getPassword(), null);


    }

    @Override
    public SysAdmin getAdminByUsername(String username) {
        //有bug先这样 username必须不一样
        //从数据库加载admin


        //没有此用户
        SysAdmin admin = sysAdminMapper.selectOne(new QueryWrapper<SysAdmin>().eq("username", username));
        if(admin!=null)
            return admin;

        //虽然只是用户名找不到，但是还是要这样写
        throw new UsernameNotFoundException("用户名或者密码错误");
    }

//    @Override
//    public boolean allocRoles(Long adminid, List<Long> roles) {
////        处理一下
////        sysRoleMapper.allocRole(adminid,roles.get(0));
//
////        先删除所有
//        sysAdminRoleRelationMapper.delete(new QueryWrapper<SysAdminRoleRelation>().eq("admin_id", adminid));
//
//        ArrayList<SysAdminRoleRelation> list = new ArrayList<>();
//        for (Long role : roles) {
//            SysAdminRoleRelation relation = new SysAdminRoleRelation();
//            relation.setAdminId(adminid);
//            relation.setRoleId(role);
//            list.add(relation);
//        }
//
//
//        //?
//        try {
//            sysRoleMapper.allocRoles(list);
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//
//
//
//    }
//

}
