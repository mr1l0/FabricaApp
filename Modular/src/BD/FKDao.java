package BD;

import helper.SQLConsHelper;
import helper.SQLHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.Tabela;
import fabrica.Conexao;
import fabrica.GeralConst;

public class FKDao {
	private String getSQLFKs(String tabela, String campo){
		StringBuilder sb = new StringBuilder();
		sb.append("select REFERENCED_TABLE_NAME, REFERENCED_COLUMN_NAME");
		sb.append("  from information_schema.KEY_COLUMN_USAGE"); 
		sb.append(" where CONSTRAINT_SCHEMA='"+GeralConst.schemaConexao+"'"); 
		sb.append("   and CONSTRAINT_NAME <> 'PRIMARY'");
		sb.append("   and upper(TABLE_NAME) = '"+tabela.toUpperCase()+"'");
		sb.append("   and upper(COLUMN_NAME) = '"+campo.toUpperCase()+"'");
		return sb.toString();		
	}
	
	
	public FKBean getFK(String tabela, String campo) {
		Connection con = null;
		PreparedStatement pstm = null;
		try{
		    con = Conexao.conectar();
            pstm = con.prepareStatement(getSQLFKs(tabela, campo));
            
	  	    ResultSet rs = pstm.executeQuery();
	  	    if(rs.next()){
	  	    	FKBean FK = new FKBean();
		    	FK.setColuna(campo);
		    	FK.setTabela(tabela);
		    	FK.setColunaReferencia(rs.getString("REFERENCED_COLUMN_NAME"));
		    	FK.setTabelaReferencia(rs.getString("REFERENCED_TABLE_NAME"));
		    	TabelaBean tabelaBean = Tabela.getTabela(FK.getTabelaReferencia());
		    	for(int i=0; i < tabelaBean.getQtdCampos(); i++){
		    		if(tabelaBean.getCampo(i).getEhDescricao()){
		    			FK.setColunaDescricao(tabelaBean.getCampo(i).getNome());
		    			FK.setCaptionDescricao(tabelaBean.getCampo(i).getCaption());
		    		}
		    	}
		    	
	  	  	    return FK;
	  	    }else
	  	    	return null;
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
	
	public String getValorDescricaoFK(CampoBean campo){
		SQLConsHelper qr = new SQLConsHelper();
		SQLHelper sqlH = new SQLHelper();
		try{
			qr.consultar(sqlH.getSelectFK(campo));
			if(qr.next())
				return qr.getValor(campo.getFK().getColunaDescricao());
			else
				return "";
		}finally{
			qr.fechar();
		}
	}
		
}
