package com.xufei.common.excel.converter;

import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

public class DeptConverter implements Converter<Long> {

    @Override
    public Class<?> supportJavaTypeKey() {
        return Long.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return null;
    }


    @Override
    public Long convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        String stringValue = cellData.getStringValue();
        Long id = 0L;
        switch (stringValue) {
            case "网络部":
                id = 2L;
                break;
            case "财务部":
                id = 3L;
                break;
        }
        return id;
    }

    @Override
    public WriteCellData<?> convertToExcelData(Long value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {

        String deptName = "";
        if (value.longValue() == 1L) {
            deptName = "总经办";
        } else if (value.longValue() == 2L) {
            deptName = "网络部";
        } else if (value.longValue() == 3L) {
            deptName = "财务部";
        }

        return new WriteCellData<>(deptName);
    }
}
