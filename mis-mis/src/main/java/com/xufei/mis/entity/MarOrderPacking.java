package com.xufei.mis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("mar_order_packing")
public class MarOrderPacking {

    private Long id;
    private String packingName;
    private String packingCode;
}
