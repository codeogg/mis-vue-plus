package com.xufei.system.service;

import com.xufei.common.core.PageQuery;
import com.xufei.common.core.TableData;
import com.xufei.system.domain.SysSite;

import java.util.List;
import java.util.Map;

public interface ISysSiteService {

    List<SysSite> getAllSites();

    TableData<SysSite> selectPageUserList(SysSite searchData, PageQuery<SysSite> pageQuery);

    SysSite getById(Long id);

    void save(SysSite user);

    void update(SysSite user);

    void deleteById(Long id);
}
