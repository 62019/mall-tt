package com.example.mall.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mall.admin.entity.SysAdmin;
import com.example.mall.admin.entity.SysRole;
import com.example.mall.admin.mapper.SysRoleMapper;
import com.example.mall.admin.service.SysAdminService;
import com.example.common.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 后台用户表 前端控制器
 * </p>
 *
 * @author D_Richard
 * @since 2021-04-05
 */
@Controller
@RequestMapping("/sys/admin")
public class SysAdminController {
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    SysAdminService adminService;

    @Autowired
    SysRoleMapper sysRoleMapper;

    @PostMapping("/login")
    @ResponseBody
    //有点浪费 懒
    public CommonResult login(@RequestBody SysAdmin sysAdmin) {
//        sysAdminService.

        String token = adminService.login(sysAdmin.getUsername(), sysAdmin.getPassword());

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);

        if (token != null)
            return CommonResult.succ(200, "成功", tokenMap);
        else return CommonResult.fail("fail");
    }


    @GetMapping("/getRoles/{adminid}")
    @ResponseBody
    public CommonResult getRolesByAdminid(@PathVariable int adminid) {
        List<SysRole> roles = sysRoleMapper.getRoleByAdminID((long) adminid);
        return CommonResult.succ(roles);
    }

    //分配角色
    //增加和删除
//    @PostMapping("/update")
//    @ResponseBody
//    public CommonResult alloc(@RequestParam("adminId") Long adminId,
//                        @RequestParam("roleIds") List<Long> roleIds) {
//
//        adminService.allocRoles(adminId,roleIds);
//        return CommonResult.succ("");
//    }


    //应该不需要后端吧直接前端删除cookie
    @PostMapping("/logout")
    @ResponseBody
    //有点浪费 懒
    public CommonResult logout() {
        return CommonResult.succ(null);
    }


    @GetMapping("/info")
    @ResponseBody
    //有点浪费 懒
    public HashMap<String, Object> info(Principal principal) {
//        UserDetails userDetails = sysAdminService.loadUserDetail(principal.getName());
        SysAdmin admin = adminService.getAdminByUsername(principal.getName());
//        通过name找到id
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", admin.getUsername());


//        通过adminid查询roles
        List<SysRole> roles = sysRoleMapper.getRoleByAdminID(admin.getId());

        map.put("role", roles);
//        map.put("menu",);
        return map;

    }


//    增删改查


    //一级目录包括二级目录
    //自关联查询
    @GetMapping("/list")
    @ResponseBody
    public CommonResult list(Principal principal) {
        List<SysAdmin> list = adminService.list();
        return CommonResult.succ(list);
    }

    @PostMapping("/delete")
    @ResponseBody
    public CommonResult de(@RequestParam("ids") List<Long> ids) {
        boolean list = adminService.removeById(ids.get(0));
        return CommonResult.succ(list);
    }

    //    前端  website+id(变量)
    @PostMapping("/delete/{id}")
    @ResponseBody
    public CommonResult de(@PathVariable int id) {
        boolean list = adminService.removeById(id);
        return CommonResult.succ(list);
    }

    @PostMapping("/create")
    @ResponseBody
    public CommonResult de(@RequestBody SysAdmin sysAdmin) {
        adminService.save(sysAdmin);
        return CommonResult.succ("添加用户成功");
    }


    //其实id传不传都行啊
    @PostMapping("/update/{id}")
    @ResponseBody
    public CommonResult de(@PathVariable int id, @RequestBody SysAdmin sysAdmin) {
        adminService.update(sysAdmin, new QueryWrapper<SysAdmin>().eq("id", sysAdmin.getId()));
        return CommonResult.succ(null);
    }

}
