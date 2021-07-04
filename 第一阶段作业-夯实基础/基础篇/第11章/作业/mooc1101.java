package mooc;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

class MyComparator implements Comparator<Map.Entry<String,Integer>>{
    public int compare(HashMap.Entry<String,Integer> he1,HashMap.Entry<String,Integer> he2) {
        return he2.getValue()-he1.getValue();
    }
}

public class mooc1101 {
    static final int MAX = 50;

    public static void main(String[] args) {
        HashMap<String, Integer> hm = new HashMap<String,Integer>();
        
        //文件读取
        try(BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("D:\\test\\a.txt")))){
            String line;
            while((line = br.readLine())!=null) {
                Pattern p = Pattern.compile("\\s+");	//正则匹配空格
                String words[]= p.split(line);			//获取的字符串进行分割单词
                
                for(String item : words) {
                    if(item.length()!=0) {
                        if(hm.get(item)==null)
                            hm.put(item, 1);			//将数据放入map中
                        else
                            hm.put(item, hm.get(item)+1);
                    }
                }
            }
        }catch(Exception e) {
            System.err.println(e.getMessage());
        }
        
        //HashMap转换为list，进行排序
        List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(hm.entrySet());
        Collections.sort(list,new MyComparator());
        
        //将数据写入文件
        try(BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\test\\b.txt")))){
            for(Map.Entry<String, Integer> item: list) {
                bw.write(item.getKey()+","+item.getValue());
                bw.newLine();
            }
        }catch(Exception e) {
            System.err.println(e.getMessage());
        }
        
    }

}