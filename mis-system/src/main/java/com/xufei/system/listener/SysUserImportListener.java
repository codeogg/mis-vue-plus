package com.xufei.system.listener;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.xufei.common.excel.ExcelListener;
import com.xufei.common.exception.ServiceException;
import com.xufei.system.domain.SysUser;
import com.xufei.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SysUserImportListener implements ExcelListener<SysUser> {

    private ISysUserService userService;
    private int successNum = 0;
    private int failureNum = 0;

    private final StringBuilder successMsg = new StringBuilder();
    private final StringBuilder failureMsg = new StringBuilder();

    public SysUserImportListener() {
        userService = SpringUtil.getBean(ISysUserService.class);
    }

    @Override
    public void invoke(SysUser sysUser, AnalysisContext context) {
        SysUser user = this.userService.selectUserByUserName(sysUser.getUserName());
        try {
            if (ObjectUtil.isNull(user)) {
                userService.save(sysUser);
                successNum++;
                successMsg.append("\r\n").append(successNum).append("、账号 ").append(sysUser.getUserName()).append(" 导入成功");
            } else {
                failureNum++;
                failureMsg.append("\r\n").append(failureNum).append("、账号 ").append(sysUser.getUserName()).append(" 已存在");
            }
        } catch (Exception e) {
            failureNum++;
            String msg = "\r\n" + failureNum + "、账号 " + sysUser.getUserName() + " 导入失败：";
            failureMsg.append(msg).append(e.getMessage());
            log.error(msg, e);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

    @Override
    public String getExcelResult() {
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }
}
