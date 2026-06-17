package com.evorsio.eshop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.evorsio.eshop.domain.Spu;
import com.evorsio.eshop.vo.SpuDetailVo;
import com.evorsio.eshop.vo.SpuVo;

import java.util.List;

/**
 * @author Admin
 * @description 针对表【spu】的数据库操作Mapper
 * @createDate 2026-06-16 00:58:31
 * @Entity generator.domain.Spu
 */
public interface SpuMapper extends BaseMapper<Spu> {
    List<SpuVo> selectProductList();

    List<SpuDetailVo> selectProductDetailList();
}




