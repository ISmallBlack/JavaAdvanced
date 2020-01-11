package com.zhangch.protogenesislogback.configuration;

import ch.qos.logback.core.PropertyDefinerBase;
import lombok.Getter;
import lombok.Setter;

/**
 * 功能说明: <br>
 * 系统版本: 1.0 <br>
 * 开发人员: zhanch
 * 开发时间: 2020/1/6<br>
 * <br>
 */
@Setter
@Getter
public class TestPropertyDefiner extends PropertyDefinerBase {
    String color;
    String shape;
    String size;
    @Override
    public String getPropertyValue() {
        System.out.println("color："+color+"shape："+shape+"size："+size);
        return "info";
    }
}
