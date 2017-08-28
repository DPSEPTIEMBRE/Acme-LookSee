package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.StatusNote;

@Component
@Transactional
public class StringToStatusNote implements Converter<String, StatusNote> {
	
	@Override
	public StatusNote convert(String text){
		StatusNote result = new StatusNote();
		result.setValue(text);
		
		
		return result;
	}
}
