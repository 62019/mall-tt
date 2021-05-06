package com.example.mall.portal.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.mall.admin.entity.OmsCart;
import com.example.mall.admin.entity.OmsOrderItem;
import com.example.mall.portal.entity.OmsCartPortalEntity;
import com.example.mall.portal.entity.OmsOrderItemPortalEntity;
import com.example.mall.portal.entity.OmsOrderPortalEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author D_Richard
 * @since 2021-04-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OmsOrderPortalVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 订单编号
     */
    private String orderSn;
    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;


    List<OmsOrderItemPortalEntity> goods;


}
