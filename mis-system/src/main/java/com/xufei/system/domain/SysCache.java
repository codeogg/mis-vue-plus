package com.xufei.system.domain;

import lombok.Data;

@Data
public class SysCache {

    private String cacheName = "";
    private String cacheKey = "";
    private String cacheValue = "";
    private String remark = "";

}
