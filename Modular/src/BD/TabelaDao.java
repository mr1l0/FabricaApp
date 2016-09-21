package BD;

import fabrica.Conexao;
import fabrica.GeralConst;
import helper.JSHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.Tabela;

public class TabelaDao {
	public ArrayList<Object> getTodas() {
		ArrayList<Object> todos = new ArrayList<Object>();
		Connection con = null;
		PreparedStatement pstm = null;
		try{
			con = Conexao.conectar();
            pstm = con.prepareStatement("select * from information_schema.tables where table_schema='"+GeralConst.schemaConexao+"' ");
	  	    TabelaBean tabela = null;
	  	    CampoDao campoDao = new CampoDao();
	  	    DetailDao detailDao = new DetailDao();
		    ResultSet rs = pstm.executeQuery();
		    while(rs.next()){
	   			tabela = new TabelaBean();
				tabela.setNome(rs.getString("TABLE_NAME"));
				tabela.setCaption(rs.getString("TABLE_COMMENT"));
				tabela.setCampos(campoDao.getTodosCampos(tabela.getNome()));
				tabela.setCamposPK(campoDao.getTodosCamposPK(tabela.getNome()));
				tabela.setDetails(detailDao.getDetailsTabela(tabela.getNome()));
				todos.add(tabela);
		    }
		    PreencherEhDetail(todos);
	  	  	return todos;
		}catch(Exception ex){
			System.err.println(ex.getMessage());
			return null;
		}finally{
	      	try {
				if(pstm != null) pstm.close();
				if(con != null) con.close();
	      	} catch (SQLException e) {
				e.printStackTrace();
			}	      
	    }
	}
	
	private TabelaBean getTabela(ArrayList<Object> arrayDeTabelas, String nome){
		for(int i=0; i < arrayDeTabelas.size(); i++){
			TabelaBean tabela = (TabelaBean) arrayDeTabelas.get(i);
			if(tabela.getNome().equalsIgnoreCase(nome))
				return tabela;
		}
		return null;
	}
	
	public void PreencherEhDetail(ArrayList<Object> todos){
		ArrayList<Object> todosDetail = todos;
		for(int i=0; i < todos.size(); i++){
			TabelaBean tabela = (TabelaBean) todos.get(i);
			for(int i1=0; i1 < tabela.getQtdDetails(); i1++){
				TabelaBean tabelaDetail = getTabela(todosDetail, tabela.getDetail(i1).getNomeTabelaDetail());
				tabelaDetail.setEhDetail(true);
			}
			
		}
		
	}
	
}
