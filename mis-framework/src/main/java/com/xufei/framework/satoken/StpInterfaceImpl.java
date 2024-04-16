package com.xufei.framework.satoken;

import cn.dev33.satoken.stp.StpInterface;
import com.xufei.common.core.LoginUser;
import com.xufei.common.helper.LoginHelper;

import java.util.ArrayList;
import java.util.List;

public class StpInterfaceImpl implements StpInterface {

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {

        LoginUser loginUser = LoginHelper.getLoginUser();
        return new ArrayList<>(loginUser.getMenuPermission());
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        LoginUser loginUser = LoginHelper.getLoginUser();
        return new ArrayList<>(loginUser.getRolePermission());
    }
}
