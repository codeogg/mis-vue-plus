package com.xufei.hk.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xufei.hk.entity.MarOrderSub;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@DS("hk")
public interface MarOrderSubMapper extends BaseMapper<MarOrderSub> {
}
