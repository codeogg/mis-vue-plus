package com.xufei.system.test;

import com.xufei.framework.minio.MinioClientFactory;
import com.xufei.framework.minio.MinioUtil;
import io.minio.BucketExistsArgs;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@SpringBootTest
public class ServiceTest {


    @Test
    void test01() throws FileNotFoundException {
//        MinioUtil.download("test","excel-template/userImportTemplate.xlsx",
//                new FileOutputStream("C:\\Users\\Administrator\\Desktop\\fsdownload\\aaa.xlsx"));

        String a = "excel-template/userImportTemplate.xlsx";
        System.out.println("a.substring(a.lastIndexOf(\"/\")) = " + a.substring(a.lastIndexOf("/")));
    }
}
