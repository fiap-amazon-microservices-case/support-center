package br.com.fiap.aoj.supportcenter.interfaces;

import br.com.fiap.aoj.supportcenter.applications.FindTicketUseCase;
import br.com.fiap.aoj.supportcenter.applications.OpenTicketUseCase;
import br.com.fiap.aoj.supportcenter.domain.TicketDomain;
import br.com.fiap.aoj.supportcenter.interfaces.converters.TicketDomainToTicketDtoConverter;
import br.com.fiap.aoj.supportcenter.interfaces.converters.TicketDtoToTicketDomainConverter;
import br.com.fiap.aoj.supportcenter.interfaces.dtos.TicketDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(path = "${api.version.v1:/v1}/tickets")
class TicketsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TicketsController.class);

	private final OpenTicketUseCase openTicketUseCase;
	private final FindTicketUseCase findTicketUseCase;
	private final TicketDtoToTicketDomainConverter ticketDtoToTicketDomainConverter;
	private final TicketDomainToTicketDtoConverter ticketDomainToTicketDtoConverter;

	TicketsController(final OpenTicketUseCase openTicketUseCase,
			final FindTicketUseCase findTicketUseCase,
			final TicketDtoToTicketDomainConverter ticketDtoToTicketDomainConverter,
			final TicketDomainToTicketDtoConverter ticketDomainToTicketDtoConverter) {
		this.openTicketUseCase = openTicketUseCase;
		this.findTicketUseCase = findTicketUseCase;
		this.ticketDtoToTicketDomainConverter = ticketDtoToTicketDomainConverter;
		this.ticketDomainToTicketDtoConverter = ticketDomainToTicketDtoConverter;
	}

	@PostMapping
	@ResponseStatus(CREATED)
	public Mono<TicketDto> open(@RequestBody @Valid final TicketDto ticketDto){
		LOGGER.debug("m=open(ticketDto={})", ticketDto);

		final TicketDomain ticketDomain = ticketDtoToTicketDomainConverter.convert(ticketDto);
		return openTicketUseCase.open(ticketDomain) //
		.map(ticketDomainToTicketDtoConverter::convert) //
		.map(Mono::just) //
		.orElseGet(() -> Mono.error(new IllegalArgumentException()));
	}

	@GetMapping
	@ResponseStatus(OK)
	public Flux<TicketDto> find(@RequestParam("clientId") String clientId){
		LOGGER.debug("m=find(clientId={})", clientId);

		return Flux //
				.fromStream(findTicketUseCase.find(clientId) //
				.map(ticketDomainToTicketDtoConverter::convert));
	}
}