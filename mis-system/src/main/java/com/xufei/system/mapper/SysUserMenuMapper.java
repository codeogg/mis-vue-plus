package com.xufei.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xufei.system.domain.SysUserMenu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysUserMenuMapper extends BaseMapper<SysUserMenu> {

    List<String> selectDisabledMenuByUserId(@Param("userId") Long userId, @Param("siteId") Long siteId);

    void deleteByUserId(Long id);

    List<String> selectExtraMenuByUserId(@Param("userId") Long userId, @Param("siteId") Long siteId);
}
