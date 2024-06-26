package com.xufei.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xufei.common.core.BaseEntity;
import lombok.Data;

@Data
@TableName(value = "sys_notice")
public class SysNotice extends BaseEntity {

    private String noticeTitle;
    private String noticeType;
    private String noticeContent;
    private Integer status;

}
