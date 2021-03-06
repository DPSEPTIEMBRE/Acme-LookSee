package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.StatusApplication;

@Component
@Transactional
public class StringToStatusApplication implements Converter<String, StatusApplication> {
	
	@Override
	public StatusApplication convert(String text){
		StatusApplication result = new StatusApplication();
		result.setValue(text);
		
		return result;
	}
}
