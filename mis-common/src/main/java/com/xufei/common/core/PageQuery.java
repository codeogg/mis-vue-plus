package com.xufei.common.core;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xufei.common.exception.ServiceException;
import com.xufei.common.utils.StringUtil;
import lombok.Data;

import java.io.Serializable;

@Data
public class PageQuery<T> implements Serializable {

    public static final Integer DEFAULT_CURRENT_PAGE = 1;
    public static final Integer DEFAULT_PAGE_SIZE = 15;

    // 当前页码
    private Integer currentPage;
    // 分页大小
    private Integer pageSize;
    // 排序列
    private String sortByColumn;
    // asc还是desc
    private String sortType;

    private T searchData;

    public <T> Page<T> build() {
        Integer pageNum = ObjectUtil.defaultIfNull(getCurrentPage(), DEFAULT_CURRENT_PAGE);
        Integer pageSize = ObjectUtil.defaultIfNull(getPageSize(), DEFAULT_PAGE_SIZE);
        if (pageNum <= 0) {
            pageNum = DEFAULT_CURRENT_PAGE;
        }

        Page<T> page = new Page<>(pageNum, pageSize);
        OrderItem orderItem = buildOrderItem();
        if (ObjectUtil.isNotNull(orderItem)) {
            page.addOrder(orderItem);
        }
        return page;
    }

    private OrderItem buildOrderItem() {
        if (StringUtil.isBlank(sortByColumn)) {
            return null;
        }
        String column = StringUtil.toUnderlineCase(sortByColumn);

        if (StringUtil.isBlank(sortType) || "asc".equalsIgnoreCase(sortType)) {
            return OrderItem.asc(column);
        }
        if (StringUtil.isNotBlank(sortType) && "desc".equalsIgnoreCase(sortType)) {
            return OrderItem.desc(column);
        } else {
            throw new ServiceException("排序参数有误");
        }

    }


}
