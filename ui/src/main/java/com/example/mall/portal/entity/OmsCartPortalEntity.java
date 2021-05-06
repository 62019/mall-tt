package com.example.mall.portal.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("oms_cart")
public class OmsCartPortalEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品的id
     */
    private Long productId;

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 购买数量
     */
    private Integer quantity;

    /**
     * 添加到购物车的价格
     */
    private BigDecimal price;

    /**
     * 商品主图
     */
    private String productPic;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品品牌
     */
    private String productBrand;

    /**
     * 商品的条码
     */
    private String productSn;

    /**
     * 商品副标题（卖点）
     */
    private String productSubTitle;

    /**
     * 会员昵称
     */
    private String memberNickname;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 修改时间
     */
    private LocalDateTime modifyDate;

    /**
     * 商品的分类
     */
    private Long productCategoryId;

    /**
     * 商品销售属性:[{"key":"颜色","value":"银色"},{"key":"容量","value":"4G"}]
     */
    private String productAttr;


}
