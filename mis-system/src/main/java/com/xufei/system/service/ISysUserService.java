package com.xufei.system.service;

import com.xufei.common.core.PageQuery;
import com.xufei.common.core.TableData;
import com.xufei.system.domain.SysUser;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ISysUserService {

    TableData<SysUser> selectPageUserList(SysUser user, PageQuery pageQuery);

    void save(SysUser user);

    void update(SysUser user);

    void deleteById(Long id);

    SysUser getById(Long id);

    void updateStatus(List<Long> ids);

    String importData(MultipartFile file) throws Exception ;

    SysUser selectUserByUserName(String userName);

    void exportData(SysUser searchData, HttpServletResponse response);
}
