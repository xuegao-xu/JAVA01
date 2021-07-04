package mooc;

public class mooc0301 {

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int sumNum = n * n;
		for(int i = 1;i <= sumNum;i++) {
			System.out.print(i + '\t');
			if(i % n == 0) {
				System.out.print('\n');
			}		
		}
	}

}
