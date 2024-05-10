package com.xufei.hk.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.xufei.common.core.R;
import com.xufei.hk.entity.MarOrder;
import com.xufei.hk.service.IMarOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SaIgnore
@RestController
@RequestMapping("/hk/business/order")
@RequiredArgsConstructor
public class MarOrderController {

    private final IMarOrderService orderService;

    @GetMapping("/list")
    public R<List<MarOrder>> list(){
      List<MarOrder> list = orderService.all();
      return R.ok(list);
    }
}
