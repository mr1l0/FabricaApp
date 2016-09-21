package controles;

import helper.HTMLHelper;
import helper.SQLExecHelper;
import helper.SQLHelper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Tabela;
import BD.TabelaBean;

/**
 * Servlet implementation class Cadastro
 */
@WebServlet("/cadastro")
public class CadastroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastroServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TabelaBean tabela = Tabela.getTabela(request.getParameter("tabela"));
		String psOperacao = request.getParameter("operacao");
		boolean pbDetail = request.getParameter("detail").equalsIgnoreCase("S");
		
		response.setContentType("text/plain");  
		response.setCharacterEncoding("UTF-8"); 
		
		if(psOperacao.equalsIgnoreCase(SQLHelper.getOpinserir())){
			HTMLHelper htmlH = new HTMLHelper();
			response.getWriter().write(htmlH.getCadastro(tabela, SQLHelper.getOpinserir(), pbDetail));			
		}if(psOperacao.equalsIgnoreCase(SQLHelper.getOpdeletar())){
			doPost(request, response);
		}if(psOperacao.equalsIgnoreCase(SQLHelper.getOpatualizar())){
			tabela.carregarTabela(request);
			HTMLHelper htmlH = new HTMLHelper();
			response.getWriter().write(htmlH.getCadastro(tabela, SQLHelper.getOpatualizar(), pbDetail));			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TabelaBean tabela = Tabela.getTabela(request.getParameter("tabela"));
		tabela.carregarTabela(request);
		String operacao = request.getParameter("operacao");
		
		response.setContentType("text/plain");  
		response.setCharacterEncoding("UTF-8"); 
		response.getWriter().write(executarOperacao(tabela, operacao));//"<p class='bg-success'>Operação realizada com sucesso!</p>");		
	}
	
	private String executarOperacao(TabelaBean tabela, String operacao){
		SQLExecHelper SQLExec = new SQLExecHelper();
		
		if(operacao.equalsIgnoreCase(SQLHelper.getOpinserir())){
			if(SQLExec.inserir(tabela)){
				//String SUCESSO = "/Exemplo/aviso?msg="+"Cadastro realizado com sucesso"+"&cor=152251152";
				return SQLExec.getUltimoIdInserido();
			}
		}else
		if(operacao.equalsIgnoreCase(SQLHelper.getOpatualizar())){
			if(SQLExec.atualizar(tabela)){
				String SUCESSO = "<p class='bg-success'>Operação realizada com sucesso!</p>";
				return SUCESSO;
			}
		}
		else
		if(operacao.equalsIgnoreCase(SQLHelper.getOpdeletar())){
			if(SQLExec.deletar(tabela)){
				String SUCESSO = "<p class='bg-success'>Operação realizada com sucesso!</p>";
				return SUCESSO;				
			}
		}
		return "";
	}	

}
