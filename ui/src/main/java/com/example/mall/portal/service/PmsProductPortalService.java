package com.example.mall.portal.service;

import com.example.mall.admin.vo.PmsProductCategoryVo;
import com.example.mall.portal.entity.PmsProductPortalEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品信息 服务类
 * </p>
 *
 * @author D_Richard
 * @since 2021-04-08
 */
public interface PmsProductPortalService extends IService<PmsProductPortalEntity> {

    List<PmsProductCategoryVo> categoryTreeList();

    List<PmsProductPortalEntity> search(String keyword, Long brandId, Long productCategoryId, Integer pageNum, Integer pageSize, Integer sort);

}
