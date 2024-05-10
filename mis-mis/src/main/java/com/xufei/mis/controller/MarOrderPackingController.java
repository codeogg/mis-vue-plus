package com.xufei.mis.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.xufei.common.core.R;
import com.xufei.mis.entity.MarOrderPacking;
import com.xufei.mis.service.IMarOrderPackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SaIgnore
@RestController
@RequestMapping("/mis/business/order/packing")
@RequiredArgsConstructor
public class MarOrderPackingController {

    private final IMarOrderPackingService orderPackingService;

    @GetMapping("/list")
    public R<List<MarOrderPacking>> list(){
        List<MarOrderPacking> list = orderPackingService.getAll();
        return R.ok(list);
    }
}
