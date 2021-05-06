package com.example.mall.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.common.CommonResult;
import com.example.mall.admin.entity.SysResource;
import com.example.mall.portal.entity.MemberDetail;
import com.example.mall.portal.entity.UmsMember;
import com.example.mall.portal.mapper.UmsMemberMapper;
import com.example.mall.portal.service.UmsMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.security.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author D_Richard
 * @since 2021-04-07
 */
@Service
public class UmsMemberServiceImpl extends ServiceImpl<UmsMemberMapper, UmsMember> implements UmsMemberService {

    @Autowired
    RedisServiceImpl redisService;

    @Autowired
    UmsMemberMapper memberMapper;

    @Autowired
    JwtTokenUtil JwtTokenUtil;

    //经常要用Prin查询用户  省略了资源 不知道能不能执行
    @Override
    public UmsMember getCurrentMember() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();

        //auth.getPrincipal只返回userDetails类型 继承他

        MemberDetail memberDetails = (MemberDetail) auth.getPrincipal();

        return memberDetails.getUmsMember();
    }

    @Override
    public String login(String username) {
        UserDetails detail = loadUserDetail(username);
        String token = JwtTokenUtil.generateToken(detail);
        return token;
    }

    @Override
    public MemberDetail loadUserDetail(String username) {


        //通过username查用户 先假设 用户名不重复
        UmsMember member = memberMapper.selectOne(new QueryWrapper<UmsMember>().eq("username", username));
//        UmsMember member = new UmsMember();
//        member.setId(100L);
//        member.setUsername("enen");
//        member.setPassword("123");

//        搞个假资源
        List<UmsMember> ress = memberMapper.selectList(null);

        if (ress != null) {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            ress.forEach(e -> {
                //why e.getId():e.getName())
                grantedAuthorities.add(new SimpleGrantedAuthority(e.getId() + ":" + e.getUsername()));
            });

            //权限自动有假权限
            return new MemberDetail(member);
//            return new User(member.getUsername(), member.getPassword(), grantedAuthorities);
        }else return null;
    }


    @Override
    public String generateAuthCode(String telephone) {
        //验证码绑定手机号并存储到redis
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }

        redisService.set("code_" + telephone, sb.toString());
        redisService.expire("code_" + telephone, 120);
        return sb.toString() + "获取验证码成功";

    }

    @Override
    public CommonResult verifyAuthCode(String telephone, String AuthCode) {

        //验证码绑定手机号并存储到redis
        String realAuthCode = null;
        StringBuilder sb = new StringBuilder();

        realAuthCode = redisService.get("code_" + telephone);


        try {
            if (AuthCode.equals(realAuthCode)) {
                return CommonResult.succ("成功");
            }

//            null.equal报错 反过来不异常
//            if (realAuthCode.equals(AuthCode)) {
//                return CommonResult.succ("成功");
//            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return CommonResult.fail("失败");

    }

    @Override
    public boolean verify(UmsMember umsMember, String telephone, String code) {
        CommonResult commonResult = verifyAuthCode(telephone, code);
        if (commonResult.getCode() == 200) {
            log.warn("验证码正确");
            return true;
        }
        return false;
    }




}