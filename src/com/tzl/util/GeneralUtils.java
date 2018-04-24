package com.tzl.util;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GeneralUtils {
	private static final String FORMART_DATE="yyyy-MM-dd HH:mm:ss";
	public static String BEGINTIME;
	public static String ENDTIME;
	public static String BEGINHOUR;
	public static String ENDHOUR;
	//��ʱ��ת����ָ����ʽ���ַ�������
	public static String formartDateTOString(Date date){
		//ʵ����SimpleDateFormart��������ת������
		SimpleDateFormat sdf = new SimpleDateFormat(FORMART_DATE);
		//������ת�����ַ���
		String result = sdf.format(date);
		return result;
	}
	//�Ƚϵ�ǰϵͳʱ���Ƿ���ڵ���ֱ�������ʱ��
	public static boolean comparatoTime(int time,boolean target){
		Date currentTime = new Date();
		String strTime = formartDateTOString(currentTime);
		strTime = strTime.split("")[1];
		//���ַ���ת��������
		String [] times = strTime.split(":");
		String cTime = "";
		//��ʱ����ƴ�ӳ�HHmmss��ʽ���ַ���
		for (int i = 0; i < times.length; i++) {
			cTime+=times[i];
		}
		int intTime = Integer.parseInt(cTime);
		if (target) {
			//�Ƚϵ�ǰʱ����ֱ����ͣ��ʱ��
			return (intTime>=time)?true:false;
		}else {
			//�Ƚϵ�ǰʱ����ֱ���俪��ʱ��
			return (intTime<=time)?true:false;
		}

	}
}
