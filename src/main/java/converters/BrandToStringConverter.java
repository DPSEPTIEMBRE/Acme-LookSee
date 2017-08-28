package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Brand;

@Component
@Transactional
public class BrandToStringConverter implements Converter<Brand, String>{
	
	public String convert(Brand ar){
		String res;
		if(ar == null){
			res = null;
		} else {
			res = ar.getValue();
		}
		return res;
	}

}