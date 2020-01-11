package com.zhangch.javaknowledge.money;

import org.javamoney.moneta.Money;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.NumberValue;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.ExchangeRate;
import javax.money.convert.ExchangeRateProvider;
import javax.money.convert.MonetaryConversions;
import java.util.List;
import java.util.Locale;

/**
 * 货币与金额
 * 功能说明: <br>
 * 系统版本: 1.0 <br>
 * 开发人员: zhanch
 * 开发时间: 2019/12/19<br>
 * <br>
 */
public class MoneyTest {

    public static void main(String[] args) {
        //据货币代码来获取货币单位
        CurrencyUnit currencyUnit = Monetary.getCurrency("USD");
        //亦或根据国家及地区来获取货币单位
        CurrencyUnit   unit    = Monetary.getCurrency(Locale.US);
        //get the default ExchangeRateProvider (CompoundRateProvider)
        ExchangeRateProvider exchangeRateProvider = MonetaryConversions.getExchangeRateProvider();
        ExchangeRate rate = exchangeRateProvider.getExchangeRate("USD", "CNY");
        NumberValue factor = rate.getFactor(); // 1.2537 (at time writing)
        CurrencyUnit baseCurrency = rate.getBaseCurrency(); // EUR
        CurrencyUnit targetCurrency = rate.getCurrency(); // USD
        System.out.println(factor);
        ExchangeRateProvider ecbExchangeRateProvider = MonetaryConversions.getExchangeRateProvider("ECB");


        CurrencyConversion dollarConversion = MonetaryConversions.getConversion("CNY");

        CurrencyConversion ecbDollarConversion = ecbExchangeRateProvider.getCurrencyConversion("USD");

        MonetaryAmount tenEuro = Money.of(10, "USD");

        MonetaryAmount inDollar = tenEuro.with(dollarConversion);
        System.out.println(inDollar);
    }
}
