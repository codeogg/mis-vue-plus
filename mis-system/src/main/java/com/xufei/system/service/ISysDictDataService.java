package com.xufei.system.service;

import com.xufei.common.core.PageQuery;
import com.xufei.common.core.TableData;
import com.xufei.system.domain.SysDictData;

public interface ISysDictDataService {

    TableData<SysDictData> selectPageList(SysDictData dictData, PageQuery<SysDictData> pageQuery);

    SysDictData getById(Long id);

    void save(SysDictData dictData);

    void update(SysDictData dictData);

    void deleteById(Long id);
}
