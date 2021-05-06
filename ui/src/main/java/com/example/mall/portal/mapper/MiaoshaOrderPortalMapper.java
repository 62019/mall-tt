package com.example.mall.portal.mapper;

import com.example.mall.portal.entity.MiaoshaOrderPortalEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author D_Richard
 * @since 2021-04-11
 */
@Mapper
public interface MiaoshaOrderPortalMapper extends BaseMapper<MiaoshaOrderPortalEntity> {

    //一直没用过注解 今天用用
    @Update("UPDATE miaosha_goods SET stock_count=stock_count-1  WHERE id = #{goodsid}")
    boolean decStock(long goodsId);
}
