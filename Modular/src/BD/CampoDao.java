package BD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fabrica.CamposConst;
import fabrica.Conexao;
import fabrica.GeralConst;

public class CampoDao {
	private ArrayList<CampoBean> getCampos(String condicao) {
		ArrayList<CampoBean> todos = new ArrayList<CampoBean>();
		Connection con = null;
		PreparedStatement pstm = null;
		try{
		    con = Conexao.conectar();
            pstm = con.prepareStatement("select * from information_schema.columns where table_schema='"+GeralConst.schemaConexao+"' and " + condicao);
	  	    CampoBean campo = null;
	  	    FKDao fkDao = new FKDao();
	  	    ResultSet rs = pstm.executeQuery();
		    while(rs.next()){
		    	campo = new CampoBean();
	    		campo.setNome(rs.getString(CamposConst.nome));
		    	
		    	String captionCampo = rs.getString(CamposConst.comentario); 
		    	if(captionCampo.contains("#_")){
		    		campo.setCaption(captionCampo.replace("#_", ""));
		    		campo.setEhDescricao(true);
		    	}else{
		    		campo.setCaption(captionCampo);
		    		campo.setEhDescricao(false);
		    	}
		    	
		    	campo.setTipo(rs.getString(CamposConst.tipo));
		    	campo.setTamanho(rs.getString(CamposConst.tamanho));
		    	
		    	//campo.setPrecisionNumeros(new Integer(rs.getString(CamposConst.precisionNumeros)));
		    	//campo.setScaleNumeros(new Integer(rs.getString(CamposConst.scaleNumeros)));
		    	campo.setTipoChave(rs.getString(CamposConst.tipoChave));
		    	campo.setValorDefault(rs.getString(CamposConst.valorDefault));
		    	campo.setOrdem(new Integer(rs.getString(CamposConst.ordem)));
		    	campo.setCalculado(!rs.getString(CamposConst.expressao).isEmpty());
		    	//campo.setPermiteNull(rs.getString(CamposConst.permiteNull)));
		    	campo.setFK(fkDao.getFK(rs.getString("TABLE_NAME"), campo.getNome()));

		    	todos.add(campo);
			  }
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
	
	public ArrayList<CampoBean> getTodosCampos(String tabela){
		return getCampos("table_name='"+tabela+"'");		
	}
	
	public ArrayList<CampoBean> getTodosCamposPK(String tabela){
		return getCampos(" table_name='"+tabela+"' AND "+CamposConst.tipoChave+"='"+CamposConst.tipoChavePK+"'");		
	}

}
