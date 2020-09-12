package br.com.fiap.aoj.supportcenter.applications;

import br.com.fiap.aoj.supportcenter.data.TicketRepository;
import br.com.fiap.aoj.supportcenter.domain.TicketDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OpenTicketUseCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(OpenTicketUseCase.class);

	private final TicketRepository ticketRepository;

	public OpenTicketUseCase(final TicketRepository ticketRepository) {
		this.ticketRepository = ticketRepository;
	}

	public Optional<TicketDomain> open(final TicketDomain ticketDomain){
		try{
			LOGGER.debug("m=open(ticketDomain={})", ticketDomain);

			return Optional.of(ticketRepository.save(ticketDomain));
		}catch (Exception exception){
			LOGGER.error("ex(message={}, cause={})", exception.getMessage(), exception);
			return Optional.empty();
		}
	}
}