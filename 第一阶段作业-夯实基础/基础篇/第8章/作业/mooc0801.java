package mooc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.text.SimpleDateFormat;

    public class mooc0801 {

        public static void main(String[] args) throws IOException
        {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("���֤�ţ�");
            String IDcard = buffer.readLine();

            if(IDcard.length()!=18)
            {
                System.out.println("0000-00-00");

            }
            else
            {
                int i,n=0;
                for( i=0;i<17;i++)
                {
                    if(IDcard.charAt(i)>='0'&&IDcard.charAt(i)<='9')
                    {
                        n++;
                    }
                    else
                        break;
                }
                
                int sum = 0;
            	int[] coefficient = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
            	for(int j = 0;j < 17;j++) {
            		sum = sum +IDcard.charAt(j) * coefficient[j];
            	}
            	int remainder = sum % 11;
                boolean check = false;
                System.out.println("������"+remainder);
                switch (remainder) {
                    case 0:
                        if ("1".equals(IDcard.substring(17)))
                        	check = true;
                    case 1:
                        if ("0".equals(IDcard.substring(17)))
                        	check = true;
                    case 2:
                        if ("X".equals(IDcard.substring(17)))
                        	check = true;
                    case 3:
                        if ("9".equals(IDcard.substring(17)))
                        	check = true;
                    case 4:
                        if ("8".equals(IDcard.substring(17)))
                        	check = true;
                    case 5:
                        if ("7".equals(IDcard.substring(17)))
                        	check = true;
                    case 6:
                        if ("6".equals(IDcard.substring(17)))
                        	check = true;
                    case 7:
                        if ("5".equals(IDcard.substring(17)))
                        	check = true;
                    case 8:
                        if ("4".equals(IDcard.substring(17)))
                        	check = true;
                    case 9:
                        if ("3".equals(IDcard.substring(17)))
                        	check = true;
                    case 10:
                        if ("2".equals(IDcard.substring(17)))
                        	check = true;
                }
                if(n<=16|| check || !(IDcard.charAt(17)>='0'&&IDcard.charAt(17)<='9'||IDcard.charAt(17)>='a'&&IDcard.charAt(17)<='z'))
                    System.out.println("0000-00-00");
                else
                {
                    int year,month,day;
                    year=Integer.valueOf(IDcard.substring(6, 10));
                    month=Integer.valueOf(IDcard.substring(10, 12));
                    day=Integer.valueOf(IDcard.substring(12, 14));

                    String strDate = IDcard.substring(6, 14);
                    // ���ַ�������ȡ����������
                    String pat1 = "yyyyMMdd" ;
                    // ����ȡ����������ֱ�Ϊָ���ĸ�ʽ
                    String pat2 = "yyyy-MM-dd" ;
                    SimpleDateFormat sdf1 = new SimpleDateFormat(pat1) ;        
                    SimpleDateFormat sdf2 = new SimpleDateFormat(pat2) ;        
                    Date d = null ;
                    try{
                        d = sdf1.parse(strDate) ;   // ���������ַ����е�������ȡ����
                    }catch(Exception e){            // ����ṩ���ַ�����ʽ�д���������쳣����
                        e.printStackTrace() ;       // ��ӡ�쳣��Ϣ
                    }
                    String r=sdf1.format(d);
                    if(r.equals(strDate))
                        System.out.println(sdf2.format(d)) ;    // �����ڱ�Ϊ�µĸ�ʽ
                    else
                        System.out.println("0000-00-00");

                }
            }
        }
    }

