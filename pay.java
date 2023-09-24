import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.lang.ClassNotFoundException;
 

public class pay extends HttpServlet {
   
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
        String cd = request.getParameter("cn");
        String ex = request.getParameter("exp");
        String yr = request.getParameter("yr"); 
        String cvn = request.getParameter("cv");
        String pp = new String("paid");
        String up = new String("Unpaid");
        // To verify whether entered data is printing correctly or not
        // Here the business validations goes. As a sample,
          // we can check against a hardcoded value or pass
          // the values into a database which can be available in local/remote  db
        // For easier way, let us check against a hardcoded value
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL","root","bonds@114");
            if(checkpay.validate(cd,ex,yr,cvn)){  
                PreparedStatement ps=con.prepareStatement("update Reservation set payment=? where payment=? ");
                        ps.setString(1, pp);
                        ps.setString(2,"p");
                          int i=ps.executeUpdate();
        RequestDispatcher rd=request.getRequestDispatcher("END.html");  
        rd.forward(request,response);  
    }  
    else{  
                PreparedStatement ps=con.prepareStatement("update Reservation set payment=? where payment=? ");
                        ps.setString(1, up);
                        ps.setString(2,"p");
                          int i=ps.executeUpdate();
                          String someMessage = "PAYMENT FAILED!";
                          pw.println("<script type='text/javascript'>");
                          pw.println("alert(" + "'" + someMessage + "'" + ");</script>");
                          pw.println("</head><body></body></html>");
        //pw.print("PAYMENT FAILED");  
        RequestDispatcher rd=request.getRequestDispatcher("index.html");  
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