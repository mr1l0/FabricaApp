package BD;

public class FKBean {
	private String Coluna = "";
	private String Tabela = "";
	private String ColunaReferencia = "";
	private String TabelaReferencia = "";
	private String ColunaDescricao = "";
	private String valorDescricao = "";
	private String CaptionDescricao = "";
	
	public String getCaptionDescricao() {
		return CaptionDescricao;
	}
	public void setCaptionDescricao(String captionDescricao) {
		CaptionDescricao = captionDescricao;
	}
	public String getColunaDescricao() {
		return ColunaDescricao;
	}
	public void setColunaDescricao(String campoDescricao) {
		ColunaDescricao = campoDescricao;
	}
	public String getValorDescricao() {
		return valorDescricao;
	}
	public void setValorDescricao(String valorDescricao) {
		this.valorDescricao = valorDescricao;
	}
	public String getColuna() {
		return Coluna;
	}
	public void setColuna(String coluna) {
		Coluna = coluna;
	}
	public String getTabela() {
		return Tabela;
	}
	public void setTabela(String tabela) {
		Tabela = tabela;
	}
	public String getColunaReferencia() {
		return ColunaReferencia;
	}
	public void setColunaReferencia(String colunaReferencia) {
		ColunaReferencia = colunaReferencia;
	}
	public String getTabelaReferencia() {
		return TabelaReferencia;
	}
	public void setTabelaReferencia(String tabelaReferencia) {
		TabelaReferencia = tabelaReferencia;
	}

}
