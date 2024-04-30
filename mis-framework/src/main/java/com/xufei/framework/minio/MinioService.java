package com.xufei.framework.minio;

import cn.hutool.core.io.IoUtil;
import io.minio.GetObjectResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class MinioService {


    public void download(String bucketName, String filePath, HttpServletResponse response) {
        String fileName = null;
        try {
            filePath = URLDecoder.decode(filePath, StandardCharsets.UTF_8.toString());
            fileName = getFileName(filePath);

            response.reset();
            response.setHeader("Content-disposition", "attachment;filename*=" + fileName);
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE + "; charset=UTF-8");

            InputStream object = MinioUtil.download(bucketName, filePath);
            byte buf[] = new byte[1024];
            int length = 0;

            OutputStream outputStream = response.getOutputStream();

            while ((length = object.read(buf)) > 0) {
                outputStream.write(buf, 0, length);
            }
            outputStream.close();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private String getFileName(String filePath) throws UnsupportedEncodingException {
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
        String encode = URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString());
        return encode.replaceAll("\\+", "%20");
    }
}
