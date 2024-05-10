package com.xufei.mis.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.xufei.mis.entity.MarOrderPacking;
import com.xufei.mis.mapper.MarOrderPackingMapper;
import com.xufei.mis.service.IMarOrderPackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@DS("mis")
@RequiredArgsConstructor
public class MarOrderPackingServiceImpl implements IMarOrderPackingService {

    private final MarOrderPackingMapper baseMapper;

    @Override
    public List<MarOrderPacking> getAll() {
        return baseMapper.selectList(null);
    }
}
