package petbook.converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

@Component
public class LocalDatetimeToStringConverter extends AbstractConverter<LocalDateTime, String> {
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
	
	@Override
	protected String convert(LocalDateTime source) {
		return source.format(formatter);
	}

}
