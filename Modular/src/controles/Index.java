package controles;

import helper.HTMLHelper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Index
 */
@WebServlet("/index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.setContentType("text/plain");  
		response.setCharacterEncoding("UTF-8"); 
		
		HTMLHelper htmlH = new HTMLHelper();
		response.getWriter().print(htmlH.getCabecario("Bells Modular"));
		response.getWriter().print("<div id='menu'>");
		response.getWriter().print(htmlH.getMenuHTML(""));
		response.getWriter().print("<br>");
		response.getWriter().print("</div>");
		response.getWriter().print("<div id='fmensagem'></div>");
		response.getWriter().print("<div id='fcaddetail' class='cdetail'></div>");
		response.getWriter().print("<div id='fconsulta' class='seletor'></div>");
		response.getWriter().print("<div id='fprincipal' class='cprincipal'></div>");
		response.getWriter().print(htmlH.getRodape());			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
