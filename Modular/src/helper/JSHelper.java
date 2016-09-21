package helper;

import BD.CampoBean;
import BD.TabelaBean;

public class JSHelper {
	
	private String sScript = "<script type='text/javascript' charset='utf-8'>"; 
	
	public String getAbirConsulta(TabelaBean tabela, String psID){
		StringBuilder sb = new StringBuilder();
		sb.append(sScript);
		sb.append("$(document).ready(function() {");
		sb.append("  $('#"+psID+"').click(function(event){");
		sb.append("    var stabela='"+tabela.getNome()+"';");
		sb.append(getChamadaServlet("consulta", "tabela:stabela", "fprincipal", "get"));

		//sb.append(" document.getElementById('"+psID+"').className='active';");
		
		sb.append("  });");
		sb.append("});");
		sb.append("</script>");
		return sb.toString();
	}
	
	public String getEventosCodigoFK(CampoBean campo){
		StringBuilder sb = new StringBuilder();
		sb.append(sScript);
		sb.append("$(document).ready(function() {");
		sb.append("  $('#"+campo.getNome()+"').blur(function(event){");
		sb.append("    var stabela='"+campo.getFK().getTabela()+"';");
		sb.append("    var scampoCodigo='"+campo.getNome()+"';");
		sb.append("	   var svalor=$('#"+campo.getNome()+"').val();");
		sb.append(getChamadaServlet("getDescricaoFKServlet", "tabela:stabela, campoCodigo:scampoCodigo, valor:svalor", "de"+campo.getNome(), "get", "val"));
		sb.append("  });");
		sb.append("});");
		sb.append("</script>");
		return sb.toString();
	}
	
	public String getEventoClickBotaoFK(CampoBean campo){
		StringBuilder sb = new StringBuilder();
		sb.append(sScript);
		
		sb.append("  $('#btPesquisar"+campo.getNome()+"').click(function(event){");
		sb.append("    var stabela='"+campo.getFK().getTabelaReferencia()+"';");
		//sb.append("    var scampoCodigo='"+campo.getNome()+"';");
		//sb.append("	   var svalor=$('#"+campo.getNome()+"').val();");
		sb.append(getChamadaServlet("consultafk", "tabela:stabela", "fconsulta", "get", "html"));
		sb.append("fconsulta.style.display = 'block';");
		sb.append("  });");
		//sb.append("});");
		
		sb.append("</script>");
		return sb.toString();
	}
	
	public String getDuploClickRow(TabelaBean tabela){
		StringBuilder sb = new StringBuilder();
		sb.append(sScript);
		
		sb.append("  $('#"+tabela.getIdRow()+"').click(function(event){");
		sb.append("$('#fconsulta').html('');");
		
		for(int i=0; i < tabela.getQtdCamposPK(); i++){
			sb.append("    $('#" + tabela.getCampo(tabela.getCampoPK(i).getNome()).getNome() + "').val('"+tabela.getCampo(tabela.getCampoPK(i).getNome()).getValor()+"');");
			sb.append("    $('#"+tabela.getCampo(tabela.getCampoPK(i).getNome()).getNome()+"').blur();");
		}
		sb.append("fconsulta.style.display = 'block';");
		sb.append("  });");
		
		sb.append("</script>");
		return sb.toString();		
	}
	
	public String getChamadaServlet(String psServlet, String psParametros, String psIdDivResposta, String getOrPost){
		return getChamadaServlet(psServlet, psParametros, psIdDivResposta, getOrPost, "html");
	}

	public String getChamadaServlet(String psServlet, String psParametros, String psIdDivResposta, String getOrPost, String operacaoResposta){
		return getChamadaServlet(psServlet, psParametros, psIdDivResposta, getOrPost, operacaoResposta, "");
	}
	
	public String getChamadaServlet(String psServlet, String psParametros, String psIdDivResposta, String getOrPost, String operacaoResposta, String onDepoisResposta){
		StringBuilder sb = new StringBuilder();
		sb.append("$."+ getOrPost +"('"+psServlet+"', {"+psParametros+"}, function(sResposta){");
		sb.append("   $('#"+psIdDivResposta+"')." + operacaoResposta + "(sResposta);");
		sb.append(onDepoisResposta);
		//sb.append("alert(sResposta);");
		sb.append("});");
		return sb.toString();		
	}
	
	public String getVincularEventoClick(boolean bAddScript, String psID, String psServlet, String psIDDivResposta, String psPreencherParametros, String psParametros, String getOrPost){
		return getVincularEventoClick(bAddScript, psID, psServlet, psIDDivResposta, psPreencherParametros, psParametros, getOrPost, "");
	}

	public String getVincularEventoClick(boolean bAddScript, String psID, String psServlet, String psIDDivResposta, String psPreencherParametros, String psParametros, String getOrPost, String eventoDepoisServlet){
		return getVincularEventoClick(bAddScript, psID, psServlet, psIDDivResposta, psPreencherParametros, psParametros, getOrPost, eventoDepoisServlet, "");
	}
	
	public String getVincularEventoClick(boolean bAddScript, String psID, String psServlet, String psIDDivResposta, String psPreencherParametros, String psParametros, String getOrPost, String eventoDepoisServlet, String onDepoisResposta){
		StringBuilder sb = new StringBuilder();		
		if(bAddScript)
			sb.append(sScript);
		sb.append("$(document).ready(function() {");
		sb.append("  $('#"+psID+"').click(function(event){");		
		sb.append(psPreencherParametros);
		sb.append(getChamadaServlet(psServlet, psParametros, psIDDivResposta, getOrPost, "html", onDepoisResposta));
		sb.append(eventoDepoisServlet);
		sb.append("  });");
		sb.append("});");
		if(bAddScript)
			sb.append("</script>");
		return sb.toString();
	}
	
	public String getVincularEventoClick(String psID, String metodo){
		StringBuilder sb = new StringBuilder();		
		sb.append(sScript);
		sb.append("$(document).ready(function() {");
		sb.append("  $('#"+psID+"').click(function(event){");		
		sb.append(metodo);
		sb.append("  });");
		sb.append("});");
		sb.append("</script>");
		return sb.toString();
	}
	
	public String getParametrosExclusao(TabelaBean tabela){
		StringBuilder sb = new StringBuilder();
		sb.append("tabela:'"+tabela.getNome()+"',");
		
		for(int i=0; i < tabela.getQtdCamposPK(); i++){
			sb.append(tabela.getCampoPK(i).getNome()+":'"+tabela.getCampo(tabela.getCampoPK(i).getNome()).getValor()+"',"); 
		}
		sb.append("operacao:'"+SQLHelper.getOpdeletar()+"'");				
		return sb.toString();
	}
	
	public String getParametrosAlteracao(TabelaBean tabela){
		StringBuilder sb = new StringBuilder();
		sb.append("tabela:'"+tabela.getNome()+"',");
		
		for(int i=0; i < tabela.getQtdCampos(); i++){
			sb.append(tabela.getCampo(i).getNome()+":'"+tabela.getCampo(i).getValor()+"',"); 
		}
		sb.append("operacao:'"+SQLHelper.getOpatualizar()+"'");				
		return sb.toString();
	}	
	
	public String getParametrosAlteracaoSalvo(TabelaBean tabela){
		StringBuilder sb = new StringBuilder();
		sb.append("tabela:'"+tabela.getNome()+"',");
		for(int i=0; i < tabela.getQtdCampos(); i++){
			sb.append(tabela.getCampo(i).getNome()+":$('#"+tabela.getCampo(i).getNome()+"').val(),"); 
		}
		sb.append("operacao:'"+SQLHelper.getOpatualizar()+"'");				
		return sb.toString();		
	}

	/*public String getPreencherParametrosExclusao(TabelaBean tabela){
		StringBuilder sb = new StringBuilder();

		sb.append("var sOperacao='"+SQLHelper.getOpdeletar()+"';");
		sb.append("var sTabela=$('#tabela').val();");		
		for(int i=0; i < tabela.getQtdCamposPK(); i++){
			sb.append("var s"+tabela.getCampoPK(i).getNome() +"=$('#"+tabela.getCampo(tabela.getCampoPK(i).getNome()).getValor() + "').val();");
		}
		return sb.toString();
	}*/
	
	public String getPreencherParametrosCadastro(TabelaBean tabela){
		StringBuilder sb = new StringBuilder();
		
		sb.append("var sOperacao=$('#operacao').val();");
		sb.append("var sTabela=$('#tabela').val();");		
		for(int i=0; i < tabela.getQtdCampos(); i++){
			sb.append("var s"+tabela.getCampo(i).getNome()+"=$('#"+tabela.getCampo(i).getNome() + "').val();"); 
		}		
		
		return sb.toString();
	}	
	
	public String getParametrosCadastro(TabelaBean tabela){
		StringBuilder sb = new StringBuilder();
		
		sb.append("tabela:sTabela,");
		
		for(int i=0; i < tabela.getQtdCampos(); i++){
			sb.append(tabela.getCampo(i).getNome()+":s"+tabela.getCampo(i).getNome()+","); 
		}
		sb.append("operacao:sOperacao");		
		
		return sb.toString();
	}

	public String getAbrirCadastro(TabelaBean tabela, String psOperacao, String psID){
		return getAbrirCadastro(tabela, psOperacao, psID, "fprincipal"); 
	}
	 
	public String getAbrirCadastro(TabelaBean tabela, String psOperacao, String psID, String divCadastro){
		return getAbrirCadastro(tabela, psOperacao, psID, divCadastro, false);
	}
	
	public String getAbrirCadastro(TabelaBean tabela, String psOperacao, String psID, String divCadastro, boolean Detail){
		StringBuilder sb = new StringBuilder();
		sb.append(sScript);
		sb.append("$(document).ready(function() {");
		sb.append("  $('#"+psID+"').click(function(event){");
		sb.append("    var stabela='"+tabela.getNome()+"';");
		
		if(Detail){
			sb.append("  var sdetail='S';");
			sb.append("$('#fcaddetail').height($(window).height());");
		}else
			sb.append("  var sdetail='N';");
		 
		sb.append(getChamadaServlet("cadastro", "tabela:stabela, operacao:'"+ psOperacao +"', detail:sdetail", divCadastro, "get"));
		sb.append("  });");
		sb.append("});");
		sb.append("</script>");
		
		return sb.toString();
	}
	
}
