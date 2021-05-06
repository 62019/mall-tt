package com.example.mall.portal.service;

import com.example.mall.portal.entity.MiaoshaOrderPortalEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mall.portal.entity.UmsMember;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author D_Richard
 * @since 2021-04-11
 */
public interface MiaoshaService extends IService<MiaoshaOrderPortalEntity> {

    void go(long goodsId);

    void go(Long goodid, UmsMember member);
}
