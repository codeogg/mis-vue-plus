package com.xufei.mis.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xufei.mis.entity.MarOrderPacking;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@DS("mis")
public interface MarOrderPackingMapper extends BaseMapper<MarOrderPacking> {
}
