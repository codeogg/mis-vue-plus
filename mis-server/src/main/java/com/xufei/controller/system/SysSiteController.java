package com.xufei.controller.system;

import cn.dev33.satoken.annotation.SaIgnore;
import com.xufei.common.core.R;
import com.xufei.system.service.ISysSiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/site")
public class SysSiteController {

    private final ISysSiteService siteService;

    @SaIgnore
    @GetMapping("/list")
    public R<Map<String, Object>> getAll() {
        Map<String, Object> map = siteService.getAllSites();
        return R.ok(map);
    }
}
