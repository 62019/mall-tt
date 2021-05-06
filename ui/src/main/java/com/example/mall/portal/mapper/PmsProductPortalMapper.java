package com.example.mall.portal.mapper;

import com.example.mall.portal.entity.PmsProductPortalEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 商品信息 Mapper 接口
 * </p>
 *
 * @author D_Richard
 * @since 2021-04-08
 */
public interface PmsProductPortalMapper extends BaseMapper<PmsProductPortalEntity> {
//    多参需要带@Param
    List<PmsProductPortalEntity> search(@Param("keyword")String keyword, @Param("bid")Long brandId, @Param("cid") Long cid);
}
