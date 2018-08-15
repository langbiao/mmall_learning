package com.mmall.utils;

import com.google.common.base.Splitter;

import java.math.BigDecimal;

/**
 * @author laolang
 * @create 2018-08-15 14:17
 * @desc
 **/
public class BigDecimalUtil {

    private BigDecimalUtil() {

    }

    public BigDecimal add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));

        return b1.add(b2);
    }

}
