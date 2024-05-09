package com.xufei.system.service;

import com.xufei.common.core.PageQuery;
import com.xufei.common.core.TableData;
import com.xufei.system.domain.SysDictType;

public interface ISysDictTypeService {

    TableData<SysDictType> selectPageList(SysDictType dictType, PageQuery<SysDictType> pageQuery);

    SysDictType getById(Long id);

    void save(SysDictType dictType);

    void update(SysDictType dictType);

    void deleteById(Long id);
}
