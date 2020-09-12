package br.com.fiap.aoj.supportcenter.interfaces.converters;

import br.com.fiap.aoj.supportcenter.domain.ClientDomain;
import br.com.fiap.aoj.supportcenter.domain.TicketDomain;
import br.com.fiap.aoj.supportcenter.interfaces.dtos.TicketDto;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Lazy
@Component
public class TicketDtoToTicketDomainConverter implements Converter<TicketDto, TicketDomain> {

	private final ClientDtoToClientDomainConverter clientDtoToClientDomainConverter;

	public TicketDtoToTicketDomainConverter(final ClientDtoToClientDomainConverter clientDtoToClientDomainConverter) {
		this.clientDtoToClientDomainConverter = clientDtoToClientDomainConverter;
	}

	@Override
	public TicketDomain convert(final TicketDto source) {
		final ClientDomain clientDomain = clientDtoToClientDomainConverter.convert(source.getClient());
		return TicketDomain //
				.builder() //
				.client(clientDomain) //
				.title(source.getTitle()) //
				.message(source.getMessage()) //
				.builder();
	}
}