package com.example.mall.admin.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mall.admin.entity.PmsProduct;
import com.example.mall.admin.service.PmsProductService;
import com.example.common.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.List;

/**
 * <p>
 * 商品信息 前端控制器
 * </p>
 *
 * @author D_Richard
 * @since 2021-04-07
 */
@Controller
@RequestMapping("/product")
public class PmsProductController {
    @Autowired
    PmsProductService productService;


    //一级目录包括二级目录
    //自关联查询
    @GetMapping("/list")
    @ResponseBody
    public CommonResult list(Principal principal) {
        List<PmsProduct> list = productService.list();
        return CommonResult.succ(list);
    }

    @PostMapping("/delete")
    @ResponseBody
    public CommonResult de(@RequestParam("ids") List<Long> ids) {
        boolean list = productService.removeById(ids.get(0));
        return CommonResult.succ(list);
    }

    //    前端  website+id(变量)
    @PostMapping("/delete/{id}")
    @ResponseBody
    public CommonResult de(@PathVariable int id) {
        boolean list = productService.removeById(id);
        return CommonResult.succ(list);
    }

    @PostMapping("/create")
    @ResponseBody
    public CommonResult de(@RequestBody PmsProduct PmsProduct) {
        productService.save(PmsProduct);
        return CommonResult.succ("添加用户成功");
    }


    //其实id传不传都行啊
    @PostMapping("/update/{id}")
    @ResponseBody
    public CommonResult de(@PathVariable int id,@RequestBody PmsProduct PmsProduct) {
        productService.update(PmsProduct,new QueryWrapper<PmsProduct>().eq("id",PmsProduct.getId()));
        return CommonResult.succ(null);
    }
}
