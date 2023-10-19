package p1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ecom_productslist
 */
@WebServlet("/ecom_productslist")
public class ecom_productslist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ecom_productslist() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
        PrintWriter out2=response.getWriter();
        //initializing the database connection
        Connection mycon=null;
        Statement sql_stmt=null;
        ResultSet records=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            mycon = DriverManager.getConnection("jdbc:mysql://localhost:3306/product_list","root","Iforgot#6");
            sql_stmt = mycon.createStatement();  
            records = sql_stmt.executeQuery("select * from products");
            
            out2.println("<html><head><title>Product List</title></head><body>");
            out2.print("<h1>" + "Products List" + "</h1>");
            out2.println("<table border='1' width='100%' cellpadding='6'>");
            
            out2.println("<tr>");
            out2.println("<th>Picture</th>");
            out2.println("<th>Product Name</th>");
            out2.println("<th>Product Description</th>");
            out2.println("<th>Price</th>");
            out2.println("<th>Product ID</th>");
            

            out2.println("</tr>");
            
            while (records.next()) {
            	out2.print("<tr>");
            	out2.println("<td>"+"<img src=\""+records.getString("image")+ "\" style=\"width:200px\">"+"</td>");
                out2.print("<td>"+records.getString("name")+"</td>");
                out2.print("<td>"+records.getString("description")+"</td>");
                out2.print("<td>"+"$"+records.getString("price")+"</td>");
                out2.print("<td>"+records.getString("pid")+"</td>");
                out2.print("</tr>");
            }
            out2.print("</table>");
        }
        
        
        
        catch(Exception e) {e.printStackTrace();}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
