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
 * Servlet implementation class PesquisaServlet
 */
@WebServlet("/pesquisa")
public class PesquisaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()pesquisa
     */
    public PesquisaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TabelaBean tabela = Tabela.getTabela(request.getParameter("tabela"));
		boolean detail = request.getParameter("detail").equalsIgnoreCase("S");
		HTMLHelper htmlH = new HTMLHelper();
		tabela.carregarTabela(request);

		
		response.setContentType("text/plain");  
		response.setCharacterEncoding("UTF-8"); 

		String divAlterarCad = "fprincipal";
		
		if(detail)
			divAlterarCad = "fcaddetail";
		else
			response.getWriter().print(htmlH.getDescricaoFiltro(tabela));
		
		response.getWriter().print(htmlH.getGridConsulta(tabela, false, true, divAlterarCad, detail));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
