package com.example.mall.admin.vo;

import com.example.mall.admin.entity.PmsProductCategory;
import lombok.Data;

import java.util.List;

@Data
public class PmsProductCategoryVo extends PmsProductCategory {
    List<PmsProductCategory> child;
}
