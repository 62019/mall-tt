package com.example.mall.portal.entity;

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
 * @since 2021-04-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CmsHome implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String imgSrc;

    private String navSrc;


}
