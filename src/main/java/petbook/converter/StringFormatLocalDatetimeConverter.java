package petbook.converter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class StringFormatLocalDatetimeConverter extends AbstractConverter<String, LocalDateTime> {
	
	@Override
	protected LocalDateTime convert(String source) {
		return OffsetDateTime.parse(source).toLocalDateTime();
	}
}
