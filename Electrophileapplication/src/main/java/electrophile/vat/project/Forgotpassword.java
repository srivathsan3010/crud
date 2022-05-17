package electrophile.vat.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/fpd")
public class Forgotpassword extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{                  
		               //PRESENTATION LOGIC
	                   
		               String username=req.getParameter("un");
	                   String password=req.getParameter("npd");
	                  
	                   
	                   PrintWriter out=res.getWriter();
	                   
	                   out.println("<html> <body bgcolor=grey> hey"+" "+username+" "+"your credentials are updated now.."+" "+"login using new password/credential <br>"+""+"<a href=./index.html>click here</a> </body> </html>");
	                   out.close();
	                   //PERSISTANCE LOGIC

	           		Connection con=null;
	           		PreparedStatement ps=null;
	           		
	           		String qry="update vathsan.electrophilecustomer set password=? where username=?";
	           		
	           		try
	           		{
	           			Class.forName("com.mysql.jdbc.Driver");
	           			System.out.println("Driver class loaded and registered");
	           			
	           			con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=j2ee&password=root");
	           			System.out.println("connection established");
	           			
	           			ps=con.prepareStatement(qry);
	           			
	           			
	        			ps.setString(1,password);
	        			ps.setString(2,username);
	           			ps.executeUpdate();
	           		
	           			System.out.println("data updated into customer table");
	           		}
	           		catch(ClassNotFoundException | SQLException e)
	           		{
	           			e.printStackTrace();
	           		}
	           		
	           		finally
	           		{
	           			if(ps!=null)
	           			{
	           				try {
	           					ps.close();
	           				} catch (SQLException e)
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

}
