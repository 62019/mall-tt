package com.example.mall.portal.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.common.CommonResult;
import com.example.mall.portal.entity.CmsHome;
import com.example.mall.portal.entity.OmsOrderItemPortalEntity;
import com.example.mall.portal.entity.OmsOrderPortalEntity;
import com.example.mall.portal.mapper.CmsHomeMapper;
import com.example.mall.portal.service.OmsOrderPortalService;
import com.example.mall.portal.vo.OmsOrderPortalVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author D_Richard
 * @since 2021-04-08
 */
@Controller
@RequestMapping("/api/order")
public class OmsOrderPortalController {
    @Autowired
    private OmsOrderPortalService omsOrderPortalService;

//    根据小程序购物车生成订单
    /*
    参数
    数量 价格 商品数组(商品和订单是 多对一）
    用户token 通过token获得用户id
     */
    /*
    生成购物车列表=订单商品表
    在后端算金额，前端容易改吧
     */


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    //万能
//    private CommonResult sd(@RequestBody HashMap<String,Object> oo){
    private CommonResult sd(@RequestBody OmsOrderPortalVo omsOrderPortalVo) {

        Map<String, Object> map = omsOrderPortalService.generateOrder(omsOrderPortalVo);

        return CommonResult.succ(map);
    }

    //    通过用户id查订单
    @RequestMapping(value = "/listByUserId", method = RequestMethod.GET)
    @ResponseBody
    //不用写RequestParam
//    private CommonResult list(@RequestParam Long memberid) {
    private CommonResult list( Long memberid) {

        List<OmsOrderPortalEntity> list = omsOrderPortalService.list(new QueryWrapper<OmsOrderPortalEntity>().eq("member_id", memberid));

        return CommonResult.succ(list);
    }

    @RequestMapping(value = "/paySuccess", method = RequestMethod.GET)
    @ResponseBody
    private CommonResult paySuccess( Long memberid) {

        List<OmsOrderPortalEntity> list = omsOrderPortalService.list(new QueryWrapper<OmsOrderPortalEntity>().eq("member_id", memberid));

        return CommonResult.succ(list);
    }

}
