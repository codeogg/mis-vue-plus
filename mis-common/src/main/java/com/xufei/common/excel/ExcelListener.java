package com.xufei.common.excel;

import com.alibaba.excel.read.listener.ReadListener;

public interface ExcelListener<T> extends ReadListener<T> {

    String getExcelResult();
}
