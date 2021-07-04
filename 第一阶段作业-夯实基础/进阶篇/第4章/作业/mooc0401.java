package moocSuper04;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.util.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class mooc0401 {
	static ArrayList<Student> S=new ArrayList<Student>();
	public static void readXml()
    {
		
    	try 
    	{
    		//采用Dom解析xml文件，固定格式
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse("score.xml");
            
            NodeList usersList = document.getChildNodes(); 
            
            for (int i = 0; i < usersList.getLength(); i++) 
            {
                Node users = usersList.item(i);         
                if (users.getNodeType() == Node.ELEMENT_NODE) {
                	NodeList userList = users.getChildNodes();
	                Student tmp=new Student();
	                for (int j = 0; j < userList.getLength(); j++) 
	                {
	                    	Node meta = userList.item(j);
	                    	if (meta.getNodeType() == Node.ELEMENT_NODE)
	                    	{
	                    		String tag=userList.item(j).getNodeName();
	                    		
	                    		if(tag.equals("name")) {
	                    			tmp.setName(meta.getTextContent());
	                    			
	                    		}
	                    		else if(tag.equals("subject")) {
	                    			tmp.setSubject(meta.getTextContent());
	                    			
	                    		}
	                    		else {
	                    			tmp.setScore(meta.getTextContent());
	                    			
	                    		}
	                    	} 
	                                     
	                }
	                S.add(tmp);
                }
            }            
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
	
	public static void createAndWrite() {
		Gson gson = new Gson();
		for(int i=0;i<S.size();i++) {
			String ob = gson.toJson(S.get(i));
			System.out.println(ob);
			JsonObject json = gson.toJsonTree(S.get(i)).getAsJsonObject(); 
			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
				
				Document document = dbBuilder.newDocument();
				if (document != null) 
				{
					Element student = document.createElement("student");		
					Element name = document.createElement("name");
					Element score = document.createElement("score");
					Element subject = document.createElement("subject");
					name.appendChild(document.createTextNode(gson.fromJson(json.get("name"),String.class))); 
					score.appendChild(document.createTextNode(gson.fromJson(json.get("score"),String.class))); 
					subject.appendChild(document.createTextNode(gson.fromJson(json.get("subject"),String.class))); 
					
					student.appendChild(name);     
					student.appendChild(score);      
					student.appendChild(subject);     
					document.appendChild(student);   
					
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					DOMSource source = new DOMSource(document);
									
					File file = new File("score2.xml");
					StreamResult result = new StreamResult(file);
			 	 
					//将xml内容写入到文件中
					transformer.transform(source, result);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}
		
	}
	
	public static void main(String[] args) {
		readXml();
		createAndWrite();
	}
}
