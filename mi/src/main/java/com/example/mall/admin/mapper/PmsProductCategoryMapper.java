package com.example.mall.admin.mapper;

import com.example.mall.admin.entity.PmsProductCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mall.admin.vo.PmsProductCategoryVo;

import java.util.List;

/**
 * <p>
 * 产品分类 Mapper 接口
 * </p>
 *
 * @author D_Richard
 * @since 2021-04-07
 */
public interface PmsProductCategoryMapper extends BaseMapper<PmsProductCategory> {
    List<PmsProductCategoryVo> TreeList();
}
