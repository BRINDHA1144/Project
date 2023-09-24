import java.sql.*;  
  
import java.sql.Connection;
import java.sql.DriverManager;

public class LoginDao {  
public static boolean validate(String name,String pass){  
boolean status=false;  
try{  
Class.forName("com.mysql.cj.jdbc.Driver");  
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL","root","bonds@114");
      
PreparedStatement ps=con.prepareStatement(  
"select * from LOGIN_TABLE where EMAIL_ID=? and PASSWORD=?");  
ps.setString(1,name);  
ps.setString(2,pass);  
      
ResultSet rs=ps.executeQuery();  
status=rs.next();  
          
}catch(Exception e){System.out.println(e);}  
return status;  
}  
}  