package br.com.fiap.aoj.supportcenter.domain;

import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.util.UUID;

public class ClientDomain implements Serializable {

	private static final long serialVersionUID = -5566934121978871840L;

	@MongoId
	private String clientId;

	private String name;

	private ClientDomain(final Builder builder){
		this.clientId = builder.clientId;
		this.name = builder.name;
	}

	//Construtor padrão para serialização do mongo
	public ClientDomain() { }

	public String getClientId() {
		return clientId;
	}

	public String getName() {
		return name;
	}

	public static final Name build(){
		return new Builder();
	}

	public static final class Builder implements Name, Build{

		private String clientId = UUID.randomUUID().toString();
		private String name;

		@Override
		public Build name(final String name) {
			this.name = name;
			return this;
		}

		@Override
		public Build clientId(final String clientId) {
			this.clientId = clientId;
			return this;
		}

		@Override
		public ClientDomain builder() {
			return new ClientDomain(this);
		}
	}

	public interface Name{
		public Build name(final String name);
	}

	public interface Build{
		public Build clientId(final String clientId);
		public ClientDomain builder();
	}
}