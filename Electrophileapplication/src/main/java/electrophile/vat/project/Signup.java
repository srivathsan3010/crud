package electrophile.vat.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/sp")
public class Signup extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		String username=req.getParameter("un");
		String password=req.getParameter("pd");
		String dob=req.getParameter("db");
		String mail=req.getParameter("mail");
		String mobileno=req.getParameter("mob");
	
		System.out.println("values entered");
		PrintWriter out=res.getWriter();
		
		out.println("<html> <body>"+""+"hi"+" "+username+"! Click here to"+" "+"<a href=./index.html>login</a>"+""+"</body> </html>");
		out.close();
		//PESRISTANCE LOGIC
		
		Connection con=null;
		PreparedStatement ps=null;
		
		String qry="insert into vathsan.electrophilecustomer values(?,?,?,?,?)";
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver class loaded and registered");
			
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=j2ee&password=root");
			System.out.println("connection established");
			
			ps=con.prepareStatement(qry);
			
			ps.setString(1,password);
			ps.setString(2,username);
			ps.setString(3, dob);
			ps.setString(4,mail);
			ps.setString(5,mobileno);
			
			ps.executeUpdate();
			System.out.println("data inserted into customer table");
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
