package com.xufei.framework.minio;

import cn.hutool.extra.spring.SpringUtil;
import io.minio.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.InputStream;
import java.io.OutputStream;

@Slf4j
public class MinioUtil {

    private static final MinioClient MINIO_CLIENT = SpringUtil.getBean(MinioClientFactory.class).getMinioClient();


    public static boolean bucketExists(String bucket) {
        try {
            return MINIO_CLIENT.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean createBucket(String bucket) {
        try {
            MINIO_CLIENT.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void upload(String bucket, String objectId, InputStream input) {
        try {
            if (!bucketExists(bucket)) {
                createBucket(bucket);
            }

            MINIO_CLIENT.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucket)
                            .object(objectId)
                            .stream(input, input.available(), -1)
                            .build());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static InputStream download(String bucket, String objectId) {
        try {
            return MINIO_CLIENT.getObject(GetObjectArgs.builder().bucket(bucket).object(objectId).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void download(String bucket, String objectId, OutputStream output) {
        //@formatter:off
        try (InputStream input = MINIO_CLIENT.getObject(
                GetObjectArgs.builder().bucket(bucket).object(objectId).build())) {
            IOUtils.copy(input, output);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //@formatter:on
    }

    public static boolean objectExists(String bucket, String objectId) {
        //@formatter:off
        try {
            // minio客户端未提供判断对象是否存在的方法，此方法中调用出现异常时说明对象不存在
            MINIO_CLIENT.statObject(StatObjectArgs.builder()
                    .bucket(bucket).object(objectId).build());
        } catch (Exception e) {
            return false;
        }
        //@formatter:on
        return true;
    }

    public static boolean deleteObject(String bucket, String objectId) {
        //@formatter:off
        try {
            MINIO_CLIENT.removeObject(RemoveObjectArgs.builder()
                    .bucket(bucket).object(objectId).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //@formatter:on
        return true;
    }


}
