<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    
    <div class="social1-container">
                  <a href="user-admin.html" class="social"></a>
    </div>
    <style>
        *{
            background-image:url("image2.jpeg");
            background-repeat:no-repeat;
        }
        table.center 
        {
            margin-left: auto; 
            margin-right: auto;
        }
        .social1-container a {
        background-image: url("2.jpg");
        background-repeat: no-repeat;
	border: 5px solid #000000;
	border-radius: 50%;
	display: inline-flex;
	justify-content: center;
	align-items: center;
	margin: 0 5px;
	height:58px;
	width: 58px;
}
    </style>
    
<body>
    <h1><center><u><i>DETAILS OF REGISTERED STUDENTS</i></u></center></h1>
<table border="3" class="center" style="width:100%">
    <br><br><br>
    
<tr>
<td><b>CANDIDATE NAME</b></td>
<td><b>EMAIL ID</b></td>
<td><b>MOBILE NUMBER</b></td>
<td><b>COURSE</b></td>
<td><b>COMMENT</b></td>
<td><b>PAYMENT STATUS</b></td>


</tr>
<%
try{
Class.forName("com.mysql.cj.jdbc.Driver");  
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL","root","bonds@114");
Statement st=con.createStatement();
String sql ="select * from Reservation";
ResultSet rs= st.executeQuery(sql);
while(rs.next()){
%>
<tr>
<td><%=rs.getString("candidate_name") %></td>
<td><%=rs.getString("email") %></td>
<td><%=rs.getString("ph_number") %></td>
<td><%=rs.getString("course") %></td>
<td><%=rs.getString("comment") %></td>
<td><%=rs.getString("payment") %></td>
</tr>
<%
}
con.close();
} catch (Exception e) {
e.printStackTrace();
}
%>
</table>
</body>
</html>