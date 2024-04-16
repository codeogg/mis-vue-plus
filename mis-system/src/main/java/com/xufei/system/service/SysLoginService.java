package com.xufei.system.service;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.xufei.common.constants.CommonConstants;
import com.xufei.common.core.LoginUser;
import com.xufei.common.enums.DeviceType;
import com.xufei.common.exception.ServiceException;
import com.xufei.common.helper.LoginHelper;
import com.xufei.common.utils.ServletUtil;
import com.xufei.common.utils.StringUtil;
import com.xufei.system.domain.SysUser;
import com.xufei.system.mapper.SysUserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class SysLoginService {

    private final SysUserMapper userMapper;
    private final SysPermissionService permissionService;

    public String login(String username, String password, Long siteId) {
        SysUser user = loadUserByUsername(username);

        if (!StringUtil.equals(password, user.getPassword())) {
            throw new ServiceException("用户名或密码不正确!");
        }

        LoginUser loginUser = buildLoginUser(user, siteId);
        LoginHelper.loginByDevice(loginUser, DeviceType.PC);

        //TODO 记录登录日志到DB,使用发布事件的方式,异步使用线程池去插入DB

        recordLoginInfo(user.getId(), username);

        return StpUtil.getTokenValue();
    }

    private void recordLoginInfo(Long userId, String username) {
        SysUser sysUser = new SysUser();
        sysUser.setId(userId);
        sysUser.setLoginIp(ServletUtil.getClientIP());
        sysUser.setLastLoginTime(new Date());
        sysUser.setUpdateBy(username);
        userMapper.updateById(sysUser);

    }

    private LoginUser buildLoginUser(SysUser user, Long siteId) {
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(user.getId());
        loginUser.setDeptId(user.getDeptId());
        loginUser.setUserName(user.getUserName());
        loginUser.setNickName(user.getNickName());
        loginUser.setSiteId(siteId);
        loginUser.setAvatar(user.getAvatar());
        loginUser.setMenuPermission(permissionService.getMenuPermissions(user, siteId));
        loginUser.setRolePermission(permissionService.getRolePermissions(user, siteId));
        loginUser.setDeptName(ObjectUtil.isNull(user.getDept()) ? "" : user.getDept().getDeptName());
        return loginUser;
    }

    public SysUser loadUserByUsername(String username) {
        SysUser user = userMapper.selectOne(new LambdaQueryWrapper<SysUser>()
                .select(SysUser::getUserName, SysUser::getStatus)
                .eq(SysUser::getUserName, username));
        if (ObjectUtil.isNull(user)) {
            log.info("登录用户：{} 不存在.", username);
            throw new ServiceException("账号不存在!");
        } else if (CommonConstants.USER_DISABLED.equals(user.getStatus())) {
            log.info("登录用户：{} 已被禁用.", username);
            throw new ServiceException("账号被禁用!");
        }
        return userMapper.selectUserByUsername(username);
    }

    public void logout() {
        try {
            LoginUser loginUser = LoginHelper.getLoginUser();

            // 用户退出记录日志
//            recordLogininfor(loginUser.getUsername(), Constants.LOGOUT, MessageUtils.message("user.logout.success"));
        } catch (NotLoginException ignored) {

        } finally {
            try {
                StpUtil.logout();
            } catch (NotLoginException ignored) {

            }
        }
    }
}
