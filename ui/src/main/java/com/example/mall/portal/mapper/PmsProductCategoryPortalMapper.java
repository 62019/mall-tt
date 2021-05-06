package com.example.mall.portal.mapper;

import com.example.mall.admin.vo.PmsProductCategoryVo;
import com.example.mall.portal.entity.PmsProductCategoryPortalEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 产品分类 Mapper 接口
 * </p>
 *
 * @author D_Richard
 * @since 2021-04-07
 */
public interface PmsProductCategoryPortalMapper extends BaseMapper<PmsProductCategoryPortalEntity> {

    List<PmsProductCategoryVo> TreeList();
}
