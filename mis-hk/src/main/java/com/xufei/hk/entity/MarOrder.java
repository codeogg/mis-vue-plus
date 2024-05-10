package com.xufei.hk.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@TableName(value = "mar_order")
public class MarOrder {

    private Long id;
    private String serialNo;
    private Integer qty;
    private BigDecimal amount;
    private String currency;
    private LocalDate deliveryDate;
    private String salesman;
    private LocalDateTime createTime;

}
