package com.example.mall.portal.controller;


import com.example.mall.portal.entity.CmsHome;
import com.example.mall.portal.mapper.CmsHomeMapper;
import com.example.common.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author D_Richard
 * @since 2021-04-07
 */
@Controller
@RequestMapping("/home")
public class CmsHomeController {

    @Autowired
    private CmsHomeMapper cmsHomeMapper;

    @RequestMapping(value = "/swiperdata", method = RequestMethod.GET)
    @ResponseBody
    private CommonResult sd(){
        List<CmsHome> cmsHomes = cmsHomeMapper.selectList(null);
        CommonResult succ = CommonResult.succ(cmsHomes);
        return succ;
    }
}
