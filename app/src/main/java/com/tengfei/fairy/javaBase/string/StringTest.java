package com.tengfei.fairy.javaBase.string;

/**
 * @ Description :
 * @ Author 李腾飞
 * @ Time 12/16/21   11:17 AM
 * @ Version :
 */
public class StringTest {

    public static void main(String... args) {
        System.out.println("----------------------string.split()-------------------------------------------");
        /**
         * 1、string.split()  拆分字符串
         * 转义字符. | $ *  等转义字符需要加 \\
         */

        String str = new String("Welcome-to-Runoob");
        System.out.println("Welcome-to-Runoob  str.split(\"-\")");
        System.out.println(str.split("-"));

        String str2 = new String("www.runoob.com");
        System.out.println("www.runoob.com  str.split(\"\\.\"");
        System.out.println(str.split("\\."));

        String str3 = new String("acount=? and uu =? or n=?");
        System.out.println("多个分隔符返回值 :");
        for (String retval : str3.split("and|or")) {
            System.out.println(retval);
        }

        System.out.println("----------------------string.substring()-截取字符串------------------------------------------");
        /**
         * 2、string.substring()-截取字符串
         * public String substring(int beginIndex)                    [beginIndex,  )
         * public String substring(int beginIndex, int endIndex)     [beginIndex, endIndex )
         *
         * beginIndex -- 起始索引（包括）, 索引从 0 开始。
         *
         * endIndex -- 结束索引（不包括）。
         */
        String str4 = new String("This is text");

        System.out.print("返回值 :");
        System.out.println(str4.substring(4));

        System.out.print("返回值 :");
        System.out.println(str4.substring(4, 10));

        System.out.println("----------------------String.toCharArray() -------------------------------------------");
        String str5 = new String("www.runoob.com");
        System.out.print("返回值 :");
        System.out.println(str5.toCharArray());

        System.out.println("----------------------String.constans() -------------------------------------------");
        /**
         * String.constans()
         * 包含指定的字符或字符串返回 true，否则返回 false。
         */
        String str6 = "tomorrow is another day";
        System.out.println(str6.contains("day"));
        System.out.println(str6.contains("A"));
        System.out.println(str6.contains(" "));



    }


}
