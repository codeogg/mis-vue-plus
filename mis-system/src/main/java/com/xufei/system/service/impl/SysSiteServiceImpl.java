package com.xufei.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xufei.system.domain.SysSite;
import com.xufei.system.mapper.SysSiteMapper;
import com.xufei.system.service.ISysSiteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SysSiteServiceImpl implements ISysSiteService {

    private final SysSiteMapper baseMapper;

    @Override
    public List<SysSite> getAllSites() {
        List<SysSite> sites = this.baseMapper.selectList(null);
        return sites;
    }

}
