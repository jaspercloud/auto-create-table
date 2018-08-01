package com.tiho.common.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

/**
 * Created by TimoRD on 2017/6/22.
 */
public class ExpressionDateFormatUtil {

    private ExpressionDateFormatUtil() {

    }

    public static String format(String expression, String dateFormat) {
        String str = format(expression, new Date(), dateFormat);
        return str;
    }

    public static String format(String expression, Date date, String dateFormat) {
        String dateStr = DateFormatUtils.format(date, dateFormat);
        String str = expression.replace("${date}", dateStr);
        return str;
    }
}
