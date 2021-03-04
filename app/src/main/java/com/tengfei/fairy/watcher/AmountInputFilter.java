package com.tengfei.fairy.watcher;

import android.text.InputFilter;
import android.text.Spanned;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 金额filter
 */

public class AmountInputFilter implements InputFilter {
    public static final int INPUT_AREA = 1;
    public static final int INPUT_RATE = 2;

    private int decimalDigits;
    private double decimalMax;
    private int inputType = 1;
    /**
     * 最大可输入个数
     */
    private int maxLength = 12;
    Pattern p;
    /**
     * 最大数字
     */
    public static final int MAX_VALUE = 100;
    /**
     * 小数点后的数字的位数
     */
    public static final int PONTINT_LENGTH = 2;

    /**
     * Constructor.
     *
     * @param decimalDigits maximum decimal digits
     */
    public AmountInputFilter(int inputType, double max, int decimalDigits, int maxLength) {
        this.inputType = inputType;
        this.decimalMax = max;
        this.decimalDigits = decimalDigits;
        this.maxLength = maxLength;
    }

    @Override
    public CharSequence filter(CharSequence source,
                               int start,
                               int end,
                               Spanned dest,
                               int dstart,
                               int dend) {

        if (source.length() == 1 && ".".equals(source)) {
            source = "";
        }

        if (inputType == INPUT_AREA) {
            int dotPos = -1;
            int len = dest.length();
            for (int i = 0; i < len; i++) {
                char c = dest.charAt(i);
                if (c == '.' || c == ',') {
                    dotPos = i;
                    break;
                }
            }
            if (dotPos >= 0) {

                // protects against many dots
                if (source.equals(".") || source.equals(",")) {
                    return "";
                }
                // if the text is entered before the dot
                if (dend <= dotPos) {
                    return null;
                }
                if (len - dotPos > decimalDigits) {
                    return "";
                }
            }
        } else if (inputType == INPUT_RATE) {
            p = Pattern.compile("[0-9]*");
            String oldtext = dest.toString();
            System.out.println(oldtext);
            //验证删除等按键
            if ("".equals(source.toString())) {
                return null;
            }
            //验证非数字或者小数点的情况
            Matcher m = p.matcher(source);
            if (oldtext.contains(".")) {
                //已经存在小数点的情况下，只能输入数字
                if (!m.matches()) {
                    return null;
                }
            } else {
                //未输入小数点的情况下，可以输入小数点和数字
                if (!m.matches() && !source.equals(".")) {
                    return null;
                }
            }
            //验证输入金额的大小
            if (!source.toString().equals("")) {
                double dold = Double.parseDouble(oldtext + source.toString());
                if (dold > decimalMax) {
                    return dest.subSequence(dstart, dend);
                } else if (dold == decimalMax) {
                    if (source.toString().equals(".")) {
                        return dest.subSequence(dstart, dend);
                    }
                }
            }
            //验证小数位精度是否正确
            if (oldtext.contains(".")) {
                int index = oldtext.indexOf(".");
                int len = dend - index;
                //小数位只能2位
                if (len > decimalDigits) {
                    CharSequence newText = dest.subSequence(dstart, dend);
                    return newText;
                }
            }

            //最大可输入长度
            int keep = maxLength - (dest.length() - (dend - dstart));
            if (keep <= 0) {
                return "";
            } else if (keep >= end - start) {
                return null; // keep original
            } else {
                keep += start;
                if (Character.isHighSurrogate(source.charAt(keep - 1))) {
                    --keep;
                    if (keep == start) {
                        return "";
                    }
                }
            }

            return dest.subSequence(dstart, dend) + source.toString();


        }

        return null;
    }

}
