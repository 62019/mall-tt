package com.example.mall.admin.controller;


import com.example.mall.admin.mapper.PmsProductCategoryMapper;
import com.example.mall.admin.vo.PmsProductCategoryVo;
import com.example.common.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

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
@RequestMapping("/category")
public class PmsProductCategoryController {

    @Autowired
    PmsProductCategoryMapper categoryMapper;

    @GetMapping("/treelist")
    @ResponseBody
    public CommonResult list() {
        List<PmsProductCategoryVo> list = categoryMapper.TreeList();

        return CommonResult.succ(list);
    }
}
