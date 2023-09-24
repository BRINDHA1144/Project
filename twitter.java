import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.lang.ClassNotFoundException;
 

public class twitter extends HttpServlet {
   
   // private static final long serialVersionUID = 1L;      
   
   /* public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }*/
   
    // From login.jsp, as a post method only the credentials are passed
    // Hence the parameters should match both in jsp and servlet and
      // then only values are retrieved properly
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException {
        response.setContentType("text/html");
        PrintWriter pw=response.getWriter();
        // We can able to get the form data by means of the below ways.
        // Form arguments should be matched and then only they are recognised
        // login.jsp component names should match and then only
          // by using request.getParameter, it is matched
        String emailId = request.getParameter("ema");
        String password = request.getParameter("ps");
        // To verify whether entered data is printing correctly or not
        // Here the business validations goes. As a sample,
          // we can check against a hardcoded value or pass
          // the values into a database which can be available in local/remote  db
        // For easier way, let us check against a hardcoded value
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL","root","bonds@114");
            if(checktw.validate(emailId,password)){  
        RequestDispatcher rd=request.getRequestDispatcher("Reservation.html");  
        rd.forward(request,response);  
    }  
    else{  
                String someMessage = "Sorry username or password error in twitter!EITHER TRY AGAIN OR SIGN IN/SIGN UP";
                pw.println("<script type='text/javascript'>");
                pw.println("alert(" + "'" + someMessage + "'" + ");</script>");
                pw.println("</head><body></body></html>");  
                RequestDispatcher rd=request.getRequestDispatcher("twitter.html");  
                rd.include(request,response);  
    } 
            /*PreparedStatement ps=con.prepareStatement("insert into LOGIN_TABLE values(?,?)");
            ps.setString(1, emailId);
            ps.setString(2,password);
            int i=ps.executeUpdate();
            pw.println(i+"RECORD INSERTED");*/
            con.close();
        }
        catch(ClassNotFoundException | SQLException e)
        {
            pw.println(e);
        }
    }
}