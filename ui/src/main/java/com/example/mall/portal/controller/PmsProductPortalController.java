package com.example.mall.portal.controller;


import com.example.common.CommonResult;
import com.example.mall.admin.entity.PmsProduct;
import com.example.mall.admin.mapper.PmsProductCategoryMapper;
import com.example.mall.admin.vo.PmsProductCategoryVo;
import com.example.mall.portal.entity.PmsProductPortalEntity;
import com.example.mall.portal.mapper.PmsProductCategoryPortalMapper;
import com.example.mall.portal.service.PmsProductPortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 产品分类 前端控制器
 * </p>
 *
 * @author D_Richard
 * @since 2021-04-07
 */
@Controller
@RequestMapping("/api/product")
public class PmsProductPortalController {


    @Autowired
    PmsProductPortalService productPortalService;

//商品详情
@RequestMapping(value = "/productDetail", method = RequestMethod.GET)
@ResponseBody
//params
public CommonResult productDetail(Long productid)
{
    PmsProductPortalEntity info = productPortalService.getById(productid);
    return CommonResult.succ(info);
}

//综合查询
//    可以简化一点数据，不需要这么多数据
//    根据商品类型id或者品牌id
@RequestMapping(value = "/search", method = RequestMethod.GET)
@ResponseBody
//用x-www-form
// 和 formdata区别是啥
//但是我用x-www-form收不到 用第一个parms收到了
public CommonResult search(@RequestParam(required = false) String keyword,
                                                   @RequestParam(required = false) Long brandId,
                                                   @RequestParam(required = false) Long productCategoryId,
                                                   @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                   @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                                                   @RequestParam(required = false, defaultValue = "0") Integer sort) {
    List<PmsProductPortalEntity> productList = productPortalService.search(keyword, brandId, productCategoryId, pageNum, pageSize, sort);
    CommonResult succ = CommonResult.succ(productList);
    return succ;
}




    @GetMapping("/category/treelist")
    @ResponseBody
    public CommonResult list() {

        List<PmsProductCategoryVo> list = productPortalService.categoryTreeList();

        return CommonResult.succ(list);
    }
}
