package BD;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

public class TabelaBean {
	private String nome;
	private String caption;
	
	private ArrayList<CampoBean> campos = null;
	private ArrayList<CampoBean> camposPK = null;
	
	private ArrayList<DetailBean> details = null;
	
	private String ultimoId;

	private boolean tabelaRecursiva = false;
	
	public String getUltimoId() {
		return ultimoId;
	}

	public void setUltimoId(String ultimoId) {
		this.ultimoId = ultimoId;
	}

	private boolean detail = false;
	
	public void limparTotal(){
		for(int i=0; i < getQtdCampos(); i++)
			getCampo(i).limparTotal();
	}
		
	public boolean isDetail() {
		return detail;
	}

	public void setEhDetail(boolean ehDetail) {
		this.detail = ehDetail;
	}

	public void limparValorCampos(){
		for(int i=0; i < campos.size(); i++){
			CampoBean campo = (CampoBean) campos.get(i);
			campo.setValor("");
		}
					
	}
	
	public String getIdRow(){
		String idLinha = getNome();
		for(int i=0; i < getQtdCamposPK(); i++){
			idLinha = idLinha + getCampo(getCampoPK(i).getNome()).getNome() + getCampo(getCampoPK(i).getNome()).getValor();					
		}
		return idLinha;
	}
		
	public void carregarTabela(HttpServletRequest request){
		for(int i=0; i < getQtdCampos(); i++){
			getCampo(i).setValor(request.getParameter(getCampo(i).getNome())); 
		}
	}
	
	public CampoBean getCampo(String nome){
		for(int i=0; i < campos.size(); i++){
			CampoBean campo = (CampoBean) campos.get(i);
			if(campo.getNome().equalsIgnoreCase(nome)){
				return campo; 
			}
		}
		return null;
	}
	
	public CampoBean getCampo(int ordem){
		return (CampoBean) campos.get(ordem);
	}
	
	public int getQtdCampos(){
		return campos.size();
	}
	
	public DetailBean getDetail(String nomeDetail){
		for(int i=0; i < details.size(); i++){
			DetailBean detail = (DetailBean) details.get(i);
			if(detail.getNomeTabelaDetail().equalsIgnoreCase(nomeDetail)){
				return detail; 
			}
		}
		return null;		
	}

	public DetailBean getDetail(int ordem){
		return (DetailBean) details.get(ordem);
	}
	
	public int getQtdDetails(){
		return details.size();
	}

	
	public CampoBean getCampoPK(int ordem){
		return (CampoBean) camposPK.get(ordem);
	}
	
	public CampoBean getCampoPK(String nome){
		for(int i=0; i < camposPK.size(); i++){
			CampoBean campo = (CampoBean) camposPK.get(i);
			if(campo.getNome().equalsIgnoreCase(nome)){
				return campo; 
			}
		}
		return null;
	}
	
	public int getQtdCamposPK(){
		return camposPK.size();
	}

	public ArrayList<CampoBean> getCampos() {
		return campos;
	}

	public void setCampos(ArrayList<CampoBean> campos) {
		this.campos = campos;
	}

	public ArrayList<CampoBean> getCamposPK() {
		return camposPK;
	}

	public void setCamposPK(ArrayList<CampoBean> camposPK) {
		this.camposPK = camposPK;
	}

	public ArrayList<DetailBean> getDetails() {
		return details;
	}

	public void setDetails(ArrayList<DetailBean> details) {
		this.details = details;
	}
	
	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
