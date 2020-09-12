package br.com.fiap.aoj.supportcenter.interfaces.dtos;

import javax.validation.constraints.NotNull;

public class ClientDto {

	@NotNull(message = "Campo obrigatório")
	private String clientId;

	@NotNull(message = "Campo obrigatório")
	private String name;

	public String getName() {
		return name;
	}

	public String getClientId() {
		return clientId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
}
