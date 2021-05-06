package com.example.mall.portal.service;

import com.example.mall.admin.entity.OmsOrder;
import com.example.mall.admin.entity.OmsOrderItem;
import com.example.mall.portal.entity.OmsOrderPortalEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mall.portal.entity.UmsMember;
import com.example.mall.portal.vo.OmsOrderPortalVo;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author D_Richard
 * @since 2021-04-08
 */
public interface OmsOrderPortalService extends IService<OmsOrderPortalEntity> {

    Map<String, Object> generateOrder(OmsOrderPortalVo omsOrderPortalVo);

    public void cancelOrder(Long orderId);
}
