package com.xufei.controller.system;

import cn.dev33.satoken.annotation.SaIgnore;
import com.xufei.common.core.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/system/todo")
public class SysIndexController {

    @SaIgnore
    @GetMapping("/")
    public R<Map<String,Integer>> index(){

        Map<String, Integer> map = new HashMap<>();
        map.put("审核预付款",2);
        map.put("审核采购单",3);

        return R.ok(map);
    }
}
