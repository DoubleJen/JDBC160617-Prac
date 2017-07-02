package tw.dou.j2ee;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
////I am View(M"V"C)
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Dou170702022")
public class Dou170702022 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		
		String x = (String)request.getAttribute("x");
		String y = (String)request.getAttribute("y");
		Integer result = (Integer)request.getAttribute("result");
		
//		out.println(x + " + " + y + " = " + result);
		
		//Load Template View
		String html = loadView("mycal.html");
		html = String .format(html, x, y, result);
		out.print(html);
	}
	
	private String loadView(String file) {
		String tempPath = getServletContext().getInitParameter("template-path");
		File viewFile = new File(tempPath, file);
		int len = (int)viewFile.length();
		byte[] buf = new byte[len];
		try {
			BufferedInputStream bin = new BufferedInputStream(new FileInputStream(viewFile));
			bin.read(buf);
			bin.close();
		} catch (Exception e) {
			
		}

		return new String(buf);
	}

}
