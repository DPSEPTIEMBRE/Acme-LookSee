package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.AdministratorKey;

@Component
@Transactional
public class AdministratorKeyToStringConverter implements Converter<AdministratorKey, String> {
	
	public String convert(AdministratorKey ar){
		String res;
		if(ar == null){
			res = null;
		}else{
			res = String.valueOf(ar.getId());
		}
		return res;
	}

}
