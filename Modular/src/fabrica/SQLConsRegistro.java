package fabrica;

import java.util.ArrayList;

public class SQLConsRegistro {
	private ArrayList<SQLConsCampo> registros = null;
		
	private SQLConsCampo getCampo(int pnIndex){
		return registros.get(pnIndex);
	}
	
	public int getQtdCampos(){
		return registros.size();
	}
	
	public void addCampo(String psNmCampo, String psValor){
		SQLConsCampo campo = new SQLConsCampo();
		campo.setIndex(registros.size() + 1);
		campo.setNome(psNmCampo);
		campo.setValor(psValor);
		registros.add(campo);
	}
	
	public String getValorCampo(int pnIndex){
		return getCampo(pnIndex).getValor();
		
	}
	
	public String getValorCampo(String psNmCampo){
		for(int i=0; i < registros.size(); i++){
			if(getCampo(i).getNome().equalsIgnoreCase(psNmCampo))
				return getCampo(i).getValor();
		}
		return "";
	}
	
}
