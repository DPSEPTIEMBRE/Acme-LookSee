package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Note;

@Component
@Transactional
public class NoteToStringConverter implements Converter<Note,String>{
	
	public String convert(Note ar){
		String res;
		if(ar == null){
			res = null;
		}else{
			res = String.valueOf(ar.getId());
		}
		return res;
	}

}
