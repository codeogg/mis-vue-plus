package com.xufei.framework.minio;

import com.xufei.framework.properties.MinioProperties;
import io.minio.MinioClient;
import io.minio.errors.MinioException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Data
@Component
@RequiredArgsConstructor
public class MinioClientFactory implements InitializingBean {

    private final MinioProperties minioProperties;

    private MinioClient minioClient;

    @Override
    public void afterPropertiesSet() throws Exception {
        minioClient = MinioClient.builder()
                .endpoint(minioProperties.getEndpoint())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
    }
}
