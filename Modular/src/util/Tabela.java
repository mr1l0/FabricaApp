package util;

import java.util.ArrayList;

import BD.TabelaBean;
import BD.TabelaDao;

public final class Tabela {
	private static final Tabela INSTANCE = new Tabela();
	public static ArrayList<Object> list = new ArrayList<Object>();
	private static ArrayList<Object> tabelas = new ArrayList<Object>();
	
	public static Tabela getInstance(){
		return INSTANCE;
	}

	private Tabela(){
		TabelaDao tabelaDao = new TabelaDao();
		tabelas = tabelaDao.getTodas(); 
	}
	
	public static void preencherTabelas(){
		if(tabelas.size() == 0){
			atualizarTabelas();
		}
	}
	
	public static void atualizarTabelas() {
		TabelaDao tabelaDao = new TabelaDao();
		tabelas = tabelaDao.getTodas();		
	}
	
	public static int getQtdTabelas(){
		preencherTabelas();
		return tabelas.size();
	}

	public static TabelaBean getTabela(String nomeTabela){
		preencherTabelas();
		for(int i=0; i < tabelas.size(); i++){
			TabelaBean tabela = (TabelaBean) tabelas.get(i);
			if(tabela.getNome().equalsIgnoreCase(nomeTabela)){
				return tabela; 
			}
		}
		return null;		
	}
	public static TabelaBean getTabela(int ordem){
		preencherTabelas();
		return (TabelaBean) tabelas.get(ordem);
	}
	
}
