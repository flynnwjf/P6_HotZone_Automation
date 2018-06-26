package Main.utilities;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Utils {
	
	//date time
	public static String getCurrentDate(){
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int day = c.get(Calendar.DATE);		
		return year+((month<10)?("0"+String.valueOf(month)):String.valueOf(month))+((day<10)?("0"+String.valueOf(day)):String.valueOf(day));
	}
	
	//format date
	public static String formatDateString(String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}
	
	public static String dateFormatString(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(new Date());
	}
	
	//format decimal
	public static String formatNumWithTwoDecimal(double num){
		DecimalFormat df = new DecimalFormat ("#.00");
		return df.format(num);
	}
	
	//random num
	public static int getRandomNum(int bound){
		Random r= new Random();
		return r.nextInt(bound)+1;
	}
	
	//sleep
	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
