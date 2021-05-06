package com.example.mall.portal.controller;


import com.example.common.CommonResult;
import com.example.mall.admin.entity.SysAdmin;
import com.example.mall.portal.entity.UmsMember;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author D_Richard
 * @since 2021-04-07
 */
@Controller
@RequestMapping("/api")
public class UmsMemberController {

    @PostMapping("/login2")
    @ResponseBody
    //有点浪费 懒
    public CommonResult login(@RequestBody UmsMember umsMember) {

        return CommonResult.succ(200, "成功", null);
    }
}
