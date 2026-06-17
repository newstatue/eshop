package com.evorsio.eshop.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * 
 * @TableName spu_image
 */
@TableName(value ="spu_image")
@Data
public class SpuImage {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 关联SPU
     */
    private Long spuId;

    /**
     * 图片地址
     */
    private String url;

    /**
     * 排序 0为主图
     */
    private Integer sort;

    /**
     * 
     */
    @TableLogic
    private Integer deleted;
}