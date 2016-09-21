package helper;

import java.util.Calendar;

import util.Tabela;
import BD.CampoBean;
import BD.TabelaBean;
import fabrica.CamposConst;

public class HTMLHelper {
	
	//private static String abreTabela = "<TABLE>";
	//private static String fechaTabela = "</TABLE>";
	//private static String btSalvar = "<input type='submit' value='Salvar' class='btn btn-default'>";
	private static String slCadastro = "/Modular/cadastro";
	private static String fechaForm = "</FORM>";
	//private String quebraLinha = "<BR>";
	private static String abreTitulo = "<H3>";
	private static String fechaTitulo = "</H3>";
	private static String abreDivFormGroup = "<div class='form-group'>";
	private static String abreDivColuna = "<div class='col-sm-10'>";
	//private String abreDiv = "<div>";
	private static String fechaDiv = "</div>";
	
	
	private String getForm(String nomeServlet){
		if(!nomeServlet.equals(""))
			return "<form id='" + nomeServlet +"' >";//class='form-horizontal'
		else
			return "<form>";
	}
	
	/*public TabelaBean getTabela(String nmTabela) {
		
		for(int i=0; i < itens.size(); i++){
			TabelaBean tabela = (TabelaBean) itens.get(i);
			if(nmTabela.equalsIgnoreCase(tabela.getNome()))
				return tabela;
		}
		return null;
	}

	public void setItens(ArrayList<Object> itens) {
		this.itens = itens;
	}*/

	public String getCabecario(String titulo){
		StringBuilder sb = new StringBuilder();
		sb.append("<!DOCTYPE html!>");
		sb.append("<HTML lang='pt-br'>");
		//sb.append("<meta charset='UTF-8'/>");
		sb.append("<meta http-equiv='content-type' content='text/html; charset='UTF-8' pageEncoding='ISO-8859-1'/>");
		//sb.append("<meta http-equiv='Content-Type' content='text/html; charset=utf-8'/>");
		sb.append("<HEAD><TITLE>").append(titulo).append("</TITLE>");
		
		//sb.append("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css'>");
		//sb.append("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css'>");
		//sb.append("<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js'></script>");
		sb.append("<link rel='stylesheet' href='./css/bootstrap.min.css'>");
		sb.append("<link rel='stylesheet' href='./css/estilo-modular.css'>");
		sb.append("<link rel='stylesheet' href='./js/bootstrap-theme.min.css'>");
		sb.append("<script src='./js/bootstrap.min.js'></script>");
		//sb.append("<link rel='stylesheet' href='./js/jquery-1.11.3.js'>");
		sb.append("<script src='https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js'></script>");
		sb.append("<script src='./js/modular.js'></script>");
		//sb.append("<script src='http://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js'></script>");
		sb.append(" <script type='text/javascript' src='*.js' charset='utf-8'></script>");
		
		sb.append("</HEAD>");
		sb.append("<body>");//text='white' bgColor='0154205' link='white' alink='white' vlink='white'
		
		return sb.toString();
	}
	
	public String getRodape(){
		StringBuilder sb = new StringBuilder();
		sb.append("</BODY>");
		sb.append("</HTML>");
		return sb.toString();

	}
	
	public String getMenuHTML(String psTabelaAtiva){
		StringBuilder sb = new StringBuilder();
		
		sb.append("<ul class='nav nav-tabs'>");		
		for(int i=0; i < Tabela.getQtdTabelas(); i++){
			TabelaBean tabela = Tabela.getTabela(i);
			
			if(tabela.isDetail()||tabela.getCaption().equalsIgnoreCase(""))
				continue;
			 
			sb.append("<li role='presentation' ");
			sb.append("id='"+tabela.getNome()+"' ");
			if(tabela.getNome().equalsIgnoreCase(psTabelaAtiva))
				sb.append("class='active' ");
						
			sb.append("><a>");
			sb.append(tabela.getCaption());
			sb.append("</a></li>");
			
			//sb.append("<button type='button' class='btn btn-default' formaction='"+urlCadastro + tabela.getNome()+"'><a href='" + urlCadastro + tabela.getNome() + "' style='font-size:15'>" + tabela.getCaption() + "</a></button>");
			
			JSHelper jsH = new JSHelper();
			sb.append(jsH.getAbirConsulta(tabela, tabela.getNome()));
			
		}
		sb.append("</ul>");
		return sb.toString();
	}
	
	public String getTitulo(TabelaBean tabela, String prefixoTitulo){
		StringBuilder sb = new StringBuilder();
		sb.append(abreTitulo).append(prefixoTitulo+tabela.getCaption()).append(fechaTitulo);
		return sb.toString();
	}
	
	public String getBtSalvar(TabelaBean tabela, String jsDepoisSalvar){
		StringBuilder sb = new StringBuilder();
		JSHelper jsH = new JSHelper();
		
		sb.append("<input type='button' id='salvar' value='Salvar' class='btn btn-default'>");
		sb.append(jsH.getVincularEventoClick(true, "salvar", "cadastro", "fmensagem", jsH.getPreencherParametrosCadastro(tabela), jsH.getParametrosCadastro(tabela), "post", "$('#fcaddetail').height(1);", jsDepoisSalvar));
		return sb.toString();
	}
	
	public String getBtExcluir(TabelaBean tabela, int indice){
		StringBuilder sb = new StringBuilder();
		JSHelper jsH = new JSHelper();
		
		sb.append("<a id='excluir"+indice+"'>Excluir</a>");
		sb.append(jsH.getVincularEventoClick(true, "excluir"+indice, "cadastro", "fprincipal", ""/*jsH.getPreencherParametrosExclusao(tabela)*/, jsH.getParametrosExclusao(tabela), "post"));
		
		return sb.toString();
	}

	public String getBtAlterar(TabelaBean tabela, int indice, String divAlterarCad, boolean detail){
		StringBuilder sb = new StringBuilder();
		JSHelper jsH = new JSHelper();
		String sDetail = "N";
		if(detail)
			sDetail = "S";
		String eventoDepoisServlet ="";
		if(detail)
			eventoDepoisServlet = "$('#fcaddetail').height($(window).height());";
		sb.append("<a id='alterar"+indice+"'>Alterar</a>");
		sb.append(jsH.getVincularEventoClick(true, "alterar"+indice, "cadastro", divAlterarCad, "", jsH.getParametrosAlteracao(tabela)+", detail:'" + sDetail + "'", "get", eventoDepoisServlet));
		
		return sb.toString();
	}	
	
	public String getEventoAposSalvar(TabelaBean tabela, String psOperacao, boolean detail){
		StringBuilder sb = new StringBuilder();
		JSHelper jsH = new JSHelper();
		
		if(detail){	
			StringBuilder sbParametros = new StringBuilder();
			sbParametros.append("tabela:sTabela,");
			
			if(detail)
				sbParametros.append("detail:'S',");
			else
				sbParametros.append("detail:'N',");
			
			for(int i=0; i < tabela.getQtdCamposPK(); i++){
				if(tabela.getCampoPK(i).getEhFK())
					sbParametros.append(tabela.getCampoPK(i).getNome() + ":s"+tabela.getCampoPK(i).getNome()+",");
			}
			sbParametros.delete(sbParametros.length()-1, sbParametros.length());
			sb.append(jsH.getChamadaServlet("pesquisa", sbParametros.toString(), "consDetail"+tabela.getNome(), "get"));
			sb.append("$('#fcaddetail').html('');");			
		}else{
			if(psOperacao.equalsIgnoreCase(SQLHelper.getOpinserir())){
				for(int i=0; i < tabela.getQtdCampos(); i++){
					if((tabela.getCampo(i).getEhPK())&&(!tabela.getCampo(i).getEhFK())){
						sb.append(jsH.getChamadaServlet("getlastid", "tabela:sTabela", tabela.getCampo(i).getNome(), "get", "val", jsH.getChamadaServlet("cadastro", jsH.getParametrosAlteracaoSalvo(tabela)+", detail:'N'", "fprincipal", "get")));
					}
				}	
			}else
				sb.append(jsH.getChamadaServlet("consulta", "tabela:sTabela", "fprincipal", "get"));		
		
		}
		return sb.toString();
	}
	
	public String getCadastro(TabelaBean tabela, String psOperacao, boolean detail){
		// = Tabela.getTabela(psTabela);
		StringBuilder sb = new StringBuilder();
		if(psOperacao.equalsIgnoreCase(SQLHelper.getOpinserir()))
			tabela.limparValorCampos();
		
		sb.append(getBotaoAbrirConsulta(tabela));
		
		sb.append(getTitulo(tabela, "Cadastro de "));
		
		sb.append(getForm(slCadastro));
		
		sb.append(getBtSalvar(tabela, getEventoAposSalvar(tabela, psOperacao, detail)));
		sb.append("<br>");
		
		//"<input type='submit' value='Salvar' class='btn btn-default'>";
		sb.append(getInputTabela(tabela));
		sb.append("<input type='hidden' id='operacao' name='operacao' value='"+psOperacao+"'>");
		sb.append(getInputs(tabela, true));
		sb.append(fechaForm);
		
		sb.append(getDetails(tabela, psOperacao));
		
		return sb.toString();
	}
	
	public String getDetail(TabelaBean tabela, TabelaBean tabelaDetail, String divCadDetail, String psOperacao){
		StringBuilder sb = new StringBuilder();
		sb.append("<hr>");
		sb.append("<h4>");
		
		String sHidden = "";
		if(psOperacao.equalsIgnoreCase(SQLHelper.getOpinserir()))
			sHidden = "hidden";
		
		sb.append("<a id='Inserir"+tabelaDetail.getNome()+"' "+sHidden+">Cadastrar</a>");
		JSHelper jsH = new JSHelper();
		sb.append(jsH.getAbrirCadastro(tabelaDetail, SQLHelper.getOpinserir(), "Inserir" + tabelaDetail.getNome(), "fcaddetail", true));
		
		
		sb.append("&nbsp - " + tabelaDetail.getCaption() + " de " + tabela.getCaption());
		sb.append("</h4>");
		
		for(int j=0; j < tabelaDetail.getQtdCamposPK(); j++){
			if(tabela.getCampo(tabelaDetail.getCampoPK(j).getNome()) != null)
				tabelaDetail.getCampo(tabelaDetail.getCampoPK(j).getNome()).setValor(tabela.getCampo(tabelaDetail.getCampoPK(j).getNome()).getValor());
		}
		sb.append("<div id='consDetail"+tabelaDetail.getNome()+"'>");
		if(!psOperacao.equalsIgnoreCase(SQLHelper.getOpinserir()))		
			sb.append(getGridConsulta(tabelaDetail, true, false, divCadDetail, true));
		sb.append("</div>");
		return sb.toString();
	}
	
	public String getDetails(TabelaBean tabela, String psOperacao){
		StringBuilder sb = new StringBuilder();

		for(int i=0; i < tabela.getQtdDetails(); i++){
			TabelaBean tabelaDetail = Tabela.getTabela(tabela.getDetail(i).getNomeTabelaDetail());
			tabelaDetail.limparValorCampos();
			sb.append(getDetail(tabela, tabelaDetail, "fcaddetail", psOperacao));
		}		
		
		return sb.toString();
	}
	
	public String getInputTabela(TabelaBean tabela){
		return "<input type='hidden' id='tabela' name='tabela' value='" + tabela.getNome() + "'>";		
	}
	
	public String getInputs(TabelaBean tabela, boolean PKDisable){
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < tabela.getQtdCampos(); i++){
			sb.append(getLineInput(tabela.getCampo(i), PKDisable));
		}		
		return sb.toString();
	}
		
	public String getLineInput(CampoBean campo, boolean PKDisable){
		if(campo.getEhPK()&&campo.getEhFK())
			return "";
		if(campo.isCalculado())
			return "";
		StringBuilder sb = new StringBuilder();
		sb.append(abreDivFormGroup);		
		sb.append(getLabelCampo(campo));
		sb.append(getInputCampo(campo, PKDisable));		
	    sb.append(fechaDiv);
		
		return sb.toString();
	}
	
	public String getLabelCampo(CampoBean campo){
		return "<label for='"+campo.getNome()+"' class='col-sm-2 control-label'>"+campo.getCaption()+":</label>";
	}
	
	public String getBtFecharConsulta(){
		return "<button id='CancelarConsulta' onclick=\"mudarestado('fconsulta')\" class='btn btn-default'>Cancelar</button>";
	}
	
	public String getInputsFK(CampoBean campo, boolean PKDisable){
		StringBuilder sb = new StringBuilder();
		JSHelper jsH = new JSHelper();
		//sb.append("<div class='form-group'>");	    
		//sb.append("	<label for='Observacao' class='col-sm-2 control-label'>Cliente:</label>");
		sb.append("	<div class='col-sm-10'>");
		 sb.append("<input id='"+campo.getNome()+"' class='form-control cdLookup' placeholder='"+campo.getCaption()+"' type='text' value='"+campo.getValor()+"'");
		
		if((campo.getEhPK())&&(PKDisable))
			sb.append(" disabled ");
		sb.append(">");
		sb.append(jsH.getEventosCodigoFK(campo));
		
		
		sb.append("<input id='de"+campo.getNome()+"' class='form-control deLookup' placeholder='"+campo.getFK().getCaptionDescricao()+"' type='text' value='"+campo.getFK().getValorDescricao()+"' disabled>");
		sb.append("<input type='button' id='btPesquisar"+campo.getNome()+"' value='...' class='btn btn-default btLookup'>");
		sb.append("</div>");
		//sb.append("</div>");		
		
		sb.append(jsH.getEventoClickBotaoFK(campo));
		//sb.append("<div id='cons"+campo.getNome()+"' class='seletor'></div>");
		
		
		return sb.toString();
	}
	
	public String getInput(CampoBean campo, boolean PKDisable){
		StringBuilder sb = new StringBuilder();
		sb.append(abreDivColuna);
		
		sb.append("<input name='" + campo.getNome()+"' ");
		sb.append("id='" + campo.getNome()+"' ");
		sb.append("class='form-control' ");
		sb.append("placeholder='"+campo.getCaption()+"' ");
		
		if(campo.getTipo().equalsIgnoreCase(CamposConst.tipoDateTime)){
			Calendar data = Calendar.getInstance();
			if(!campo.getValor().equals("")){
				data = DataHelper.StringToCalendar("yyyy-MM-dd hh:mm:ss", campo.getValor());
				sb.append("value='" + DataHelper.CalendarToString("yyyy-MM-dd", data)+ "T" + DataHelper.CalendarToString("hh:mm:ss", data)+"' ");
			}
			sb.append("type='datetime-local' ");
			
		}else
		if(campo.getTipo().equalsIgnoreCase(CamposConst.tipoInt)){
			sb.append("type='number' ");
			sb.append("value='"+campo.getValor()+"' ");
		}else
		if(campo.getTipo().equalsIgnoreCase(CamposConst.tipoNumeric)){
			sb.append("type='number' step='any' ");
			sb.append("value='"+campo.getValor()+"' ");		
		}else{
			sb.append("type='text' ");
			sb.append("value='"+campo.getValor()+"' ");
		}
		
		if((campo.getEhPK())&&(PKDisable))
			sb.append("disabled ");
		
		sb.append(" >");		
		sb.append(fechaDiv);
		return sb.toString();
	}
	
	public String getInputCampo(CampoBean campo, boolean PKDisable){
		if(campo.getEhFK())
			return getInputsFK(campo, PKDisable);
		else
			return getInput(campo, PKDisable);
		//placeholder
		//required
		//size
		//value
		//disabled
		//checked
		//autocomplete                                                                                                                                                                   
	}
	
	public String getInputDate(CampoBean campo){
		StringBuilder sb = new StringBuilder();
		
		sb.append(abreDivColuna);

		Calendar data = Calendar.getInstance();
		data = DataHelper.StringToCalendar("dd/MM/yyyy hh:mm:ss", campo.getValor());

		sb.append("<input name='" + campo.getNome()+"' ");
		sb.append("id='" + campo.getNome()+"' ");
		sb.append("class='form-control' ");
		sb.append("value='" + DataHelper.CalendarToString("yyyy-MM-dd", data)+"T"+DataHelper.CalendarToString("hh:mm:ss", data)+"' ");
		sb.append("placeholder='"+campo.getCaption()+"' ");
		sb.append("type='datetime-local' >");
		
		sb.append(fechaDiv);
		
		return sb.toString();
	}
	
	public String getBotaoAbrirCadastro(TabelaBean tabela){
		StringBuilder sb = new StringBuilder();
		JSHelper jsH = new JSHelper();
		
		sb.append("<a id='AbrirCadastro"+tabela.getNome()+"'>Cadastrar "+tabela.getCaption()+"</a>");
		sb.append(jsH.getAbrirCadastro(tabela, SQLHelper.getOpinserir(), "AbrirCadastro"+tabela.getNome()));
		
		return sb.toString();
	}

	public String getBotaoAbrirConsulta(TabelaBean tabela){
		StringBuilder sb = new StringBuilder();
		JSHelper jsH = new JSHelper();
		
		sb.append("<a id='AbrirConsulta"+tabela.getNome()+"'>Consultar "+tabela.getCaption()+"</a>");
		sb.append(jsH.getAbirConsulta(tabela, "AbrirConsulta"+tabela.getNome()));
		
		return sb.toString();
	}
	
	public String getConsultaFK(TabelaBean tabela){
		StringBuilder sb = new StringBuilder();
		sb.append(getBtFecharConsulta());
		sb.append(getTitulo(tabela, "Selecionar "));	
		sb.append(getFiltro(tabela, "gridConsultaFK", "filtroConsultaFK"));
		sb.append(getInputTabela(tabela));
		
		sb.append("<div id='gridConsultaFK'>");
		sb.append(getGridConsulta(tabela, true, true, "Não habilitado", false));
		sb.append("</div>");
		sb.append("</table>");
		
		return sb.toString();
	}
	
	public String getConsulta(String psTabela){
		TabelaBean tabela = Tabela.getTabela(psTabela);
		tabela.limparValorCampos();
		//String urlCadastro = "/Modular/cadastro?tabela=";
		

		StringBuilder sb = new StringBuilder();		
		sb.append(getBotaoAbrirCadastro(tabela));
		sb.append(getTitulo(tabela, "Consulta de "));	
		sb.append(getFiltro(tabela, "gridConsulta", "filtroPrincipal"));
		sb.append(getInputTabela(tabela));
		
		sb.append("<div id='gridConsulta'>");
		sb.append(getGridConsulta(tabela, false, false, "fprincipal", false));
		sb.append("</div>");
		sb.append("</table>");
		return sb.toString();
	}
	
	public String getDescricaoFiltro(TabelaBean tabela){
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < tabela.getQtdCampos(); i++){
			if(!tabela.getCampo(i).getValor().equals("")){
				if(tabela.getCampo(i).getEhFK())
					sb.append("<b>" + tabela.getCampo(i).getCaption() + "</b>" + " = \""+tabela.getCampo(i).getValor()+ " - " + tabela.getCampo(i).getFK().getValorDescricao() + "\" ");
				else
					sb.append("<b>" + tabela.getCampo(i).getCaption() + "</b>" + " = \""+tabela.getCampo(i).getValor()+"\" ");
			}
		}
		return sb.toString();
	}
	
	public String getCampoGrid(CampoBean campo){
		if(campo.getTipo().equalsIgnoreCase(CamposConst.tipoDateTime)){
			if(!campo.getValor().isEmpty()){
				Calendar data = DataHelper.StringToCalendar("yyyy-MM-dd hh:mm:ss", campo.getValor());
				return DataHelper.CalendarToString("dd/MM/yyyy hh:mm:ss", data);
			}else
				return "";
		}else{
			if(campo.getEhFK())
				return campo.getValor() + " - " + campo.getFK().getValorDescricao();
			else
				return campo.getValor();
		}
	}
	
	public String getGridConsulta(TabelaBean tabela, boolean bSomenteLeitura, boolean bConsultaFK, String divAlterarCad, boolean detail){
		StringBuilder sb = new StringBuilder();

		sb.append("<table class='table table-hover'>");
		sb.append(getCabecarioGrid(tabela));

		SQLConsHelper qr = new SQLConsHelper();
		SQLHelper sqlH = new SQLHelper();
		JSHelper jsH = new JSHelper();
		int qtdRegistros = 0;
		try{
			tabela.limparTotal();
			qr.consultar(sqlH.getSelect(tabela));
			int indiceReg = 0;
			
			while(qr.next()){
				qr.carregarTabela(tabela);
				
				if(bConsultaFK)
					sb.append(jsH.getDuploClickRow(tabela));
				sb.append("<tr id='" + tabela.getIdRow() + "' ");
				if(bConsultaFK)
					sb.append(" class='linhaSelecao' ");
				sb.append(">");
				for(int i=0; i < tabela.getQtdCampos(); i++){
					if(getExibeGrid(tabela.getCampo(i))){
						sb.append("<td>");
						sb.append(getCampoGrid(tabela.getCampo(i)));
						sb.append("</td>");
					}
				}
				sb.append("<td>");
				sb.append(getBtAlterar(tabela, indiceReg, divAlterarCad, detail));
				sb.append("&nbsp");
				sb.append(getBtExcluir(tabela, indiceReg));
				indiceReg++;
				sb.append("</td>");
				sb.append("</tr>");
				qtdRegistros++;
			}
		}finally{
			qr.fechar();
		}
		sb.append("<tr>");
		for(int i=0; i < tabela.getQtdCampos(); i++){
			if(getExibeGrid(tabela.getCampo(i))){
				sb.append("<td>");
				if(tabela.getCampo(i).getEhNumeric())
					sb.append("<b>"+tabela.getCampo(i).getTotal()+"</b>");
				sb.append("</td>");	
			}
		}
		sb.append("</tr>");
		sb.append("<b>");
		if(qtdRegistros == 1)
			sb.append(qtdRegistros+" "+tabela.getCaption()+" encontrado(a).");
		else
		if(qtdRegistros > 1)
			sb.append(qtdRegistros+" "+tabela.getCaption()+" encontrados(as).");
		sb.append("</b>");
		return sb.toString();
	}
	
	public String getFiltro(TabelaBean tabela, String divGrid, String divFiltro){
		StringBuilder sb = new StringBuilder();
		//sb.append("<button id='filtro' onclick=\"mudarestado('filtroPrincipal')\" class='btn btn-default'>Filtro</button>");
		sb.append("<div id='divFiltro'>");
		//sb.append("<form class='form-horizontal'>");
		sb.append(getInputs(tabela, false));
		sb.append(getBtPesquisar(tabela, divGrid, divFiltro));
		sb.append("<button type='reset'  name='Limpar' class='btn btn-default'>Limpar</button>");
		//sb.append("</form>");
		sb.append("</div>");
		sb.append("<br>");
		return sb.toString();
	}
	
	public String getBtPesquisar(TabelaBean tabela, String divGrid, String divFiltro){
		StringBuilder sb = new StringBuilder();
		JSHelper jsH = new JSHelper();
		sb.append("<input type='button' id='pesquisar' value='Pesquisar' class='btn btn-default'>");
 		sb.append(jsH.getVincularEventoClick(true, "pesquisar", "pesquisa", divGrid, jsH.getPreencherParametrosCadastro(tabela), jsH.getParametrosCadastro(tabela)+",detail:'N'", "get", "mudarestado('"+divFiltro+"');"));
		return sb.toString();
	}
	
	public boolean getExibeGrid(CampoBean campo){
		return !((campo.getEhFK())&&(campo.getEhPK())); 
	}
	
	public String getCabecarioGrid(TabelaBean tabela){
		StringBuilder sb = new StringBuilder();
		sb.append("<tr>");
		for(int i=0; i < tabela.getQtdCampos(); i++){
			if(getExibeGrid(tabela.getCampo(i))){
				sb.append("<th>");
				sb.append(tabela.getCampo(i).getCaption());
				sb.append("</th>");
			}
		}
		sb.append("<th>Ações</th>");
		sb.append("</tr>");
		return sb.toString();
	}
	
}

/*
 tipos de input:
button
checkbox
color
date 
datetime 
datetime-local 
email 
file
hidden
image
month 
number 
password
radio
range 
reset
search
submit
tel
text
time 
url
week
*/