package com.example.mall.portal.controller;


import com.example.common.CommonResult;
import com.example.mall.admin.service.SysAdminService;
import com.example.mall.portal.config.mqTemp.MailSender;
import com.example.mall.portal.entity.UmsMember;
import com.example.mall.portal.service.UmsMemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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
public class MemberLoginController {

    @Autowired
    MailSender mailSender;

    @Autowired
    UmsMemberService memberService;

    @RequestMapping(value = "/getAuthCode", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAuthCode(@RequestParam String telephone) {
        String authCode = memberService.generateAuthCode(telephone);
        return CommonResult.succ("获取验证码成功" + authCode);
    }


    @PostMapping("/login")
    @ResponseBody
    //有点浪费 懒
    public CommonResult login(@RequestBody UmsMember umsMember, @RequestParam String phone, @RequestParam String code) {

//        测试先不用
        //        boolean verify = memberService.verify(umsMember, phone, code);


        mailSender.sendMail();
//        if (verify) {
        if (true) {
//            这个token有问题都一样 先测试用用
            String token = memberService.login(umsMember.getUsername());
            return CommonResult.succ(200, "成功", token);

        } else
            return CommonResult.fail(400, "失败", null);
    }


}
