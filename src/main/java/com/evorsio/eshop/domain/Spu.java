package com.evorsio.eshop.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 *
 * @TableName spu
 */
@TableName(value = "spu")
@Data
public class Spu {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 1上架 0下架
     */
    private Integer status;

    /**
     *
     */
    @TableLogic
    private Integer deleted;
}