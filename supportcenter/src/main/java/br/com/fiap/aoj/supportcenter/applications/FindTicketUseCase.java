package br.com.fiap.aoj.supportcenter.applications;

import br.com.fiap.aoj.supportcenter.data.TicketRepository;
import br.com.fiap.aoj.supportcenter.domain.TicketDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class FindTicketUseCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(OpenTicketUseCase.class);

	private final TicketRepository ticketRepository;

	public FindTicketUseCase(final TicketRepository ticketRepository) {
		this.ticketRepository = ticketRepository;
	}

	public Stream<TicketDomain> find(final String clientId){
		LOGGER.debug("m=find(clientId={})", clientId);

		return ticketRepository.findByClientClientId(clientId);
	}
}