package fabrica;

import java.util.ArrayList;

public class SQLConsDataSet {
	private ArrayList<SQLConsRegistro> registros;
	
	private SQLConsRegistro getRegistro(int pnIndex){
		return registros.get(pnIndex);
	}
	
	public int getRecordCount(){
		return registros.size();
	}
	
	public int getQtdCampos(int pnIndexRegistro){
		return getRegistro(pnIndexRegistro).getQtdCampos();
	}
	
	public String getValorCampo(int pnIndexRegistro, int pnIndexCampo){
		return getRegistro(pnIndexRegistro).getValorCampo(pnIndexCampo);
	}
	
	public void addCampo(int pnRegistro, String nmCampo, String valorCampo){
		getRegistro(pnRegistro).addCampo(nmCampo, valorCampo);
	}
	
	public void addCampo(String nmCampo, String valorCampo){
		addCampo(getRecordCount(), nmCampo, valorCampo);
	}
	
}
