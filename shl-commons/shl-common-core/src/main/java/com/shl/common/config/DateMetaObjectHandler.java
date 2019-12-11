package com.shl.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * @author songhonglei
 * @version 1.0
 * @description 自定义填充公共 name 字段
 * @date 2019/12/12
 */
public class DateMetaObjectHandler implements MetaObjectHandler {
    private final static String UPDATE_TIME = "updateTime";
    private final static String CREATE_TIME = "createTime";

    /**
     * 插入填充，字段为空自动填充
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Object createTime = getFieldValByName(CREATE_TIME, metaObject);
        Object updateTime = getFieldValByName(UPDATE_TIME, metaObject);
        if (createTime == null || updateTime == null){
            Date date = new Date();
            if (createTime == null){
                setFieldValByName(CREATE_TIME, date, metaObject);
            }
            if (updateTime == null){
                setFieldValByName(UPDATE_TIME, date, metaObject);
            }
        }
    }

    /**
     * 更新填充
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName(UPDATE_TIME, new Date(), metaObject);
    }
}
