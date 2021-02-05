package br.com.drogaria.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JSFUtil {
	// M�todo de mensagem de sucesso
	public static void adicionarMensagemSucesso(String mensagem) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, mensagem);
		
		//Adicionar no contexto da aplica��o
		FacesContext contexto = FacesContext.getCurrentInstance();
		
		//Jogar o FacesMesage no contexto
		contexto.addMessage(null, msg);
	}

	// M�todo de mensagem de erro
	public static void adicionarMensagemErro(String mensagem) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, mensagem);
		
		//Adicionar no contexto da aplica��o
		FacesContext contexto = FacesContext.getCurrentInstance();
		
		//Jogar o FacesMessage no contexto
		contexto.addMessage(null, msg);
	}

}
