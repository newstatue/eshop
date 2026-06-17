package com.evorsio.eshop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.evorsio.eshop.common.BizCode;
import com.evorsio.eshop.common.BizException;
import com.evorsio.eshop.domain.Sku;
import com.evorsio.eshop.domain.Spu;
import com.evorsio.eshop.domain.SpuImage;
import com.evorsio.eshop.mapper.SkuMapper;
import com.evorsio.eshop.mapper.SpuImageMapper;
import com.evorsio.eshop.service.SkuService;
import com.evorsio.eshop.service.SpuService;
import com.evorsio.eshop.mapper.SpuMapper;
import com.evorsio.eshop.vo.SkuVo;
import com.evorsio.eshop.vo.SpuDetailVo;
import com.evorsio.eshop.vo.SpuVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author Admin
* @description 针对表【spu】的数据库操作Service实现
* @createDate 2026-06-16 00:58:31
*/
@Service
@RequiredArgsConstructor
public class SpuServiceImpl extends ServiceImpl<SpuMapper, Spu>
    implements SpuService{

    private final SpuMapper spuMapper;
    private final SkuMapper skuMapper;
    private final SpuImageMapper spuImageMapper;

    @Override
    public List<SpuVo> getProductList() {
        return spuMapper.selectProductList();
    }

    @Override
    public SpuDetailVo getProductDetail(Long id) {
        // 1. 查出指定商品
        Spu spu = this.getById(id);
        if(spu == null || spu.getStatus() == 0){
            throw new BizException(BizCode.PRODUCT_NOT_FOUND);
        }

        // 2. 查询Sku列表
        List<SkuVo> skuList = skuMapper.selectList(
                new LambdaQueryWrapper<Sku>()
                        .eq(Sku::getSpuId, id)
                        .orderByAsc(Sku::getId)
        ).stream().map(sku -> {
            SkuVo skuVo = new SkuVo();
            skuVo.setId(sku.getId());
            skuVo.setSpec(sku.getSpec());
            skuVo.setPrice(sku.getPrice());
            skuVo.setStock(sku.getStock());
            return skuVo;
        }).toList();

        // 3. 查询图片列表
        List<String> imageList = spuImageMapper.selectList(
                new LambdaQueryWrapper<SpuImage>()
                        .eq(SpuImage::getSpuId, id)
                        .orderByAsc(SpuImage::getSort)
        ).stream().map(SpuImage::getUrl).toList();


        SpuDetailVo vo = new SpuDetailVo();
        vo.setId(spu.getId());
        vo.setName(spu.getName());
        vo.setDescription(spu.getDescription());
        vo.setSkuList(skuList);
        vo.setImages(imageList);

        return vo;
    }
}




