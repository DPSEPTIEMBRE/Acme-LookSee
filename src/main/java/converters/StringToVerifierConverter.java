package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.VerifierRepository;
import domain.Verifier;

@Component
@Transactional
public class StringToVerifierConverter implements Converter<String, Verifier>{
	
	@Autowired
	VerifierRepository arRepository;
	
	@Override
	public Verifier convert(String text){
		Verifier result;
		int id;
		try {
			if (StringUtils.isEmpty(text)) {
				result = null;
			} else {
				id = Integer.valueOf(text);
				result = arRepository.findOne(id);
			}
		} catch (Exception oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}