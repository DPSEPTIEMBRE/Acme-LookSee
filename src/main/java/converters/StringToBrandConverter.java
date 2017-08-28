package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Brand;

@Component
@Transactional
public class StringToBrandConverter implements Converter<String, Brand> {
	
	@Override
	public Brand convert(String text) {
		if(text == null || text.trim().isEmpty()) {
			throw new IllegalArgumentException();
		}
		
		try {
			Brand res = new Brand();
			res.setValue(text);
			
			return res;
		} catch(Exception e) {
			throw new IllegalArgumentException(e);
		}
	}
}