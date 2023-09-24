import java.io.IOException;  
import java.io.PrintWriter;  
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
public class Servlet2 extends HttpServlet {  
public void doPost(HttpServletRequest request, HttpServletResponse response)  
    throws ServletException, IOException {  
        
  
    response.setContentType("text/html");  
    PrintWriter pw = response.getWriter();  
    
    String visitor_name= request.getParameter("visitor_name");
    String visitor_email = request.getParameter("visitor_email");
    String visitor_phone = request.getParameter("visitor_phone");
    String Course = request.getParameter("course");
    String message = request.getParameter("visitor_message");
    String m =new String("SELECT");
    String ex=new String("p");

    if(Course.equals(m))
    {
        String someMessage = "SELECT COURSE!";
        pw.println("<script type='text/javascript'>");
        pw.println("alert(" + "'" + someMessage + "'" + ");</script>");
        pw.println("</head><body></body></html>");
        RequestDispatcher rd=request.getRequestDispatcher("Reservation.html");  
        rd.include(request,response); 
    }
    else
    {
        try
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL","root","bonds@114");          
        PreparedStatement ps=con.prepareStatement("insert into Reservation values(?,?,?,?,?,?)");
            ps.setString(1, visitor_name);
            ps.setString(2,visitor_email);
            ps.setString(3,visitor_phone);
            ps.setString(4,Course);
            ps.setString(5,message);
            ps.setString(6, ex);
            int i=ps.executeUpdate();
            RequestDispatcher rd=request.getRequestDispatcher("pay.html");  
            rd.forward(request,response);  
            con.close();
    }
    catch(ClassNotFoundException | SQLException e)
        {
            pw.println(e);
        }
             
    } 
         

}
  
}  
