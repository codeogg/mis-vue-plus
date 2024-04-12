package com.xufei.framework.handler;

import com.xufei.common.utils.StringUtil;
import org.redisson.api.NameMapper;

public class KeyPrefixHandler implements NameMapper {

    private final String keyPrefix;

    public KeyPrefixHandler(String keyPrefix) {
        //前缀为空 则返回空前缀
        this.keyPrefix = StringUtil.isBlank(keyPrefix) ? "" : keyPrefix + ":";
    }

    @Override
    public String map(String name) {
        if (StringUtil.isBlank(name)) {
            return null;
        }
        if (StringUtil.isNotBlank(keyPrefix) && !name.startsWith(keyPrefix)) {
            return keyPrefix + name;
        }
        return name;
    }

    @Override
    public String unmap(String name) {
        if (StringUtil.isBlank(name)) {
            return null;
        }
        if (StringUtil.isNotBlank(keyPrefix) && name.startsWith(keyPrefix)) {
            return name.substring(keyPrefix.length());
        }
        return name;
    }
}
