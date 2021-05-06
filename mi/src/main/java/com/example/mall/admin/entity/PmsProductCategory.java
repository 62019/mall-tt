package com.example.mall.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 产品分类
 * </p>
 *
 * @author D_Richard
 * @since 2021-04-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PmsProductCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 上级分类的编号：0表示一级分类
     */
    private Long parentId;

    private String name;

    /**
     * 分类级别：0->1级；1->2级
     */
    private Integer level;

    /**
     * 显示状态：0->不显示；1->显示
     */
    private Integer showStatus;

    private Integer sort;

    private String keywords;

    /**
     * 描述
     */
    private String description;


}
