package com.xufei.common.core.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RouterVO {

    private String path;
    private boolean hidden;
    private String redirect;
    private String component;
    private boolean alwaysShow;
    private MetaVO meta;
    private List<RouterVO> children;
}
