package com.xufei.common.excel.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.xufei.common.enums.UserStatusEnum;

public class StatusConverter implements Converter<Integer> {

    @Override
    public Class<?> supportJavaTypeKey() {
        return null;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return null;
    }

    @Override
    public Integer convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return UserStatusEnum.NORMAL.getDescription().equals(cellData.getStringValue()) ? UserStatusEnum.NORMAL.getCode() : UserStatusEnum.DISABLED.getCode();
    }

    @Override
    public WriteCellData<?> convertToExcelData(Integer value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return new WriteCellData<>(value.equals(UserStatusEnum.NORMAL.getCode()) ? UserStatusEnum.NORMAL.getDescription() : UserStatusEnum.DISABLED.getDescription());
    }
}
