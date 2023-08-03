package com.cassio.userapi.dto;

import com.cassio.userapi.model.User;

public class CadastroResponse {
	
	private String mensagem;
    private User user;

    public CadastroResponse(){
       
    }
    
    public CadastroResponse(String mensagem, User user) {
        this.mensagem = mensagem;
        this.user = user;
    }

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
   
}
