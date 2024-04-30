package com.xufei.controller.system;

import cn.dev33.satoken.annotation.SaIgnore;
import com.xufei.common.constants.CommonConstants;
import com.xufei.common.core.LoginUser;
import com.xufei.common.core.R;
import com.xufei.common.core.dto.LoginBody;
import com.xufei.common.core.vo.RouterVO;
import com.xufei.common.helper.LoginHelper;
import com.xufei.system.domain.SysMenu;
import com.xufei.system.domain.SysUser;
import com.xufei.system.service.ISysMenuService;
import com.xufei.system.service.ISysUserService;
import com.xufei.system.service.SysLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class SysLoginController {

    private final SysLoginService loginService;
    private final ISysMenuService menuService;

    @SaIgnore
    @PostMapping("/login")
    public R<Map<String, Object>> login(@RequestBody LoginBody loginBody) {
        Map<String, Object> ajax = new HashMap<>();

        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getSiteId());
        ajax.put(CommonConstants.TOKEN, token);
        ajax.put(CommonConstants.SITE_ID, loginBody.getSiteId());

        return R.ok(ajax);
    }

    @SaIgnore
    @PostMapping("/logout")
    public R logout(){
        loginService.logout();
        return R.ok("退出成功");
    }

    @GetMapping("/getInfo")
    public R<Map<String,Object>> getInfo(Long siteId){
        LoginUser loginUser = LoginHelper.getLoginUser();
        Long userId = loginUser.getUserId();

        SysUser user = new SysUser();
        user.setId(userId);
        user.setNickName(loginUser.getNickName());
        user.setAvatar(loginUser.getAvatar());

        loginUser.setSiteId(siteId);

        List<RouterVO> menus = menuService.selectMenuTreeByUserId(loginUser);

        Map<String, Object> ajax = new HashMap<>();
        ajax.put("user",user);
        ajax.put("roles", loginUser.getRolePermission());
        ajax.put("permissions", loginUser.getMenuPermission());
        ajax.put("menus", menus);

        return R.ok(ajax);
    }
}
