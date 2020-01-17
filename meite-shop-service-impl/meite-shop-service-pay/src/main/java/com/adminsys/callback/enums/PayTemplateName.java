package com.adminsys.callback.enums;

/**
 * @author Qiang
 * @version 1.0
 * @description 支付渠道模板名
 * @date 2020/1/17 下午8:50
 **/
public enum PayTemplateName {

    /**
     *  支付宝模板名称
     */
    ALI_PAY_TEMPLATE("AliPayCallbackTemplate"),
    /**
     *  银联模板名称
     */
    UNION_PAY_TEMPLATE("UnionPayCallbackTemplate");

    PayTemplateName(String templateName){
        this.templateName = templateName;
    }

    private String templateName;

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
}
