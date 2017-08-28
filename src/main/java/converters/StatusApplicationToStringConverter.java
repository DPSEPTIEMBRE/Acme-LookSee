package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.StatusApplication;

@Component
@Transactional
public class StatusApplicationToStringConverter implements Converter<StatusApplication, String>{
	
	public String convert(StatusApplication ar){
		String res;
		if(ar == null){
			res = null;
		}else{
			res = ar.getValue();
		}
		return res;
	}

}
