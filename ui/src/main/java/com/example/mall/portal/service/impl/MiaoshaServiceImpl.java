package com.example.mall.portal.service.impl;

import com.example.mall.portal.entity.MiaoshaOrderPortalEntity;
import com.example.mall.portal.entity.OmsOrderPortalEntity;
import com.example.mall.portal.entity.UmsMember;
import com.example.mall.portal.mapper.MiaoshaOrderPortalMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mall.portal.service.MiaoshaService;
import com.example.mall.portal.service.OmsOrderPortalService;
import com.example.mall.portal.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author D_Richard
 * @since 2021-04-11
 */
@Service
public class MiaoshaServiceImpl extends ServiceImpl<MiaoshaOrderPortalMapper, MiaoshaOrderPortalEntity> implements MiaoshaService {

    @Autowired
    MiaoshaOrderPortalMapper miaoshaOrderPortalMapper;

    @Autowired
    OmsOrderPortalService orderService;

    @Autowired
    UmsMemberService memberService;


    //1.减库存 2.创建订单

    //懒着写redis 所以
    @Override
    @Transactional
    public void go(Long goodsId, UmsMember member) {

        miaoshaOrderPortalMapper.decStock(goodsId);


        //rabbit异步 这个不能在这用 可以放到redis 现在先用简单的方法吧
        //UmsMember member = memberService.getCurrentMember();


        //创建普通订单
        OmsOrderPortalEntity order = new OmsOrderPortalEntity();
        order.setStatus(0);
        order.setMemberUsername(member.getUsername());
        order.setMemberId(member.getId());

        order.setCreateTime(LocalDateTime.now());
        //假商品id 因为多对一 所以没写
        //String.valueOf 数字转字符串
        order.setOrderSn(String.valueOf(goodsId));
        boolean save = orderService.save(order);

        //加入秒杀订单
        MiaoshaOrderPortalEntity msOrder = new MiaoshaOrderPortalEntity();
        msOrder.setOrderId(order.getId());
        msOrder.setUserId(member.getId());
        msOrder.setGoodsId(goodsId);
        miaoshaOrderPortalMapper.insert(msOrder);
    }


    //1.减库存 2.创建订单
    @Override
    @Transactional
    public void go(long goodsId) {

        miaoshaOrderPortalMapper.decStock(goodsId);


        //rabbit异步 这个不能在这用 可以放到redis 现在先用简单的方法吧
        //UmsMember member = memberService.getCurrentMember();


        //创建普通订单
        OmsOrderPortalEntity order = new OmsOrderPortalEntity();
        order.setStatus(0);
        order.setMemberUsername(memberService.getCurrentMember().getUsername());
        order.setMemberId(memberService.getCurrentMember().getId());

        order.setCreateTime(LocalDateTime.now());
        //假商品id 因为多对一 所以没写
        //String.valueOf 数字转字符串
        order.setOrderSn(String.valueOf(goodsId));
        boolean save = orderService.save(order);

        //加入秒杀订单
        MiaoshaOrderPortalEntity msOrder = new MiaoshaOrderPortalEntity();
        msOrder.setOrderId(order.getId());
        msOrder.setUserId(memberService.getCurrentMember().getId());
        msOrder.setGoodsId(goodsId);
        miaoshaOrderPortalMapper.insert(msOrder);
    }
}
