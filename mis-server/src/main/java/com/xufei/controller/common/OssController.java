package com.xufei.controller.common;

import cn.dev33.satoken.annotation.SaIgnore;
import com.xufei.framework.minio.MinioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@SaIgnore
@Slf4j
@RestController
@RequiredArgsConstructor
public class OssController {

  private final MinioService minioService;

    @GetMapping("/download")
    public void download(@RequestParam String bucketName, @RequestParam String filePath, HttpServletResponse response){
        minioService.download(bucketName, filePath, response);
    }

}
