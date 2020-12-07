package com.tengfei.fairy.test.string;

import java.util.Date;
import java.util.Locale;

/**
 * @ Description :String.format()的详细用法，对string进行快速格式化
 * @ Author 李腾飞
 * @ Time 2020-12-01   15:52
 * @ Version :
 */
public class StringFormatTest {

    public static void main(String[] args){
        //常规 类型格式化:
        test1();
        //搭配转换符的标志转化：
        test2();
        //时间格式转换符转化：
        test3();

    }

    /**
     * 常规 类型格式化:
     * 输出结果为：
     * Hi,哈士奇
     * Hi,老鹰:是一种.鸟类
     * 字母h的大写是：H
     * 12.34>33.22的结果是：false
     * 100的一半是：50
     * 100的16进制数是：64
     * 100的8进制数是：144
     * 100元的书包打8.5折扣是：85.000000 元
     * 100的16进制浮点数是：0x1.54p6
     * 100的指数表示：8.500000e+01
     * 10的指数和浮点数结果的长度较短的是：85.0000
     * 100的折扣是85%
     * 字母A的散列码是：41
     */
    public static void test1() {
        System.out.println("----------------------类型格式化-------------------------------------------");
        String str1 = String.format("Hi,%s", "哈士奇");
        System.out.println(str1);
        String str2 = String.format("Hi,%s:%s.%s", "老鹰", "是一种", "鸟类");
        System.out.println(str2);
        System.out.printf("字母h的大写是：%c %n", 'H');
        System.out.printf("12.34>33.22的结果是：%b %n", 12.34 > 33.22);
        System.out.printf("100的一半是：%d %n", 100 / 2);
        System.out.printf("100的16进制数是：%x %n", 100);
        System.out.printf("100的8进制数是：%o %n", 100);
        System.out.printf("100元的书包打8.5折扣是：%f 元%n", 100 * 0.85);
        System.out.printf("100的16进制浮点数是：%a %n", 100 * 0.85);
        System.out.printf("100的指数表示：%e %n", 100 * 0.85);
        System.out.printf("10的指数和浮点数结果的长度较短的是：%g %n", 100 * 0.85);
        System.out.printf("100的折扣是%d%% %n", 85);
        System.out.printf("字母A的散列码是：%h %n", 'A');
    }

    /**
     * 搭配转换符的标志转化：
     * 输出结果为：
     * 全部日期和时间信息：星期三 九月 19 13:47:42 CST 2018
     * 年-月-日格式：2018-09-19
     * 月/日/年格式：09/19/18
     * HH:MM:SS PM格式（12时制）：01:47:42 下午
     * HH:MM:SS格式（24时制）：13:47:42
     * HH:MM格式（24时制）：13:47
     */
    public static void test2() {
        System.out.println("----------------------搭配转换符的标志转化-------------------------------------------");
        Date date = new Date();
        //c的使用
        System.out.printf("全部日期和时间信息：%tc%n", date);
        //f的使用
        System.out.printf("年-月-日格式：%tF%n", date);
        //d的使用
        System.out.printf("月/日/年格式：%tD%n", date);
        //r的使用
        System.out.printf("HH:MM:SS PM格式（12时制）：%tr%n", date);
        //t的使用
        System.out.printf("HH:MM:SS格式（24时制）：%tT%n", date);
        //R的使用
        System.out.printf("HH:MM格式（24时制）：%tR%n", date);
    }

    /**
     * 时间格式转换符转化：
     * 转化后结果：
     * 2位数字24时制的小时（不足2位前面补0）:14
     * 2位数字12时制的小时（不足2位前面补0）:02
     * 2位数字24时制的小时（前面不补0）:14
     * 2位数字12时制的小时（前面不补0）:2
     * 2位数字的分钟（不足2位前面补0）:12
     * 2位数字的秒（不足2位前面补0）:49
     * 3位数字的毫秒（不足3位前面补0）:796
     * 9位数字的毫秒数（不足9位前面补0）:796000000
     * 小写字母的上午或下午标记(英)：pm
     * 小写字母的上午或下午标记（中）：下午
     * 相对于GMT的RFC822时区的偏移量:+0800
     * 时区缩写字符串:CST
     * 1970-1-1 00:00:00 到现在所经过的秒数：1537337569
     * 1970-1-1 00:00:00 到现在所经过的毫秒数：1537337569796
     */
    public static void test3() {
        System.out.println("----------------------时间格式转换符转化-------------------------------------------");
        Date date = new Date();
        //H的使用
        System.out.printf("2位数字24时制的小时（不足2位前面补0）:%tH%n", date);
        //I的使用
        System.out.printf("2位数字12时制的小时（不足2位前面补0）:%tI%n", date);
        //k的使用
        System.out.printf("2位数字24时制的小时（前面不补0）:%tk%n", date);
        //l的使用
        System.out.printf("2位数字12时制的小时（前面不补0）:%tl%n", date);
        //M的使用
        System.out.printf("2位数字的分钟（不足2位前面补0）:%tM%n", date);
        //S的使用
        System.out.printf("2位数字的秒（不足2位前面补0）:%tS%n", date);
        //L的使用
        System.out.printf("3位数字的毫秒（不足3位前面补0）:%tL%n", date);
        //N的使用
        System.out.printf("9位数字的毫秒数（不足9位前面补0）:%tN%n", date);
        //p的使用
        String str = String.format(Locale.US, "小写字母的上午或下午标记(英)：%tp", date);
        System.out.println(str);
        System.out.printf("小写字母的上午或下午标记（中）：%tp%n", date);
        //z的使用
        System.out.printf("相对于GMT的RFC822时区的偏移量:%tz%n", date);
        //Z的使用
        System.out.printf("时区缩写字符串:%tZ%n", date);
        //s的使用
        System.out.printf("1970-1-1 00:00:00 到现在所经过的秒数：%ts%n", date);
        //Q的使用
        System.out.printf("1970-1-1 00:00:00 到现在所经过的毫秒数：%tQ%n", date);
    }
}
