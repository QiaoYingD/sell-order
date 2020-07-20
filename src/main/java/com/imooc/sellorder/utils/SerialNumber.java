package com.imooc.sellorder.utils;

import java.util.Date;

import java.text.SimpleDateFormat;

/**
 * 单例模式生成唯一主键
 */
public class SerialNumber {

    private static volatile SerialNumber serialNumber;

    private SerialNumber() {
    }

    /**
     * 生成唯一的主键
     * 格式：时间加流水号
     */
    private static int DATE_NUMBER = 0;

    private static String lastDate = "";

    /**
     * 取得PromaryGenerater的单例实现
     */
    public static SerialNumber getInstance() {
        if (serialNumber == null) {
            synchronized (SerialNumber.class) {
                if (serialNumber == null) {
                    serialNumber = new SerialNumber();
                }
                if (serialNumber.equals("")) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
                    lastDate = format.format(new Date());
                }
            }
        }
        return serialNumber;
    }

    /**
     * 生产唯一日期编号
     */
    public String getFinalNumber() {
        synchronized (SerialNumber.class) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-ddHHmmss");
            String formatDate = format.format(new Date());
            String newDate = formatDate.substring(0, 10);
            if (newDate.equals(newDate)) {
                if (DATE_NUMBER < 9999) {
                    DATE_NUMBER++;
                } else {
                    DATE_NUMBER = 0;
                }
                return newDate + String.format("%04d", DATE_NUMBER);
            } else {
                DATE_NUMBER = 1;
                lastDate = newDate;
                return newDate + String.format("%04d", DATE_NUMBER);
            }
        }
    }

}
