package com.xufei.controller.system;

import cn.dev33.satoken.annotation.SaIgnore;
import com.xufei.common.core.R;
import com.xufei.system.domain.SysSite;
import com.xufei.system.service.ISysSiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/site")
public class SysSiteController {

    private final ISysSiteService siteService;

    @SaIgnore
    @GetMapping("/list")
    public R<List<SysSite>> getAll() {
        List<SysSite> list = siteService.getAllSites();
        return R.ok(list);
    }
}
