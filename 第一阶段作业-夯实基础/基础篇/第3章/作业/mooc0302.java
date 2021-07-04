package mooc;

public class mooc0302 {

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		if(n % 2 != 0 && n >= 5 && n <= 11) {
			for(int i = 0; i < n; i ++) {
				//5/2-0=2-0=2
				//5/2-1=2-1=1
				//5/2-2=2-2=0
				//5/2-3=2-3=-1
				//5/2-4=2-4=-2
				for(int space = Math.abs(n / 2 - i); space > 0; space --){
					System.out.print(" ");
				}
				//5-2*2=1
				//5-2*1=3
				for(int star = n - 2 * Math.abs(n / 2 -i); star > 0; star --){
					System.out.print("*");
				}
				System.out.println();
			}
		}
		else
			System.out.println("输入有误，请重新输入");
	}
}