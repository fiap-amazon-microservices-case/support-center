package br.com.fiap.aoj.supportcenter.domain;

import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public class TicketDomain implements Serializable {

	private static final long serialVersionUID = 1649219665299117651L;

	@MongoId
	private UUID ticketId;

	private ClientDomain client;

	private LocalDateTime createdAt;

	private String title;

	private String message;

	private TicketDomain(final Builder builder){
		this.ticketId = builder.ticketId;
		this.client = builder.client;
		this.createdAt = builder.createdAt;
		this.title = builder.title;
		this.message = builder.message;
	}

	//Construtor padrão para serialização do mongo
	public TicketDomain(){}

	public UUID getTicketId() {
		return ticketId;
	}

	public ClientDomain getClient() {
		return client;
	}

	public LocalDateTime createdAt() {
		return createdAt;
	}

	public String getTitle() {
		return title;
	}

	public String getMessage() {
		return message;
	}

	public static final Client builder(){
		return new Builder();
	}

	public static final class Builder implements Client, Title, Message, Build{
		private UUID ticketId = UUID.randomUUID();
		private ClientDomain client;
		private LocalDateTime createdAt = LocalDateTime.now();
		private String title;
		private String message;

		@Override
		public Title client(final ClientDomain clientDomain) {
			this.client = clientDomain;
			return this;
		}

		@Override
		public Message title(final String title) {
			this.title = title;
			return this;
		}

		@Override
		public Build message(final String message) {
			this.message = message;
			return this;
		}

		@Override
		public Build ticketId(final String ticketId) {
			this.ticketId = UUID.fromString(ticketId);
			return this;
		}

		@Override
		public Build createdAt(final LocalDateTime createdAt) {
			this.createdAt = createdAt;
			return this;
		}

		@Override
		public TicketDomain builder() {
			return new TicketDomain(this);
		}
	}

	public interface Client{
		public Title client(final ClientDomain clientDomain);
	}

	public interface Title{
		public Message title(final String title);
	}

	public interface Message{
		public Build message(final String message);
	}

	public interface Build{
		public Build ticketId(final String ticketId);
		public Build createdAt(final LocalDateTime createdAt);
		public TicketDomain builder();
	}
}
