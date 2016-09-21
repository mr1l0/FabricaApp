package helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fabrica.Conexao;
import BD.TabelaBean;

public class SQLConsHelper {
	private ResultSet rs = null;
	private Connection con = null;
	private PreparedStatement pstm = null;
		
	/*public SQLConsDataSet getDataSet(){
		return ds;
	}*/
	
	public String getValor(String psCampo){
		try{
			return rs.getString(psCampo);
		}catch(Exception ex){
			System.err.println(ex.getMessage());
			return "";
		}
	}

	public String getValor(int pnIndex){
		try{
			return rs.getString(pnIndex);
		}catch(Exception ex){
			System.err.println(ex.getMessage());
			return "";
		}
	}
	
	public void first(){
		try{
			rs.first();
		}catch(Exception ex){
			System.err.println(ex.getMessage());
		}
	}
	
	public boolean next(){
		try{
			return rs.next();
		}catch(Exception ex){
			System.err.println(ex.getMessage());
			return false;
		}
	}
	
	public void carregarTabela(TabelaBean tabela){
		try{
			for(int i=0; i < tabela.getQtdCampos(); i++){
				tabela.getCampo(i).setValor(rs.getString(tabela.getCampo(i).getNome()));
			}
		}catch(Exception ex){
			System.err.println(ex.getMessage());
		}
	}
	
	public boolean consultar(String psSelect){
		try{
		  con = Conexao.conectar();
		  pstm = con.prepareStatement(psSelect);
		  
		  rs = pstm.executeQuery();
	  	  return true;
		}catch(Exception ex){
			System.err.println(ex.getMessage());
			return false;
		}
	}
	
	public void fechar(){
		try{
			if(pstm != null) pstm.close();
			if(con != null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
}
