package com.example.mall.admin.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品信息
 * </p>
 *
 * @author D_Richard
 * @since 2021-04-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PmsProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long brandId;

    private Long productCategoryId;

    private String pic;

    private String name;

    /**
     * 货号
     */
    private String productSn;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 上架状态：0->下架；1->上架
     */
    private Integer publishStatus;

    /**
     * 排序
     */
    private Integer sort;

    private BigDecimal price;

    /**
     * 商品描述
     */
    private String description;

    private String keywords;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 商品分类名称
     */
    private String productCategoryName;


}
