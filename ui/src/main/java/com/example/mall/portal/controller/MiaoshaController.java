package com.example.mall.portal.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.common.CommonResult;
import com.example.mall.portal.config.mqTemp.MiaoshaSender;
import com.example.mall.portal.entity.MiaoshaGoodsPortalEntity;
import com.example.mall.portal.entity.MiaoshaOrderPortalEntity;
import com.example.mall.portal.entity.UmsMember;
import com.example.mall.portal.service.MiaoshaGoodsPortalService;
import com.example.mall.portal.service.MiaoshaService;
import com.example.mall.portal.service.RedisService;
import com.example.mall.portal.service.UmsMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author D_Richard
 * @since 2021-04-11
 */
@RestController
@Slf4j
@RequestMapping("/api")
public class MiaoshaController implements InitializingBean {

    @Autowired
    UmsMemberService memberService;

    @Autowired
    MiaoshaGoodsPortalService miaoshaGoodsPortalService;

    @Autowired
    MiaoshaService miaoshaService;

    @Autowired
    RedisService redisService;

    @Autowired
    MiaoshaSender miaoshaSender;

    /**
     * 系统初始化
     * 库存加载到redis
     */
    public void afterPropertiesSet() throws Exception {
        List<MiaoshaGoodsPortalEntity> list = miaoshaGoodsPortalService.list();
        for (MiaoshaGoodsPortalEntity goodsPortalEntity : list) {

            redisService.set("msSP" + goodsPortalEntity.getId(), goodsPortalEntity.getStockCount().toString());
            redisService.expire("msSP" + goodsPortalEntity.getId(), 9999);
        }
    }

    

    @RequestMapping("/miaosha")

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


        //============


        //直接减库存
        // 然后判断 不同于之前先查
        //为什么不用判断
        Long stock = redisService.dec("msSP" + goodsId, goodsId);

        //str转int
        //String s = redisService.get();
        //Integer stock = Integer.valueOf(s);
        if (stock < 0) {
            log.error("[]没货了 兄弟",member.getUsername());
            return CommonResult.fail("sold out ");
        }




        //判断是否已经秒杀到

        // 异步创建顶订单

        //需要哪些参数
        //商品id就行了
        HashMap<String, Object> map = new HashMap<>();
        map.put("goodid", goodsId);
        map.put("member", memberService.getCurrentMember());
        miaoshaSender.sendMiaosha(map);





        //MiaoshaGoodsPortalEntity good = miaoshaGoodsPortalService.getById(goodsId);
        //if (good.getStockCount() < 0) {
        //    return CommonResult.fail("卖完");
        //}
        //
        ////2.
        ////MiaoshaOrderPortalEntity one = miaoshaService.getOne(new QueryWrapper<MiaoshaOrderPortalEntity>().eq("user_id", member.getId()));
        ////if (one != null) {
        ////    return CommonResult.fail("只能买一个");
        ////}
        //
        ////3.创建订单 减库存 这是一起操作的 需要写道事务里面      //用户id直接有
        //miaoshaService.go(goodsId);



        //============
        log.error("[]进入队列",member.getUsername());

        return CommonResult.succ("wait");
    }
}
