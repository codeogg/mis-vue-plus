package com.xufei.framework.handler;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.xufei.common.core.BaseEntity;
import com.xufei.common.core.LoginUser;
import com.xufei.common.exception.ServiceException;
import com.xufei.common.helper.LoginHelper;
import com.xufei.common.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

@Slf4j
public class CreateAndUpdateMetaObjectHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
        try {
            if (ObjectUtil.isNotNull(metaObject) && metaObject.getOriginalObject() instanceof BaseEntity) {
                BaseEntity baseEntity = (BaseEntity) metaObject.getOriginalObject();
                Date current = ObjectUtil.isNotNull(baseEntity.getCreateTime()) ? baseEntity.getCreateTime() : new Date();
                baseEntity.setCreateTime(current);
                String username = StringUtil.isNotBlank(baseEntity.getCreateBy()) ? baseEntity.getCreateBy() : getLoginUsername();
                baseEntity.setCreateBy(username);
            }
        } catch (Exception e) {
            throw new ServiceException(HttpStatus.HTTP_UNAUTHORIZED, "自动注入异常 => " + e.getMessage());
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        try {
            if (ObjectUtil.isNotNull(metaObject) && metaObject.getOriginalObject() instanceof BaseEntity) {
                BaseEntity baseEntity = (BaseEntity) metaObject.getOriginalObject();
                baseEntity.setUpdateTime(new Date());
                String username = getLoginUsername();
                if (StringUtil.isNotBlank(username)) {
                    baseEntity.setUpdateBy(username);
                }
            }
        } catch (Exception e) {
            throw new ServiceException(HttpStatus.HTTP_UNAUTHORIZED, "自动注入异常 => " + e.getMessage());
        }
    }

    private String getLoginUsername() {
        LoginUser loginUser = LoginHelper.getLoginUser();
        return loginUser.getNickName();
    }


}
