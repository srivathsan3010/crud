package electrophile.vat.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/lc")
public class Logincheck extends HttpServlet
{
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		String username=req.getParameter("un");
		String password=req.getParameter("pd");
		
		PrintWriter out=res.getWriter();
		
		//PERSISTANCE LOGIC
		
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		String qry="select * from vathsan.electrophilecustomer  where username=? and password=?";
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("driver class loaded and registered");
			
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=j2ee&password=root");
			System.out.println("connection established with database");
			
			ps=con.prepareStatement(qry);
			System.out.println("platform created");
			ps.setString(1,username);
			ps.setString(2, password);
			
			rs=ps.executeQuery();
			System.out.println("data validation starts");
			
			if(rs.next())
			{   
				String un=rs.getString(1);
				 out.println("<html> <body bgcolor='grey' > Hi"+" "+username+""+",Your login is Successful!"+ "<a href=./homepage.html> Start purchase </a> </body> </html>"); 
				 
				
			}
			else
			{
				 out.println("<html> <body bgcolor='grey'> Hi"+" "+username+""+",Oops! Your login is Unsuccessful!"+""+"check username or password"+"</body> </html>"); 
			}
			  
			out.close();
			
		} 
		catch (ClassNotFoundException  | SQLException e ) 
		{
			
			e.printStackTrace();
		}
		
		 finally
         {
       	  if(rs!=null)
		try 
       	  {
					rs.close();
		   } 
       	  catch (SQLException e) 
       	  {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
         }
         
         if(ps!=null)
         {
       	  try 
       	  {
				ps.close();
			} 
       	  catch (SQLException e)
       	  {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         }
         
         if(con!=null)
         {
       	  try 
       	  {
				con.close();
			} 
       	  catch (SQLException e) 
       	  {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
	}

}
}
