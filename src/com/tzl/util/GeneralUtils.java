package com.tzl.util;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GeneralUtils {
	private static final String FORMART_DATE="yyyy-MM-dd HH:mm:ss";
	public static String BEGINTIME;
	public static String ENDTIME;
	public static String BEGINHOUR;
	public static String ENDHOUR;
	//将时间转换成指定格式的字符串日期
	public static String formartDateTOString(Date date){
		//实例化SimpleDateFormart对象用来转换日期
		SimpleDateFormat sdf = new SimpleDateFormat(FORMART_DATE);
		//将日期转换成字符串
		String result = sdf.format(date);
		return result;
	}
	//比较当前系统时间是否大于等于直播间结束时间
	public static boolean comparatoTime(int time,boolean target){
		Date currentTime = new Date();
		String strTime = formartDateTOString(currentTime);
		strTime = strTime.split("")[1];
		//将字符串转换成数字
		String [] times = strTime.split(":");
		String cTime = "";
		//将时分秒拼接成HHmmss格式的字符串
		for (int i = 0; i < times.length; i++) {
			cTime+=times[i];
		}
		int intTime = Integer.parseInt(cTime);
		if (target) {
			//比较当前时间与直播间停播时间
			return (intTime>=time)?true:false;
		}else {
			//比较当前时间与直播间开播时间
			return (intTime<=time)?true:false;
		}

	}
}
