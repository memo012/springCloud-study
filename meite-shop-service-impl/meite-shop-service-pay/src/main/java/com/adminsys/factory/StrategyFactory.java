package com.adminsys.factory;

import com.adminsys.strategy.PayStrategy;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Qiang
 * @version 1.0
 * @description 初始化不同的策略行为
 * @date 2020/1/16 下午3:45
 **/
public class StrategyFactory {

    private static Map<String, PayStrategy> strategyBean = new ConcurrentHashMap<String, PayStrategy>();

    // 思考几个点：
    public static PayStrategy getPayStrategy(String classAddres) {
        try {
            if (StringUtils.isEmpty(classAddres)) {
                return null;
            }
            PayStrategy beanPayStrategy = strategyBean.get(classAddres);
            if (beanPayStrategy != null) {
                return beanPayStrategy;
            }
            // 1.使用Java的反射机制初始化子类
            Class<?> aClass = Class.forName(classAddres);
            // 2.反射机制初始化对象
            PayStrategy payStrategy = (PayStrategy) aClass.newInstance();
            strategyBean.put(classAddres, payStrategy);
            return payStrategy;
        }catch (Exception e){
            return null;
        }
    }
}
