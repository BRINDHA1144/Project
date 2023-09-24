import java.io.IOException;  
import java.io.PrintWriter;  
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
  
import javax.servlet.RequestDispatcher;  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
  
public class SignupServlet extends HttpServlet {  
public void doGet(HttpServletRequest request, HttpServletResponse response)  
        throws ServletException, IOException {  
  
    response.setContentType("text/html");  
    PrintWriter pw = response.getWriter();  
          
    String mail=request.getParameter("email");  
    String pass=request.getParameter("psw"); 
    String pass1=request.getParameter("rpsw"); 
    if(pass.equals(pass1))
  {
        try
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL","root","bonds@114");   
        PreparedStatement ba=con.prepareStatement("select * from LOGIN_table where EMAIL_ID=?");
                ba.setString(1, mail);
                ResultSet rs=ba.executeQuery();  
        if(rs.next())
        {
            String someMessage = "EMAIL ALREADY EXISTS";
            pw.println("<script type='text/javascript'>");
            pw.println("alert(" + "'" + someMessage + "'" + ");</script>");
            pw.println("</head><body></body></html>");
            RequestDispatcher rd=request.getRequestDispatcher("index.html");  
            rd.include(request,response);  
        }
        else
        {
        PreparedStatement ps=con.prepareStatement("insert into LOGIN_TABLE values(?,?)");
            ps.setString(1, mail);
            ps.setString(2,pass);
            int i=ps.executeUpdate();
            RequestDispatcher rd=request.getRequestDispatcher("index.html");  
            rd.forward(request,response);  
        }
            con.close();
    }
    catch(ClassNotFoundException | SQLException e)
        {
            pw.println(e);
        }
          
    }
    else
    {
        String someMessage = "THE PASSWORD AND CONFIRM PASSWORD DOES NOT MATCH!";
            pw.println("<script type='text/javascript'>");
            pw.println("alert(" + "'" + someMessage + "'" + ");</script>");
            pw.println("</head><body></body></html>");
        RequestDispatcher rd=request.getRequestDispatcher("index.html");  
        rd.include(request,response);  
    }
}
}  