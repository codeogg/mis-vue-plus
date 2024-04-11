package com.xufei.system.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@TableName(value = "sys_logininfor")
public class SysLogininfor implements Serializable {

    private Long infoId;
    private String userName;
    private String ippaddr;
    private String loginLocation;
    private String browser;
    private String os;
    private String status;
    private String msg;
    private Date loginTime;

    @TableField(exist = false)
    public Map<String,Object> params = new HashMap<>();
}
