package com.xufei.system.test;

import com.xufei.hk.entity.MarOrder;
import com.xufei.hk.service.IMarOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ServiceTest {

    @Autowired
    private IMarOrderService orderService;

    @Test
    void test01() {
        List<MarOrder> all = orderService.all();
        System.out.println(all.size());
    }
}
