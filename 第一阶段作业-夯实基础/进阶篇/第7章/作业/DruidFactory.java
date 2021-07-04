package moocSuper07;

import java.sql.Connection;

import com.alibaba.druid.pool.DruidDataSource;
 
public class DruidFactory {
   private static DruidDataSource dataSource=null;
   
   public static void init()throws Exception{
	   dataSource=new DruidDataSource();
	   dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	   dataSource.setUsername("root");
	   dataSource.setPassword("123456");
	   dataSource.setUrl("jdbc:mysql://localhost:3306/book?");
	   dataSource.setMinIdle(1);
	   dataSource.setMaxActive(10);
	   
   }
   public static Connection getConnection()throws Exception{
	   if(null==dataSource) {
		   init();
	   }
	   return dataSource.getConnection();
   }
}