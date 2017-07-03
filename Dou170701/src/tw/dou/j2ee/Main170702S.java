package tw.dou.j2ee;
//Main170702S==>AddAccount170702S
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Main170702S")
public class Main170702S extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");//此處必加
			System.out.println("Driver OK");
			
			Properties prop = new Properties();
			prop.setProperty("user", "root");
			prop.setProperty("password", "root");
			
			Connection conn = 
					DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/double", prop);
			
			String delid =  request.getParameter("delid");
			if(delid !=  null) {
				conn.createStatement().executeUpdate("delete from cust2 where id = " + delid );
			}
			
			PreparedStatement pstmt=
			conn.prepareStatement("select * from cust2");
			rs = pstmt.executeQuery();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		out.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8'>");
		out.println("<a href='AddAccount170702S'>Add</a>");
		out.println("<hr>");
		out.println("<table border='1' width='100%'>");
		out.println("	<tr>\r\n" + 
				"		<th>ID</th>\n" + 
				"		<th>Name</th>\n" + 
				"		<th>Password</th>\n" +
				"		<th>Delete</th>\n" +
				"	</tr>");
		try {
		if(rs!=null) {
			while(rs.next()) {
				String id = rs.getString("id");
				String account = rs.getString("account");
				out.print("<tr>");
					out.print("<td>" + id + "</td>");
					out.print("<td>" + rs.getString("account") + "</td>");
					out.print("<td>" + rs.getString("passwd") + "</td>");
					out.print("<td><a href='?delid="+ id + "' onclick='return confirm(\"Delete "+ account + "?\"); '>Delete</a></td>");
				out.print("</tr>");
			}
			
		}
		}catch(Exception e) {}
		out.println("</table>");


	}


}
