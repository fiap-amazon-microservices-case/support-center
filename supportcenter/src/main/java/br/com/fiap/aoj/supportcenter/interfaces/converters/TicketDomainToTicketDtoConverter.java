package br.com.fiap.aoj.supportcenter.interfaces.converters;

import br.com.fiap.aoj.supportcenter.domain.TicketDomain;
import br.com.fiap.aoj.supportcenter.interfaces.dtos.ClientDto;
import br.com.fiap.aoj.supportcenter.interfaces.dtos.TicketDto;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Lazy
@Component
public class TicketDomainToTicketDtoConverter implements Converter<TicketDomain, TicketDto> {

	private final ClientDomainToClientDtoConverter clientDomainToClientDtoConverter;

	public TicketDomainToTicketDtoConverter(final ClientDomainToClientDtoConverter clientDomainToClientDtoConverter) {
		this.clientDomainToClientDtoConverter = clientDomainToClientDtoConverter;
	}

	@Override
	public TicketDto convert(final TicketDomain source) {
		final TicketDto ticketDto = new TicketDto();
		ticketDto.setCreatedAt(source.createdAt());
		ticketDto.setMessage(source.getMessage());
		ticketDto.setTitle(source.getTitle());
		ticketDto.setTicketId(source.getTicketId().toString());
		ticketDto.setClient(buildClient(source));

		return ticketDto;
	}

	private ClientDto buildClient(final TicketDomain source) {
		return clientDomainToClientDtoConverter.convert(source.getClient());
	}
}