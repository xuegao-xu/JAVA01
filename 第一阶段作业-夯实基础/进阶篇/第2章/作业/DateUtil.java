package mooc01;

public class DateUtil {
	public  boolean isLeapYear(int year) {
		
	if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0){ 
		return true;
	 }
	 else{
		return false;
	 }
  } 

}
