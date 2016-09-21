package helper;

import fabrica.CamposConst;
import BD.CampoBean;
import BD.TabelaBean;

public class SQLHelper {
	
	private static String sepVir = ",";
	private static String InsertInto = "INSERT INTO ";
	private static String parametroSQL = "?";
	private static String Values = ")VALUES(";
	private static String parentesesFim = ")";
	private static String deleteFrom = "DELETE FROM ";
	private static String update = "UPDATE ";
	private static String set = " SET ";
	private static String where = " WHERE ";
		
	private static final String opInserir = "inserir"; 
	private static final String opAtualizar = "atualizar";
	private static final String opDeletar = "deletar";
	
	public static String getOpinserir() {
		return opInserir;
	}

	public static String getOpatualizar() {
		return opAtualizar;
	}

	public static String getOpdeletar() {
		return opDeletar;
	}	
	
	public String getSQLInsert(TabelaBean tabela){
		
		StringBuilder sb = new StringBuilder();
		sb.append(InsertInto).append(tabela.getNome()).append("(");
		
		for(int i=0; i < tabela.getQtdCampos(); i++){
			if(!tabela.getCampo(i).getValor().equalsIgnoreCase("")){
				sb.append(tabela.getCampo(i).getNome());
				sb.append(sepVir);				
			}
		}
		sb.delete(sb.length()-1, sb.length());
		sb.append(Values);
		
		for(int i=0; i < tabela.getQtdCampos(); i++){
			if(!tabela.getCampo(i).getValor().equalsIgnoreCase("")){
				sb.append(parametroSQL);
				sb.append(sepVir);
			}
		}
		sb.delete(sb.length()-1, sb.length());		
		sb.append(parentesesFim);
		
		return sb.toString();
	}
	
	public String getSQLDelete(TabelaBean tabela){
		StringBuilder sb = new StringBuilder();
		sb.append(deleteFrom + tabela.getNome() + where);

		for(int i=0; i < tabela.getQtdCamposPK(); i++){
			//if(tabela.getCampoPK(i).getTipoChave().equalsIgnoreCase(CamposConst.tipoChavePK))
			sb.append(tabela.getCampoPK(i).getNome() + "=" + parametroSQL);
		}
		
		return sb.toString();
	}
	
	public String getSQLUpdate(TabelaBean tabela){
		StringBuilder sb = new StringBuilder();
		sb.append(update + tabela.getNome() + set);
		
		for(int i=0; i < tabela.getQtdCampos(); i++){
			sb.append(tabela.getCampo(i).getNome() + "=" + parametroSQL);
			if(i != tabela.getQtdCampos() - 1)
				sb.append(sepVir);
		}
		
		boolean EhPrimeiro = true;
		for(int i=0; i < tabela.getQtdCamposPK(); i++){
			
			if(tabela.getCampoPK(i).getTipoChave().equalsIgnoreCase(CamposConst.tipoChavePK))
				if(EhPrimeiro){
					sb.append(where);
					EhPrimeiro = false;
				}else{
					sb.append(" and ");
				}
				sb.append(tabela.getCampoPK(i).getNome() + "=" + parametroSQL);
		}
		
		return sb.toString();
	}

	public String getSelect(TabelaBean tabela){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM " + tabela.getNome());
		boolean bPrimeiroFiltro = true;
		
		for(int i=0; i < tabela.getQtdCampos(); i++){
			if(!tabela.getCampo(i).getValor().equals("")){
				if(bPrimeiroFiltro){
					sb.append(" where ");
					bPrimeiroFiltro = false;
				}else{
					sb.append(" and ");
				}
				sb.append(getFiltroCampo(tabela.getCampo(i)));
			}
		}		
		
		return sb.toString();		
	}
	
	public String getSelectFK(CampoBean campo){
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT ");
		sb.append(campo.getFK().getColunaReferencia()+",");
		sb.append(campo.getFK().getColunaDescricao());
		sb.append(" FROM " + campo.getFK().getTabelaReferencia());
		
		if(!campo.getValor().equals("")){
			sb.append(" WHERE " + getFiltroCampo(campo));
		}else{
			if(!campo.getFK().getValorDescricao().equals("")){
				sb.append(" WHERE " + campo.getFK().getColunaDescricao() + " like '%" + campo.getValor().replace(" ", "%") + "%'");
			}
		}
				
		return sb.toString();		
	}
	
	public String getFiltroCampo(CampoBean campo){
		StringBuilder sb = new StringBuilder();
		
		if(campo.getTipo().equalsIgnoreCase(CamposConst.tipoDateTime))
			sb.append(campo.getNome() + "='" + campo.getValor() + "'");
		else
		if(campo.getTipo().equalsIgnoreCase(CamposConst.tipoString))
			sb.append(campo.getNome() + " like '%" + campo.getValor().replace(" ", "%") + "%'");
		else
		if(campo.getTipo().equalsIgnoreCase(CamposConst.tipoInt))
			sb.append(campo.getNome() + " = " + campo.getValor());			
		
		
		return sb.toString();
	}
	
	public String getURLExclusao(TabelaBean tabela){
		StringBuilder sb = new StringBuilder();
		sb.append("/Modular/cadastro?tabela=" + tabela.getNome()+"&");
		for(int i=0; i < tabela.getQtdCamposPK(); i++){
			sb.append(tabela.getCampoPK(i).getNome() + "=" + tabela.getCampo(tabela.getCampoPK(i).getNome()).getValor() + "&");
		}
		sb.append("operacao=" + opDeletar);
		return "<a href='"+sb.toString()+"'>Deletar</a>";
	}
	
}
