package com.xufei.common.core;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableData<T> implements Serializable {

    private long total;
    private List<T> rows;

    public static <T> TableData<T> build(IPage<T> page) {
        return new TableData<>(page.getTotal(), page.getRecords());
    }

    public static <T> TableData<T> build(List<T> list) {
        return new TableData<>(list.size(), list);
    }
}
