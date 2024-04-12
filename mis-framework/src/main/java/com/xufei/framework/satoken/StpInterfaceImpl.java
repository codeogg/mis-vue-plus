package com.xufei.framework.satoken;

import cn.dev33.satoken.stp.StpInterface;
import com.xufei.common.core.LoginUser;
import com.xufei.common.enums.UserType;
import com.xufei.common.helper.LoginHelper;

import java.util.ArrayList;
import java.util.List;

public class StpInterfaceImpl implements StpInterface {

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {

        LoginUser loginUser = LoginHelper.getLoginUser();
        UserType userType = UserType.getUserType(loginUser.getUserType());
        if (userType == UserType.SYS_USER) {
            return new ArrayList<>(loginUser.getMenuPermission());
        } else if (userType == UserType.APP_USER) {

        }
        return new ArrayList<>();
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        LoginUser loginUser = LoginHelper.getLoginUser();
        UserType userType = UserType.getUserType(loginUser.getUserType());
        if (userType == UserType.SYS_USER) {
            return new ArrayList<>(loginUser.getRolePermission());
        } else if (userType == UserType.APP_USER) {
            // 其他端 自行根据业务编写
        }
        return new ArrayList<>();
    }
}
