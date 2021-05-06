package com.example.mall.portal.service.impl;

import com.example.mall.portal.config.mqTemp.Sender;
import com.example.mall.portal.entity.OmsOrderItemPortalEntity;
import com.example.mall.portal.entity.OmsOrderPortalEntity;

import com.example.mall.portal.entity.UmsMember;
import com.example.mall.portal.mapper.OmsOrderItemPortalMapper;
import com.example.mall.portal.mapper.OmsOrderPortalMapper;
import com.example.mall.portal.service.OmsOrderPortalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mall.portal.service.UmsMemberService;
import com.example.mall.portal.vo.OmsOrderPortalVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author D_Richard
 * @since 2021-04-08
 */
@Service
public class OmsOrderPortalServiceImpl extends ServiceImpl<OmsOrderPortalMapper, OmsOrderPortalEntity> implements OmsOrderPortalService {

    @Autowired
    public OmsOrderPortalMapper omsOrderPortalMapper;

    @Autowired
    public OmsOrderItemPortalMapper omsOrderItemPortalMapper;

    @Autowired
    public Sender sender;

    @Autowired
    UmsMemberService memberService;


//    @Autowired
//    public OmsCartPortalMapper cartPortalMapper;

    // 格式化的时间字符串
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    // 获取当前时间年月日时分秒毫秒字符串
    private static String getNowDateStr() {
        return sdf.format(new Date());
    }

    @Override
    @Transactional
    public Map<String, Object> generateOrder(OmsOrderPortalVo omsOrderPortalVo) {

        UmsMember member = memberService.getCurrentMember();

        OmsOrderPortalEntity order = new OmsOrderPortalEntity();


        //锁定库存

        //生成订单号
        order.setOrderSn(generateOrderSn(order));
        order.setTotalAmount(omsOrderPortalVo.getTotalAmount());
        order.setCreateTime(LocalDateTime.now());
        //不能为空
        order.setStatus(0);
        order.setMemberId(1L);
        //？？？执行之后直接order就有值了
        omsOrderPortalMapper.insert(order);
//        不需要
//        OmsOrderPortalEntity orderSn = omsOrderPortalMapper.selectOne(new QueryWrapper<OmsOrderPortalEntity>().eq("order_sn", order.getOrderSn()));

        //插入订单商品表
        //订单商品表和订单表是多对一 所以 good.setOrderId(order.getId());
        List<OmsOrderItemPortalEntity> goods = omsOrderPortalVo.getGoods();
        for (OmsOrderItemPortalEntity good : goods) {
            //应该无法获取
            good.setOrderId(order.getId());
            good.setOrderSn(order.getOrderSn());
            omsOrderItemPortalMapper.insert(good);
        }

        //超时取消订单    (定时也可以实现，但是不推荐，因为项目大的时候耗资源多)
        sendDelayMessageCancelOrder(order.getId());

        Map<String, Object> result = new HashMap<>();
        result.put("order", order);
//        result.put("orderItemList", goods);

        return result;
    }




    private void sendDelayMessageCancelOrder(Long orderId) {
        //60s
        long delayTimes = 10 * 1000;
        //发送延迟消息
        sender.sendMessage(orderId, delayTimes);
    }

    //取消订单 设置状态为4
    @Override
    public void cancelOrder(Long orderId) {
        OmsOrderPortalEntity omsOrderPortalEntity = omsOrderPortalMapper.selectById(orderId);
        omsOrderPortalEntity.setStatus(4);
        omsOrderPortalMapper.updateById(omsOrderPortalEntity);
    }



    private String generateOrderSn(OmsOrderPortalEntity order) {
        return "sn" + getNowDateStr();
    }
}
