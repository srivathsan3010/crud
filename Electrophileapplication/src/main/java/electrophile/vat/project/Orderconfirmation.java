package electrophile.vat.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cnf")
public class Orderconfirmation extends HttpServlet
{
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		String name=req.getParameter("nm");
		int mobilenumber=Integer.parseInt(req.getParameter("mob"));
		String address=req.getParameter("ad");
		
		PrintWriter out=res.getWriter();
		out.println("<html> <body bgcolor='grey'>"+"Hooray!! your order is confirmed , Thank you,for using electrophile, Hope you enjoyed our service"+" "+"click to purchase again"+" " +"<a href=./homepage.html>grab more</a> "+" "+"have a great day!"+"</body> </html>");
		
		//PESRISTANCE LOGIC
		
				Connection con=null;
				PreparedStatement ps=null;
				
				String qry="insert into vathsan.orderconfirmation values(?,?,?)";
				
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					System.out.println("Driver class loaded and registered");
					
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=j2ee&password=root");
					System.out.println("connection established");
					
					ps=con.prepareStatement(qry);
					
					ps.setString(1,name);
					ps.setInt(2,mobilenumber);
					ps.setString(3, address);
					
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


