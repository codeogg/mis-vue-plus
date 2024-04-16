package com.xufei.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xufei.system.domain.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<String> selectMenuPermsByUserId(@Param("userId") Long userId, @Param("siteId") Long siteId);

    List<SysMenu> selectMenusByUserId(@Param("userId") Long userId, @Param("siteId") Long siteId);
}
