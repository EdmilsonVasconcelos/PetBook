package petbook.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import petbook.converter.LocalDatetimeToStringConverter;
import petbook.converter.StringFormatLocalDatetimeConverter;

@Configuration
public class ModelMapperConfig {
	
	@Autowired
	private StringFormatLocalDatetimeConverter stringFormatLocalDatetimeConverter;
	
	@Autowired
	private LocalDatetimeToStringConverter localDatetimeToStringConverter;
	
	@Bean
	public ModelMapper modelMapper() {
		final ModelMapper modelMapper = new ModelMapper();
		modelMapper.addConverter(stringFormatLocalDatetimeConverter);
		modelMapper.addConverter(localDatetimeToStringConverter);
		return modelMapper;
	}

}
