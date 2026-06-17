package com.evorsio.eshop.service;

import com.evorsio.eshop.domain.Spu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.evorsio.eshop.vo.SpuDetailVo;
import com.evorsio.eshop.vo.SpuVo;

import java.util.List;

/**
* @author Admin
* @description 针对表【spu】的数据库操作Service
* @createDate 2026-06-16 00:58:31
*/
public interface SpuService extends IService<Spu> {

    List<SpuVo> getProductList();
    SpuDetailVo getProductDetail(Long id);
}
