package br.com.fiap.aoj.supportcenter.interfaces.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.*;

public class TicketDto {

	@JsonProperty(access = READ_ONLY)
	private String ticketId;

	@Valid
	@NotNull(message = "Campo obrigatório")
	private ClientDto client;

	@JsonProperty(access = READ_ONLY)
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime createdAt;

	@NotNull(message = "Campo obrigatório")
	@Size(min = 2, max = 256, message = "Campo deve ter entre {min} e {max} caracteres.")
	private String title;

	@NotNull(message = "Campo obrigatório")
	@Size(min = 5, max = 1024, message = "Campo deve ter entre {min} e {max} caracteres.")
	private String message;

	public String getTitle() {
		return title;
	}

	public ClientDto getClient() {
		return client;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public String getTicketId() {
		return ticketId;
	}

	public String getMessage() {
		return message;
	}

	public void setClient(ClientDto client) {
		this.client = client;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
