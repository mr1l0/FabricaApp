package controles;

import helper.HTMLHelper;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Tabela;
import BD.TabelaBean;

/**
 * Servlet implementation class FKgetDescricaoServlet
 */
@WebServlet("/getDescricaoFKServlet")
public class FKgetDescricaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FKgetDescricaoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TabelaBean tabela = Tabela.getTabela(request.getParameter("tabela"));
		tabela.getCampo(request.getParameter("campoCodigo")).setValor(request.getParameter("valor"));

		response.setContentType("text/plain");  
		response.setCharacterEncoding("UTF-8"); 
		response.getWriter().write(tabela.getCampo(request.getParameter("campoCodigo")).getFK().getValorDescricao());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
