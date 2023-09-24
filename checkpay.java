import java.sql.*;  
  
import java.sql.Connection;
import java.sql.DriverManager;

public class checkpay {  
public static boolean validate(String cn, String exp, String yr,String cv){  
boolean status=false;  
try{  
Class.forName("com.mysql.cj.jdbc.Driver");  
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL","root","bonds@114");
      
PreparedStatement ps=con.prepareStatement("select * from pay where cardno=? and cvv=?");  
ps.setString(1,cn);  
//ps.setString(2,ex);//
//ps.setString(3,yr);//
ps.setString(2,cv);

      
ResultSet rs=ps.executeQuery();  
status=rs.next();  
          
}catch(Exception e)
{System.out.println(e);}  
return status;  
}  
}  