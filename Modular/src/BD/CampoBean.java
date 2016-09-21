package BD;

import fabrica.CamposConst;

public class CampoBean {
	private String nome;
	private String tipo;
	private String tamanho;
	private String caption;
	
	private int precisionNumeros;
	private int scaleNumeros;
	private String tipoChave;
	private String valorDefault;
	private int ordem;
	private boolean permiteNull;
	private boolean calculado;
	
	public boolean isCalculado() {
		return calculado;
	}

	public void setCalculado(boolean calculado) {
		this.calculado = calculado;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	private FKBean FK = null;
	private boolean EhDescricao = false;
	
	private double valorTotal = 0.0;
	
	public void limparTotal(){
		valorTotal = 0.0;
	}
	
	public double getTotal(){
		return valorTotal;
	}
	
	public boolean getEhDescricao() {
		return EhDescricao;
	}

	public void setEhDescricao(boolean ehDescricao) {
		EhDescricao = ehDescricao;
	}

	public boolean getEhFK(){
		return FK!=null;
	}
	
	public boolean getEhPK(){
		return getTipoChave().equalsIgnoreCase(CamposConst.tipoChavePK);
	}
	
	public FKBean getFK() {
		return FK;
	}
	public void setFK(FKBean fK) {
		FK = fK;
	}

	private String valor;
	
	public String getValor() {
		if(valor==null)
			return "";
		else
			return valor;
	}
	
	public boolean getEhNumeric(){
		return getTipo().equalsIgnoreCase(CamposConst.tipoNumeric);
	}
	
	public void setValor(String valor) {
		if(valor != null){
			this.valor = valor;
			if(getEhNumeric()&&!valor.equalsIgnoreCase(""))
				valorTotal = valorTotal + new Double(valor);
			if(getEhFK()){
				if(!valor.equals("")){
					FKDao fkDao = new FKDao();
					getFK().setValorDescricao(fkDao.getValorDescricaoFK(this));
				}else
					getFK().setValorDescricao("");
			}
		}else
			setValor("");
	}
	public int getPrecisionNumeros() {
		return precisionNumeros;
	}
	public void setPrecisionNumeros(int precisionNumeros) {
		this.precisionNumeros = precisionNumeros;
	}
	public int getScaleNumeros() {
		return scaleNumeros;
	}
	public void setScaleNumeros(int scaleNumeros) {
		this.scaleNumeros = scaleNumeros;
	}
	public String getTipoChave() {
		return tipoChave;
	}
	public void setTipoChave(String tipoChave) {
		this.tipoChave = tipoChave;
	}
	public String getValorDefault() {
		return valorDefault;
	}
	public void setValorDefault(String valorDefault) {
		this.valorDefault = valorDefault;
	}
	public int getOrdem() {
		return ordem;
	}
	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}
	public boolean isPermiteNull() {
		return permiteNull;
	}
	public void setPermiteNull(boolean permiteNull) {
		this.permiteNull = permiteNull;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getTamanho() {
		return tamanho;
	}
	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}

}
