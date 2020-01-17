package com.adminsys.callback.factory;

import com.adminsys.callback.templete.AbstractPayCallbackTemplate;
import com.adminsys.utils.SpringContextUtil;

/**
 * @author Qiang
 * @version 1.0
 * @description 获取具体实现的模版工厂方案
 * @date 2020/1/17 下午8:58
 **/
public class TemplatesFactory {

    public static AbstractPayCallbackTemplate getPayCallbackTemplate(String beanId) {
        return (AbstractPayCallbackTemplate) SpringContextUtil.getBean(beanId);
    }
}
