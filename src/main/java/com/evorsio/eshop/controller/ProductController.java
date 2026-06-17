package com.evorsio.eshop.controller;

import com.evorsio.eshop.common.R;
import com.evorsio.eshop.service.SpuService;
import com.evorsio.eshop.vo.SpuDetailVo;
import com.evorsio.eshop.vo.SpuVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Evorsio
 * @since 2026/6/16
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final SpuService spuService;

    @GetMapping("/list")
    public R<List<SpuVo>> getProductList() {
        List<SpuVo> list = spuService.getProductList();
        return R.ok(list);
    }

    @GetMapping("/{id}")
    public R<SpuDetailVo> getProductDetail(@PathVariable Long id) {
        SpuDetailVo vo = spuService.getProductDetail(id);
        return R.ok(vo);
    }
}
