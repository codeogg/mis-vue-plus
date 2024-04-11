package com.xufei.system.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "sys_user_post")
public class SysUserPost implements Serializable {

    private Long userId;
    private Long postId;
}
