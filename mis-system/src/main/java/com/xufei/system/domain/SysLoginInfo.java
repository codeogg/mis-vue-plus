package com.xufei.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@TableName(value = "sys_login_info")
public class SysLoginInfo implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String userName;
    private String nickName;
    private String ippAddr;
    private String loginLocation;
    private String browser;
    private String os;
    private Integer status;
    private String msg;
    private Date loginTime;

    @TableField(exist = false)
    public Map<String,Object> params = new HashMap<>();
}
