package javaTest;

import java.util.Scanner;

public class mooc4{

	public static void main(String[] args){

		//����Scanner����

		//System.in��ʾ��׼�����룬Ҳ���Ǽ�������

                Scanner sc = new Scanner(System.in);

                //����hasNextXXX()�ж��Ƿ�����һ������

                int a = 0;

                int b = 0;

                int c = 0;
            	System.out.println("������a");
                if (sc.hasNext()) {
                    a = sc.nextInt();

                }
            	System.out.println("������b");
                if (sc.hasNext()) {
                    b = sc.nextInt();

                }
            	System.out.println("������c");
                if (sc.hasNext()) {
                    c = sc.nextInt();

                }

                if(a==0 || b==0 || c==0)

                {

        	    System.out.println("���벻��Ϊ0");

        	    System.exit(-1);

                }       

                MyNumber obj1 = new MyNumber();
                MyNumber obj2 = new MyNumber();
                MyNumber obj3 = new MyNumber();

                //�����￪ʼ������swap������������ĳ���
                obj1.num = a;
                obj2.num = b;
                obj3.num = c;
                swap(obj1,obj2);
                swap(obj2,obj3);
                swap(obj1, obj2);
                System.out.println(obj1.num+","+obj2.num+","+obj3.num);



	}

	

	public static void swap(MyNumber m, MyNumber n){

		if(m.num > n.num){

			int s = m.num;

			m.num = n.num;

			n.num = s;

		}

	}

}



class MyNumber{

	int num;

}