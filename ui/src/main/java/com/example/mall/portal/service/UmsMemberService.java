package com.example.mall.portal.service;

import com.example.common.CommonResult;
import com.example.mall.portal.entity.UmsMember;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author D_Richard
 * @since 2021-04-07
 */
public interface UmsMemberService extends IService<UmsMember> {

    UserDetails loadUserDetail(String username);

    String generateAuthCode(String telephone);

    CommonResult verifyAuthCode(String telephone, String authCode);


    UmsMember getCurrentMember();

    boolean verify(UmsMember umsMember, String phone,String code);

    String login(String username);
}
