package moocSuper05;

public class mooc05{
 
	public static void main(String[] args) throws InterruptedException {
		int m=1;
		int n=100000000;
		int k=n-m+1;
		int quotient=k/6;  //商
		int remainder=k%6; //余数
		long sum=0;
		Sum totalSum=new Sum(sum,m,n);
		for(int i=1;i<n+1;i++) {
			sum=sum+i;         
		}
        System.out.println("商："+quotient+"  余数："+remainder+"  参考结果："+sum); 
		Thread[] thread=new Thread[6];
       
		for(int i=0;i<6;i++) {
			if(remainder>0) {
				thread[i]=new Thread(new AccThread(totalSum,quotient+1));
				remainder--;
			}else {
				thread[i]=new Thread(new AccThread(totalSum,quotient));
			}
			thread[i].setName("累加器"+i);
			thread[i].start();
		}
		while(totalSum.flag) {
	        Thread.sleep(1000);
		}
		System.out.println(m+"到"+n+"的总和为："+totalSum.getSum());
	}
}
 
class AccThread implements Runnable{
    private Sum sum;
    private int count;
 
    public AccThread(Sum sum,int count) {
		this.sum=sum;
		this.count=count;
	}
	
    public synchronized void run() {
	    
    	sum.add(count);   
		System.out.println(Thread.currentThread().getName()+" partSum:"+sum.getSum()+" a:"+sum.getA());
	}
}
class Sum{
	public volatile boolean flag=true;
	private long sum=0;
	private int a=0;
	private int n=0;
	public Sum(long sum, int a,int n) {
		super();
		this.sum = sum;
		this.a = a;
		this.n=n;
	}
	public long getSum() {
		return sum;
	}
	public int getA() {
		return a;
	}
	public synchronized void add(int count) {  
		for(int i=0;i<count;i++) {
		   sum=sum+a;         
		   a++;               
		   if(a==n+1) {
			  flag=false;   
		   }
		} 
	}	
}