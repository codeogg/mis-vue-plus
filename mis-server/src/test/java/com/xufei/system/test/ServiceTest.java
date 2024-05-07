package com.xufei.system.test;

import com.xufei.framework.minio.MinioClientFactory;
import com.xufei.framework.minio.MinioUtil;
import com.xufei.system.service.ISysDeptService;
import com.xufei.system.service.impl.SysDeptServiceImpl;
import io.minio.BucketExistsArgs;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

@SpringBootTest
public class ServiceTest {

    @Autowired
    private SysDeptServiceImpl deptService;

    @Test
    void test01() throws FileNotFoundException {
        List<Long> ids = deptService.getDeptIdsWithChildren(109L);
        ids.stream().forEach(System.out::println);
    }
}
