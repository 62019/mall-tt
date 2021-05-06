package com.example.mall.portal.controller;


import com.example.common.CommonResult;
import com.example.mall.portal.entity.MiaoshaGoodsPortalEntity;
import com.example.mall.portal.entity.UmsMember;
import com.example.mall.portal.service.MiaoshaGoodsPortalService;
import com.example.mall.portal.service.MiaoshaService;
import com.example.mall.portal.service.RedisService;
import com.example.mall.portal.service.UmsMemberService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author D_Richard
 * @since 2021-04-11
 */
@Controller
@RequestMapping("/api")
public class Miaosha2Controller implements InitializingBean {

    @Autowired
    UmsMemberService memberService;

    @Autowired
    MiaoshaGoodsPortalService miaoshaGoodsPortalService;

    @Autowired
    MiaoshaService miaoshaService;

    @Autowired
    RedisService redisService;


    /**
     * 系统初始化
     * 库存加载到redis
     * */
    public void afterPropertiesSet() throws Exception {
        List<MiaoshaGoodsPortalEntity> list = miaoshaGoodsPortalService.list();
        for (MiaoshaGoodsPortalEntity goodsPortalEntity : list) {

            redisService.set("msSP"+goodsPortalEntity.getId(),goodsPortalEntity.getStockCount().toString());
            redisService.expire("msSP"+goodsPortalEntity.getId(),120);
        }
    }

    @RequestMapping("/miaosha2")
    //可以通过 prin 得到 用户名 通过用户名查询用户
    public CommonResult list(@RequestParam("goodsId") long goodsId, Principal principal) {
        /*
        1.判断库存
        2.判断是否已经买过=查订单（怎么想出来的）
        3.减少库存、创建订单、创建秒杀订单
         */


        UmsMember member = memberService.getCurrentMember();
        //为什么为空啊
        String name = principal.getName();



//正常
//        //1.
//        MiaoshaGoodsPortalEntity good = miaoshaGoodsPortalService.getById(goodsId);
//        if (good.getStockCount() < 0) {
//            return CommonResult.fail("卖完");
//        }
//
//        //2.
//        MiaoshaOrderPortalEntity one = miaoshaService.getOne(new QueryWrapper<MiaoshaOrderPortalEntity>().eq("user_id", member.getId()));
//        if (one != null) {
//            return CommonResult.fail("只能买一个");
//        }
//
//        //3.创建订单 减库存 这是一起操作的 需要写道事务里面      //用户id直接有
//        miaoshaService.go(goodsId);

        return CommonResult.succ("maybe");
    }
}
