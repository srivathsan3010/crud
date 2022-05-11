package electrophile.vat.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/pc")
public class Pcdesktop extends HttpServlet 
{
	

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		String brand=req.getParameter("pc");
	     int quantity=Integer.parseInt(req.getParameter("qty"));
	     
	     PrintWriter out =res.getWriter();
	        

			//PERSISTANCE LOGIC
			
			Connection con=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			
			String qry="select * from vathsan.pctable  where brand=?";
			
			try 
			{
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("driver class loaded and registered");
				
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=j2ee&password=root");
				System.out.println("connection established with database");
				
				ps=con.prepareStatement(qry);
				System.out.println("platform created");
				
				ps.setString(1,brand);
			    rs=ps.executeQuery();
				System.out.println("data validation starts");
				
				if(rs.next())
				{   
					String nam=rs.getString(1);
					int num=rs.getInt(2);
					int n=num*quantity;
					
					 out.println("<html> <body bgcolor='grey' >" +"Your order of "+brand+" "+"gadget worth"+" " + n +"is added to cart,for confirmation click"+" <a href=./order.html> order </a> </body> </html>"); 
					 
					
				}
				else
				{
					 out.println("<html> <body bgcolor='grey'>"+"Sorry,your order is out of stock/not available"+"</body> </html>"); 
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
