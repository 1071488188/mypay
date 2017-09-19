package com.har.unmanned.mfront.utils;

import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串操作类
 * @author lixu
 */
public class StringUtil {

	/**
	 * 在指定字符串左边添加指定位数的字符。
	 * @param st 需要处理的字符串
	 * @param pad 追加的字符
	 * @param cnt 追加的位数
	 * @return 追加后的字符串
	 */
	public static String lPad(String st, char pad, int cnt) {

		if (st == null) return st;
		StringBuilder sb = new StringBuilder();
		while (sb.toString().length() < cnt - st.length()) {
			sb.append(pad);
		}
		sb.append(st);
		return sb.toString();
	}

	/**
	 * 给指定字符串在左边添加指定位数的0
	 * @param st 需要添加的字符串
	 * @param cnt 位数
	 * @return 添加后的字符串
	 */
	public static String lPadZero(String st, int cnt) {

		return lPad(st, '0', cnt);
	}
	
	/**
	 * 统计Str中ch字符出现的次数
	 * @param Str
	 * @param ch
	 * @return
	 */
	public static int countStr(String Str, char ch){
		if(CheckUtil.isNull(Str) || CheckUtil.isNull(ch)){
			return -1;
		}
		int count=0;
		for(int i=0;i<Str.length();i++){
			if(Str.charAt(i)==ch){
				count++;
			}
		}
		return count;
	}
	
	/**
	 * 将金额转换成元角分格式
	 * 例：12.5——1250
	 * @param money
	 * @return
	 */
	public static String conversionYJF(String money){
		BigDecimal bigMoney = new BigDecimal(money);
		
		bigMoney = bigMoney.multiply(new BigDecimal(100));
		
		return bigMoney.intValue()+"";
	}
	
	/**
	 * 将金额分转换为元
	 * 例：1250——12.50
	 * @param money
	 * @return
	 */
	public static String conversionYUAN(String money){
		BigDecimal bigMoney = new BigDecimal(money);
		
		bigMoney = bigMoney.divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
		
		return bigMoney+"";
	}


	/**
	 * @description 将字符串的首字母转换成大写形式的
	 * @param str
	 * @return
	 */
	public static String capitalize(String str) {
		if(str == null || str.trim().length() == 0)
			return str;
		return str.substring(0, 1).toUpperCase() + str.substring(1, str.length());
	}
	
	/**
	 * @description 提取最后一个标记之前的字符串
	 * @param str
	 * @param symbol
	 * @return
	 */
	public static String beforeLastSymbol(String str,String symbol) {
		if(str == null || symbol == null)
			return null;
		int index = str.lastIndexOf(symbol);
		return index > -1 ? str.substring(0, index) : "";
	}
	
	/**
	 * @description 提取最后一个标记之后的字符串
	 * @param str
	 * @param symbol
	 * @return
	 */
	public static String afterLastSymbol(String str, String symbol) {
		if (str == null || symbol == null)
			return null;
		int index = str.lastIndexOf(symbol);
		return index > -1 ? str.substring(index + symbol.length(), str.length()) : "";
	}
	
	/**
	 * 给字符串去空
	 * @param str
	 * @return
	 */
	public static String TransNull(String str){
		if(str == null){
			str =  "";
		}
		return str;
	}
	
	/**
	 * 转换为html文字编码.<br>
	 */
	public static String htmltextencoder(String src) {
		if (src == null || src.equals("")) {
			return "";
		}

		String dst = src;
		dst = replaceall(dst, "&amp;", "&");
		dst = replaceall(dst, "&lt;", "<");
		dst = replaceall(dst, "&gt;", ">");
		dst = replaceall(dst, "&quot;", "\"");
		dst = replaceall(dst, "&acute;", "\\");
		dst = replaceall(dst, "\r\n", "<br>");
		dst = replaceall(dst, "\r", "<br>");
		dst = replaceall(dst, "\n", "<br>");
		dst = replaceall(dst, " ", "");
		dst = replaceall(dst, "  ", "");
		return dst;
	}
	
	/**
	 * 将字符串src中的子字符串fnd全部替换为新子字符串rep.<br>
	 * 功能相当于java sdk 1.4的string.replaceall方法.<br>
	 * 不同之处在于查找时不是使用正则表达式而是普通字符串.
	 */
	public static String replaceall(String src, String fnd, String rep) {
		if (src == null || src.equals("")) {
			return "";
		}
		String dst = src;

		int idx = dst.indexOf(fnd);

		while (idx >= 0) {
			dst = dst.substring(0, idx) + rep
					+ dst.substring(idx + fnd.length(), dst.length());
			idx = dst.indexOf(fnd, idx + rep.length());
		}

		return dst;

	}
	
	public static final String escapeHTMLTags(String input) {
		if (input == null || input.length() == 0) {
			return input;
		}
		StringBuffer buf = new StringBuffer(input.length());
		char ch = ' ';
		for (int i = 0; i < input.length(); i++) {
			ch = input.charAt(i);
			if (ch == '<') {
				buf.append("&lt;");
			} else if (ch == '>') {
				buf.append("&gt;");
			} else {
				buf.append(ch);
			}
		}
		return buf.toString();
	}
	public static boolean isNum(String str){
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}
	
//		/**
//		 * 得到 全拼
//		 * 
//		 * @param src
//		 * @return
//		 */
//	public static String getPingYin(String src) {
//		char[] t1 = null;
//		t1 = src.toCharArray();
//		String[] t2 = new String[t1.length];
//		HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
//		t3.setCaseType(HanyuPinyinCaseType.UPPERCASE);
//		t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
//		t3.setVCharType(HanyuPinyinVCharType.WITH_V);
//		String t4 = "";
//		int t0 = t1.length;
//		try {
//			for (int i = 0; i < t0; i++) {
//				// 判断是否为汉字字符
//				if (java.lang.Character.toString(t1[i]).matches(
//						"[\\u4E00-\\u9FA5]+")) {
//					t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
//					t4 += t2[0];
//				} else {
//					t4 += java.lang.Character.toString(t1[i]);
//				}
//			}
//			return t4;
//		} catch (BadHanyuPinyinOutputFormatCombination e1) {
//			e1.printStackTrace();
//		}
//		return t4;
//	}
//
//	/**
//	 * 得到中文首字母
//	 * 
//	 * @param str
//	 * @return
//	 */
//	public static String getPinYinHeadChar(String str) {
//
//		String convert = "";
//		for (int j = 0; j < str.length(); j++) {
//			char word = str.charAt(j);
//			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
//			if (pinyinArray != null) {
//				convert += pinyinArray[0].charAt(0);
//			} else {
//				convert += word;
//			}
//		}
//		return convert;
//	}

	/**
	 * 将字符串转移为ASCII码
	 * 
	 * @param cnStr
	 * @return
	 */
	public static String getCnASCII(String cnStr) {
		StringBuffer strBuf = new StringBuffer();
		byte[] bGBK = cnStr.getBytes();
		for (int i = 0; i < bGBK.length; i++) {
			// System.out.println(Integer.toHexString(bGBK[i]&0xff));
			strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
		}
		return strBuf.toString();
	}
	/**
	 * 产生BaseOrder的订单编号
	 * @param n
	 * @param str
	 * @return
	 */
	public static String getRandomStrByCurrentTime(Integer n, String str){
		
		// 当前时间
		String currentDateStr = DateUtil.getCurrentTimeStamp();
		// 指定位数的随机数字
		String randomStr = RandomUtils.generateDigit(n);
		
		// 返回当前时间 + 随机数字 + 给定的字符串
		return currentDateStr + randomStr + str;
	}
	
	/***
	 * 根据传入的字符串，替换其中{0},{1}这样的参数为传入的参数
	 *
	 * @author tangjiale
	 * @date 2015年8月27日
	 * @param str 需要替换的字符串
	 * @param params 需要替换的值数组
	 * @return 替换完成的字符串
	 */
	public static String getStringWithParam(String str, String[] params) {

		return MessageFormat.format(str, (Object[]) params);
		
	}
	
	/**
	 * 去除字符串两端空格，若传入字符串为null，则返回空字符串""
	 * 
	 * @param origObj
	 * @return
	 */
	public static String trimStr(Object origObj) {
		if(origObj == null) {
			return "";
		}
		return origObj.toString().trim();
	}

	/**
	 * 判断是否为空字符串，null与去两端空格后的字符串若等于""，都视为空字符串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isStrNull(String str) {
		str = trimStr(str);
		return str.equals("");
	}

	
	/**
     * 判断字符是否是中文
     *
     * @param c 字符
     * @return 是否是中文
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串是否是乱码
     *
     * @param strName 字符串
     * @return 是否是乱码
     */
    public static boolean isMessyCode(String strName) {
        Pattern p = Pattern.compile("\\s*|t*|r*|n*");
        Matcher m = p.matcher(strName);
        String after = m.replaceAll("");
        String temp = after.replaceAll("\\p{P}", "");
        char[] ch = temp.trim().toCharArray();
        float chLength = ch.length;
        float count = 0;
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (!Character.isLetterOrDigit(c)) {
                if (!isChinese(c)) {
                    count = count + 1;
                }
            }
        }
        float result = count / chLength;
        if (result > 0.4) {
            return true;
        } else {
            return false;
        }

    }

	/**
	 * 替换字符的首字母为大写
	 * @param sourceStr
	 */
    public static String replaceFirstChar2Upper(String sourceStr) {
    	
    	if (sourceStr != null && sourceStr.length() > 0) {
			sourceStr = sourceStr.substring(0, 1).toUpperCase() + sourceStr.substring(1);
		}
    	
    	return sourceStr;
    }
    
    /**
	 * 替换字符的首字母为小写
	 * @param sourceStr
	 */
    public static String replaceFirstChar2Lower(String sourceStr) {
    	
    	if (sourceStr != null && sourceStr.length() > 0) {
			sourceStr = sourceStr.substring(0, 1).toLowerCase() + sourceStr.substring(1);
		}
    	
    	return sourceStr;
    }
    /**
     * 替换写入日志系统中的内容的敏感数据
     * 
     * @param str
     * @return
     */
    public static String replace(String str) {
    	List<String> list = new ArrayList<String>();
		list.add("\"cardNo\":\"(.*?)\",");
		list.add("\"codeNo\":\"(.*?)\",");
		list.add("\"surl\":\"(.*?)\",");
		list.add("\"code\":\"(.*?)\",");
		for (int i = 0; i < list.size(); i++) {
			Matcher matcher=Pattern.compile(list.get(i)).matcher(str);  
		    while(matcher.find()) {  
		    	String ret = matcher.group(1);  
		    	if(!"".equals(ret)){
		    		str = str.replace(ret, "******");
		    	}
		    }
		}
		
		return str;
    }
    
    /** 
	 * 将url参数转换成map 
	 * @param param aa=11&bb=22&cc=33 
	 * @return 
	 */  
	public static Map<String, Object> getUrlParams(String param) {  
	    Map<String, Object> map = new HashMap<String, Object>(0);  
	    if (StringUtils.isBlank(param)) {  
	        return map;  
	    }  
	    String[] params = param.split("&");  
	    for (int i = 0; i < params.length; i++) {  
	        String[] p = params[i].split("=");  
	        if (p.length == 2) {  
	            map.put(p[0], p[1]);  
	        }  
	    }  
	    return map;  
	}  
	
	/** 
	 * 将map转换成url 
	 * @param map 
	 * @return 
	 */  
	public static String getUrlParamsByMap(Map<String, Object> map) {  
	    if (map == null) {  
	        return "";  
	    }  
	    StringBuffer sb = new StringBuffer();  
	    for (Map.Entry<String, Object> entry : map.entrySet()) {  
	        sb.append(entry.getKey() + "=" + entry.getValue());  
	        sb.append("&");  
	    }  
	    String s = sb.toString();  
	    if (s.endsWith("&")) {  
	        s = StringUtils.substringBeforeLast(s, "&");
	    }  
	    return s;  
	} 
	
	public static int[] cvtStringList2Int(List<String> strList) {
		
		if (strList == null) {
			return null;
		}
		
		int[] cvtArr = new int[strList.size()];
		
		for (int i = 0; i < strList.size(); i++) {
			cvtArr[i] = Integer.valueOf(strList.get(i));
		}
		
		return cvtArr;
	}
	public static long[] cvtStringList2Long(List<String> strList) {
		
		if (strList == null) {
			return null;
		}
		
		long[] cvtArr = new long[strList.size()];
		
		for (int i = 0; i < strList.size(); i++) {
			cvtArr[i] = Long.valueOf(strList.get(i));
		}
		
		return cvtArr;
	}
}
