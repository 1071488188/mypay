package com.har.unmanned.mfront.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式生成工具
 * Created by jiang on 2017/9/27.
 */
public class RegexUtil {
    // 记录拼接的表达式
    private StringBuffer sb = new StringBuffer();
    // 正则表达式的特殊字符，需要进行转义处理
    private String expectChar = ".+*\\$^?{}()[]|";

    /**
     * 匹配汉字
     */
    public static RegexUtil chinese = new RegexUtil("[\u4e00-\u9fa5]");

    /**
     * 行首
     */
    public static RegexUtil lineHead = new RegexUtil("$");

    /**
     * 行尾
     */
    public static RegexUtil lineTail = new RegexUtil("^");

    /**
     * 匹配除换行外的所有字符
     */
    public static RegexUtil anyButLine = new RegexUtil(".");

    /**
     * 匹配数字
     */
    public static RegexUtil num = new RegexUtil("[0-9]");

    /**
     * 匹配大写字母
     */
    public static RegexUtil upperLetter = new RegexUtil("[A-Z]");

    /**
     * 匹配小写字母
     */
    public static RegexUtil lowLetter = new RegexUtil("[a-z]");

    /**
     * 匹配大小写字母
     */
    public static RegexUtil letter = new RegexUtil("[a-zA-Z]");

    /**
     * 匹配小写字母和数字
     */
    public static RegexUtil lowLetterAndNum = new RegexUtil("[a-z0-9]");

    /**
     * 匹配大写字母和数字
     */
    public static RegexUtil upperLetterAndNum = new RegexUtil("[A-Z0-9]");

    /**
     * 匹配大小写字母和数字
     */
    public static RegexUtil letterAndNum = new RegexUtil("[a-zA-Z0-9]");

    /**
     * 匹配大小写字母、数字、下划线
     */
    public static RegexUtil letterAndNumAndUnderLine = new RegexUtil(
            "[a-zA-Z0-9_]");

    /**
     * 匹配一个单词的边界
     */
    public static RegexUtil boundary = new RegexUtil("\\b");

    /**
     * 匹配一个非单词的边界
     */
    public static RegexUtil notBoundary = new RegexUtil("\\B");

    /**
     * 匹配任何空白字符，包括空格、制表符、换页符等。与 [ \f\n\r\t\v] 等效。
     */
    public static RegexUtil blank = new RegexUtil("\\s");

    /**
     * 匹配任何非空白字符。与 [^ \f\n\r\t\v] 等效。
     */
    public static RegexUtil notBlank = new RegexUtil("\\S");

    /**
     * 匹配任何字类字符，包括下划线。与"[A-Za-z0-9_]"等效。
     */
    public static RegexUtil anyChar = new RegexUtil("\\w");

    /**
     * 与任何非单词字符匹配。与"[^A-Za-z0-9_]"等效。
     */
    public static RegexUtil notAnyChar = new RegexUtil("\\W");

    public RegexUtil() {

    }

    /**
     * 构造时就传入一个正则表达式
     *
     * @param regex 正则表达式
     */
    public RegexUtil(String regex) {
        sb = new StringBuffer(regex);
    }

    /**
     * 构造时就传入一个RegexUtil
     *
     * @param regex 正则表达式
     */
    public RegexUtil(RegexUtil regex) {
        sb = new StringBuffer(regex.toString());
    }

    /**
     * 执行最短匹配（懒惰匹配）
     */
    public void minMatch() {
        // 判断最外面是否是中括号,不是加上中括号
        //sb = addMidBracketIfNo(sb);
        sb.append("?");
    }

    /**
     * 重复0-N次，等效于 {0,}。
     */
    public void repeatZeroOrMore() {
        // 判断最外面是否是中括号,不是加上中括号
        sb = addMidBracketIfNo(sb);
        sb.append("*");
    }

    /**
     * 重复0或1次，等效于 {0,1}或?。
     */
    public void repeatZeroOrOne() {
        // 判断最外面是否是中括号,不是加上中括号
        sb = addMidBracketIfNo(sb);
        sb.append("?");
    }

    /**
     * 重复1-N次，等效于 {1,}。
     */
    public void repeatOneOrMore() {
        // 判断最外面是否是中括号,不是加上中括号
        sb = addMidBracketIfNo(sb);
        sb.append("+");
    }

    /**
     * 重复num次
     *
     * @param num 次数
     */
    public void repeat(int num) {
        // 判断最外面是否是中括号,不是加上中括号
        sb = addMidBracketIfNo(sb);
        sb.append("{" + num + "}");
    }

    /**
     * 重复min-max次
     *
     * @param min 最小
     * @param max 最大
     */
    public void repeat(int min, int max) {
        // 判断最外面是否是中括号,不是加上中括号
        sb = addMidBracketIfNo(sb);
        sb.append("{" + min + "," + max + "}");
    }

    /**
     * 至少重复num次
     *
     * @param num 次数
     */
    public void repeatMin(int num) {
        // 判断最外面是否是中括号,不是加上中括号
        sb = addMidBracketIfNo(sb);
        sb.append("{" + num + ",}");
    }

    /**
     * 若字符串两边不是中括号增加上中括号
     *
     * @param sb 原StringBuffer
     * @return StringBuffer
     */
    private StringBuffer addMidBracketIfNo(StringBuffer sb) {
        if (!chkMidBracket(sb)) {
            return addMinBrackets(sb);
        } else {
            return sb;
        }
    }

    /**
     * 字符串两边加上()
     *
     * @param str 字符串
     * @return StringBuffer
     */
    private StringBuffer addMinBrackets(StringBuffer str) {
        return new StringBuffer("(" + str + ")");
    }

    /**
     * 字符串两边加上[]
     *
     * @param str 字符串
     * @return StringBuffer
     */
    private StringBuffer addMidBrackets(StringBuffer str) {
        return new StringBuffer("[" + str + "]");
    }

    /**
     * 去掉字符串两边的[]
     *
     * @param str 字符串
     * @return String
     */
    private String removeMidBrackets(StringBuffer str) {
        return str.toString().replaceAll("^\\[", "").replaceAll("\\]$", "");
    }

    /**
     * 对字符串里面的特殊字符进行处理
     *
     * @param str 源字符串
     * @return String
     */
    private String handleExpectChar(String str) {
        StringBuffer sbTemp = new StringBuffer();
        char[] arr = str.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            if (expectChar.indexOf(arr[i]) != -1) {
                sbTemp.append("\\" + arr[i]);
            } else {
                sbTemp.append(arr[i]);
            }
        }
        return sbTemp.toString();
    }

    /**
     * 判断字符串最外围是否为中括号
     *
     * @param sb
     * @return boolean 是 true，否则 false。
     */
    private boolean chkMidBracket(StringBuffer sb) {
        if ("[".equals(sb.substring(0, 1))
                && "]".equals(sb.substring(sb.length() - 1))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 追加一个正则
     *
     * @param re 正则
     */
    public void append(RegexUtil re) {
        sb.append(re.toString());
    }

    /**
     * 追加一个正则表达式
     *
     * @param re 正则表达式
     */
    public void append(String re) {
        sb.append(handleExpectChar(re));
    }

    /**
     * 或一个正则
     *
     * @param re 正则
     */
    public void or(RegexUtil re) {
        or(re.toString());
    }

    /**
     * 或一个正则表达式
     *
     * @param re 正则表达式
     */
    public void or(String re) {
        //最外层为中括号
        if (chkMidBracket(sb)) {
            //首先去掉两边的中括号
            sb = new StringBuffer(removeMidBrackets(sb));
        }
        if (re.length() > 1) {
            // 字符串用|
            sb.append("|" + handleExpectChar(re));
        } else {
            // 非字符串直接追加
            sb.append(handleExpectChar(re));
        }
        // 追加上中括号
        sb = new StringBuffer(addMidBrackets(sb));
    }

    /**
     * 对自己进行否处理
     */
    public void not() {
        sb = new StringBuffer("[^" + sb + "]");
    }

    /**
     * 返回正则表达式
     */
    public String toString() {
        return sb.toString();
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //验证邮箱总规则。
        //1.邮箱名允许大小写字母数字下划线2.域名可以含数字、大小写字母、点、下划线，如果有些邮箱要求不同，可自行修改。
        RegexUtil r=new RegexUtil();
        //@之前规则
        RegexUtil before=new RegexUtil(RegexUtil.letterAndNumAndUnderLine);//允许大小写字母和下划线
        before.repeatOneOrMore();//允许重复1-N次
        r.append(before);//将@之前的规则追加到总规则
        r.append("@");//追加上@符号

        //@之后到最后一个域名点之前的规则
        RegexUtil after=new RegexUtil(RegexUtil.letterAndNumAndUnderLine);//允许大小写字母和下划线
        after.or(".");//允许点，防止邮箱二级域名,如：@vip.qq.com
        after.or("-");//域名中允许横线
        after.repeatOneOrMore();//允许重复1-N次
        r.append(after);//追加到总规则
        //顶级域名前的点
        r.append(".");

        //顶级域名的规则
        RegexUtil last=new RegexUtil(RegexUtil.lowLetter);//顶级域名只允许小写字母
        last.repeatOneOrMore();////允许重复1-N次
        r.append(last);
        System.out.println(r);//打印总正则：[a-zA-Z0-9_]+@[a-zA-Z0-9_\.-]+\.[a-z]+

        Pattern p = Pattern.compile(r.toString());
        Matcher m = p.matcher("862990787@qq.com");
        System.out.println(m.matches());
    }
}
