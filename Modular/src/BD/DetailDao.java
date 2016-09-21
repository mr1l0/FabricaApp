package BD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fabrica.Conexao;
import fabrica.GeralConst;

public class DetailDao {
	
	public String getSQLDetail(String sTabela){
		StringBuilder sb = new StringBuilder();
		sb.append("select ku.TABLE_NAME,"); 
		sb.append("       ku.REFERENCED_TABLE_NAME ");
		sb.append("  from information_schema.KEY_COLUMN_USAGE ku ");
		sb.append(" where ku.table_schema='"+GeralConst.schemaConexao+"' ");
		sb.append("   and referenced_table_name is not null");
		sb.append("   and ku.referenced_table_name = '"+sTabela+"'");
		sb.append("   and 2 = (select count(1) ");
		sb.append("              from information_schema.KEY_COLUMN_USAGE ku1 ");
		sb.append("        where ku1.column_name = ku.COLUMN_NAME ");
		sb.append("          and ku1.TABLE_NAME = ku.TABLE_NAME );");
		return sb.toString();
	}
	
	public ArrayList<DetailBean> getDetailsTabela(String sTabela) {
		 ArrayList<DetailBean> todos = new ArrayList<DetailBean>();
		 Connection con = null;
		 PreparedStatement pstm = null;
		 try{
		     con = Conexao.conectar();
             pstm = con.prepareStatement(getSQLDetail(sTabela));             
	  	     DetailBean detail = null;	  	     
		     ResultSet rs = pstm.executeQuery();
		     while(rs.next()){
		    	detail = new DetailBean();
		    	detail.setNomeTabelaMaster(rs.getString("REFERENCED_TABLE_NAME"));
		    	detail.setNomeTabelaDetail(rs.getString("TABLE_NAME"));
				todos.add(detail);
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
}
