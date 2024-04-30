package com.xufei.common.helper;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.context.model.SaStorage;
import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import com.xufei.common.constants.CommonConstants;
import com.xufei.common.core.LoginUser;
import com.xufei.common.enums.DeviceType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginHelper {

    public static final String LOGIN_USER_KEY = "loginUser";
    public static final String USER_KEY = "userId";

    public static LoginUser getLoginUser() {
        LoginUser loginUser = (LoginUser) SaHolder.getStorage().get(LOGIN_USER_KEY);

        if (loginUser != null) {
            return loginUser;
        }

        SaSession session = StpUtil.getTokenSession();

        if (ObjectUtil.isNull(session)) {
            return null;
        }

        loginUser = (LoginUser) session.get(LOGIN_USER_KEY);

        SaHolder.getStorage().set(LOGIN_USER_KEY, loginUser);
        return loginUser;
    }

    public static void loginByDevice(LoginUser loginUser, DeviceType deviceType) {
        SaStorage storage = SaHolder.getStorage();

        storage.set(LOGIN_USER_KEY, loginUser);
        storage.set(USER_KEY, loginUser.getUserId());

        SaLoginModel model = new SaLoginModel();
        if (ObjectUtil.isNotNull(deviceType)) {
            model.setDevice(deviceType.getDevice());
        }

        StpUtil.login(loginUser.getUserId(), model);
        StpUtil.getTokenSession().set(LOGIN_USER_KEY, loginUser);
    }

    public static boolean isAdmin(String userName) {
        return Arrays.asList(CommonConstants.ADMIN_LIST).contains(userName);
    }

    public static void refreshLoginUser(LoginUser loginUser) {
        SaHolder.getStorage().set(LOGIN_USER_KEY, loginUser);

        SaSession session = StpUtil.getTokenSession();
        if (ObjectUtil.isNotNull(session)) {
            session.set(LOGIN_USER_KEY,loginUser);
        }
    }
}
