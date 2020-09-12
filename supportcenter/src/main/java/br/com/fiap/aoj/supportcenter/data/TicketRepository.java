package br.com.fiap.aoj.supportcenter.data;

import br.com.fiap.aoj.supportcenter.domain.TicketDomain;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;
import java.util.stream.Stream;

public interface TicketRepository extends MongoRepository<TicketDomain, UUID> {

	public Stream<TicketDomain> findByClientClientId(final String clientId);

}