package mooc;

import java.util.Arrays;
import java.util.Scanner;
 
 class Currency {
	protected String name;       
	private int originalValue;  
	protected int value;          
	
	public static String[] CURRENCY_NAME = { "CNY", "HKD", "USD", "EUR" };
	public static int[] CURRENCY_RATIO = { 100, 118, 15, 13 };
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOriginalValue() {
		return originalValue;
	}
	public void setOriginalValue(int originalValue) {
		this.originalValue = originalValue;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
 
	public Currency(String name, int originalValue, int value) {
		super();
		this.name = name;
		this.originalValue = originalValue;
		this.value = value;
	}
	public Currency() {
		super();
	}
	
}
 
 class HKD extends Currency implements Comparable<Currency> {
	
	public HKD(int a) {
		this.setName(CURRENCY_NAME[1]);
		this.setOriginalValue(a);
		int i= CURRENCY_RATIO[0];
		int j= CURRENCY_RATIO[1];
		float k=(float)i/j;
		a =(int)(a*k);
		this.setValue(a);
	}
 
 
	public int compareTo(Currency another) {
		int i = 0;
		if (i == 0) {
			return value- another.value;
		} else {
			return i; 
		}
	}
 
}
 
 class USD extends Currency implements Comparable<Currency> {
 
	public USD(int b) {
		
		this.setName(CURRENCY_NAME[2]);
		this.setOriginalValue(b);
		int i= CURRENCY_RATIO[0];
		int j= CURRENCY_RATIO[2];
		float k=(float)i/j;
		b =(int)(b*k);
		this.setValue(b);
	}
 
	public int compareTo(Currency another) {
		int i = 0;
		if (i == 0) {
			return value- another.value;
		} else {
			return i; 
		}
	}
 
 
}
 
 class EUR extends Currency implements Comparable<Currency> {
	public EUR(int c) {
		this.setName(CURRENCY_NAME[3]);
		this.setOriginalValue(c);
		int i= CURRENCY_RATIO[0];
		int j= CURRENCY_RATIO[3];
		float k=(float)i/j;
		c =(int)(c*k);
		this.setValue(c);
	}
	public int compareTo(Currency another) {
		int i = 0;
		if (i == 0) {
			return value- another.value;
		} else {
			return i; 
		}
	}
}
 
public class mooc1001 {
	public static void main(String[] args) {
		Currency[] cs = new Currency[3];
		Scanner sc = new Scanner(System.in);
                int a = 0;
                int b = 0;
                int c = 0;
                if (sc.hasNext()) {
                    a = sc.nextInt();
                    cs[0] = new HKD(a);
                }
                if (sc.hasNext()) {
                    b = sc.nextInt();
                    cs[1] = new USD(b);
                }
                if (sc.hasNext()) {
                    c = sc.nextInt();
                    cs[2] = new EUR(c);
                }
      Arrays.sort(cs);
      for (Currency currency : cs) {
		System.out.println(currency.getName()+currency.getOriginalValue());
      }
	}
}