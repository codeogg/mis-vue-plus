package com.xufei.system.test;

import com.xufei.system.domain.SysUser;
import com.xufei.system.mapper.SysUserMapper;
import com.xufei.system.service.ISysSiteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
public class ServiceTest {

    @Autowired
    private SysUserMapper userMapper;

    @Test
    void test01() {
        SysUser user = userMapper.selectUserByUsername("xufei");
        System.out.println(user);
    }
}
