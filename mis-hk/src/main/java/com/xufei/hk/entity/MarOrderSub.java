package com.xufei.hk.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName(value = "mar_order_sub")
public class MarOrderSub {

    private Long id;
    private String serialNo;
    private String productNo;
    private String cpno;
    private String productNameCn;
    private String productNameEn;
    private BigDecimal price;
    private Integer qty;
    private Integer sampleQty;

}
