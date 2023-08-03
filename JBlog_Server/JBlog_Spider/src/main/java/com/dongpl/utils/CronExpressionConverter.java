package com.dongpl.utils;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CronExpressionConverter {

    private static final String[] WEEKDAYS = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
    private static final String[] MONTHS = {"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};

    private static final Pattern CRON_PATTERN = Pattern.compile("(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)");

    public static String convertToChinese(String cronExpression) {
        Matcher matcher = CRON_PATTERN.matcher(cronExpression);
        if (matcher.matches()) {
            String seconds = matcher.group(1);
            String minutes = matcher.group(2);
            String hours = matcher.group(3);
            String dayOfMonth = matcher.group(4);
            String month = matcher.group(5);
            String dayOfWeek = matcher.group(6);

            String[] cronParts = {seconds, minutes, hours, dayOfMonth, month, dayOfWeek};

            for (int i = 0; i < cronParts.length; i++) {
                cronParts[i] = convertToChinese(cronParts[i], i);
            }

            return MessageFormat.format("秒：{0}，分钟：{1}，小时：{2}，日期：{3}，月份：{4}，星期：{5}",
                    cronParts[0], cronParts[1], cronParts[2], cronParts[3], cronParts[4], cronParts[5]);
        } else {
            return "无效的Cron表达式";
        }
    }

    private static String convertToChinese(String cronPart, int partIndex) {
        StringBuilder sb = new StringBuilder();
        String[] values = cronPart.split(",");
        for (String value : values) {
            if (partIndex == 3) { // 处理日期部分
                if (value.equals("?")) {
                    sb.append("每天");
                } else {
                    sb.append(value).append("号");
                }
            } else if (partIndex == 5) { // 处理星期部分
                if (value.equals("?")) {
                    sb.append("不限");
                } else {
                    int dayOfWeekNum = Integer.parseInt(value);
                    sb.append(WEEKDAYS[dayOfWeekNum]);
                }
            } else {
                sb.append(value);
            }
            sb.append("，");
        }
        return sb.substring(0, sb.length() - 1);
    }

    public static void main(String[] args) {
        String cronExpression = "0 0 1 1,15 1/3 ?"; // 示例Cron表达式
        String chineseDescription = convertToChinese(cronExpression);
        System.out.println("Cron表达式：" + cronExpression);
        System.out.println("中文描述：" + chineseDescription);
    }
}
