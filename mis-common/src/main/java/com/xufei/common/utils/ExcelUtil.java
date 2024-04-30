package com.xufei.common.utils;

import com.alibaba.excel.EasyExcel;
import com.xufei.common.excel.ExcelListener;
import com.xufei.common.exception.ServiceException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExcelUtil {

    public static <T> String importExcel(MultipartFile file, Class<T> clazz, ExcelListener<T> listener) throws Exception {
        if (file.isEmpty() && file.getSize() == 0) {
            throw new ServiceException("导入的文件为空");
        }
        InputStream is = file.getInputStream();
        EasyExcel.read(is, clazz, listener).sheet().doRead();
        return listener.getExcelResult();
    }
}
