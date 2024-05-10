package com.xufei.hk.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.xufei.hk.entity.MarOrder;
import com.xufei.hk.mapper.MarOrderMapper;
import com.xufei.hk.service.IMarOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@DS("hk")
@RequiredArgsConstructor
public class MarOrderServiceImpl implements IMarOrderService {

    private final MarOrderMapper baseMapper;

    @Override
    public List<MarOrder> all() {
        return baseMapper.selectList(null);
    }

}
