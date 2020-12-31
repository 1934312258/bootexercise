package com.kevin.firstUtil;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @创建人 zhaowenjian
 * @创建时间 2020/9/1
 * @描述
 */
public class BigDemicalTest {
    public static String makeRefundSn(Long vid) {
        String str1 = getRandom(3);
        String str2 = "100" + vid.toString();
        str2 = str2.substring(str2.length() - 3);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
        String str3 = dateFormat.format(new Date());
        return str1 + str2 + str3;
    }

    public static String getRandom(int len) {
        int rs = (int) ((Math.random() * 9 + 1) * Math.pow(10, len - 1));
        return String.valueOf(rs);
    }

    private void change(BigDecimal bigDecimal) {
        bigDecimal = new BigDecimal(12);
        System.out.println(bigDecimal);
    }

    public static void main(String[] args) {
        BigDemicalTest test = new BigDemicalTest();
        BigDecimal big = new BigDecimal(1.02);
        BigDecimal big1 = new BigDecimal(1.00);

        // 小数部分全部为零，则此方法可以去除小数部分，如果小数部分不为零则不可使用该方法,后面会带上很多非零小数值
        big.stripTrailingZeros().toPlainString();
        String str = "kdgjnkfbgntovbnge";
        String string = str.substring(str.length() - 2);
        System.out.println(string);
        System.out.println(BigDemicalTest.makeRefundSn(273L));
        System.out.println(BigDemicalTest.makeRefundSn(273L));
        System.out.println(BigDemicalTest.makeRefundSn(273L));


        BigDecimal bigDecimal = new BigDecimal(7.11);
        BigDecimal bigDecimal1 = new BigDecimal(200).divide(bigDecimal, 2, RoundingMode.HALF_UP);
        BigDecimal bigDecimal2 = new BigDecimal(200).add(bigDecimal);
        System.out.println(bigDecimal1);
        test.change(new BigDecimal(10).subtract(new BigDecimal(5)).multiply(new BigDecimal(100)));
        System.out.println(new BigDecimal(10).subtract(new BigDecimal(5)).multiply(new BigDecimal(100)));
        System.out.println(bigDecimal.compareTo(BigDecimal.valueOf(bigDecimal.intValue())));

    }

}
