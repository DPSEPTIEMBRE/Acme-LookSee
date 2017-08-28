package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.StatusNote;

@Component
@Transactional
public class StatusNoteToStringConverter implements Converter<StatusNote, String>{
	
	public String convert(StatusNote ar){
		String res;
		if(ar == null){
			res = null;
		}else{
			res = ar.getValue();
		}
		return res;
	}

}
