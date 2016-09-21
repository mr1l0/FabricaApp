package helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BD.TabelaBean;
import fabrica.CamposConst;
import fabrica.Conexao;

public class SQLExecHelper {
	
	private String ultimoIdInserido = "";
	
	
	public String getUltimoIdInserido() {
		return ultimoIdInserido;
	}

	public boolean inserir(TabelaBean tabela){
		Connection con = null;
		PreparedStatement pstm = null;
		try{
			con = Conexao.conectar();
			pstm = con.prepareStatement(new SQLHelper().getSQLInsert(tabela));
			int count = 0;
			for(int i=0; i < tabela.getQtdCampos(); i++){
				if(!tabela.getCampo(i).getValor().equalsIgnoreCase("")){				
					pstm.setString(count + 1, tabela.getCampo(i).getValor());
					count++;
				}
			}
	  	  	pstm.executeUpdate();
	  	  	
	  	  	pstm.close();
	  	  	pstm = con.prepareStatement("select LAST_INSERT_ID();");
	  	  	ResultSet rs = pstm.executeQuery();
	  	  	if(rs.next())
	  	  		tabela.setUltimoId(rs.getString(1));
	  	  	return true;
		}catch(Exception ex){
			System.err.println(ex.getMessage());
			return false;
		}finally{
	      	try {
				if(pstm != null) pstm.close();
				if(con != null) con.close();
	      	} catch (SQLException e) {
				e.printStackTrace();
			}	      
	    }
	}
	
	public boolean atualizar(TabelaBean tabela){
		Connection con = null;
		PreparedStatement pstm = null;
		try{
		    con = Conexao.conectar();
		    pstm = con.prepareStatement(new SQLHelper().getSQLUpdate(tabela));
		    for(int i=0; i < tabela.getQtdCampos(); i++){
		    	pstm.setString(i + 1, tabela.getCampo(i).getValor());
		    }
		    for(int i=0; i < tabela.getQtdCamposPK(); i++){
		    	if(tabela.getCampoPK(i).getTipoChave().equalsIgnoreCase(CamposConst.tipoChavePK))
		    		pstm.setString(i + tabela.getQtdCampos() + 1, tabela.getCampo(tabela.getCampoPK(i).getNome()).getValor());
		    }		    
		    pstm.executeUpdate();
	  	  	return true;
		}catch(Exception ex){
			System.err.println(ex.getMessage());
			return false;
		}finally{
	      	try {
				if(pstm != null) pstm.close();
				if(con != null) con.close();
	      	} catch (SQLException e) {
				e.printStackTrace();
			}	      
	    }
	}
	
	public boolean deletar(TabelaBean tabela){
		Connection con = null;
		PreparedStatement pstm = null;
		try{
		    con = Conexao.conectar();
		    pstm = con.prepareStatement(new SQLHelper().getSQLDelete(tabela));
		    
			for(int i=0; i < tabela.getQtdCamposPK(); i++){
				pstm.setString(i+1, tabela.getCampo(tabela.getCampoPK(i).getNome()).getValor());
			}
        		    
		    pstm.executeUpdate();
	  	  	return true;
		}catch(Exception ex){
			System.err.println(ex.getMessage());
			return false;
		}finally{
	      	try {
				if(pstm != null) pstm.close();
				if(con != null) con.close();
	      	} catch (SQLException e) {
				e.printStackTrace();
			}	      
	    }		
	}
	
	
}
