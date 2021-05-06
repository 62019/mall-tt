package com.example.mall.portal.service.impl;

import com.example.mall.admin.vo.PmsProductCategoryVo;
import com.example.mall.portal.entity.PmsProductPortalEntity;
import com.example.mall.portal.mapper.PmsProductCategoryPortalMapper;
import com.example.mall.portal.mapper.PmsProductPortalMapper;
import com.example.mall.portal.service.PmsProductPortalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author D_Richard
 * @since 2021-04-08
 */
@Service
public class PmsProductPortalServiceImpl extends ServiceImpl<PmsProductPortalMapper, PmsProductPortalEntity> implements PmsProductPortalService {

    @Autowired
    PmsProductCategoryPortalMapper categoryMapper;

    @Autowired
    PmsProductPortalMapper productPortalMapper;






    @Override
    public List<PmsProductCategoryVo> categoryTreeList() {
        return categoryMapper.TreeList();
    }

    @Override
    public List<PmsProductPortalEntity> search(String keyword, Long brandId, Long productCategoryId, Integer pageNum, Integer pageSize, Integer sort) {

        return productPortalMapper.search(keyword,brandId,productCategoryId);
    }
}
