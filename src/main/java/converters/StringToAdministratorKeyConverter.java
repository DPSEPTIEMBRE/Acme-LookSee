package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.AdministratorKey;
import repositories.AdministratorKeyRepository;

@Component
@Transactional
public class StringToAdministratorKeyConverter implements Converter<String, AdministratorKey> {
	
	@Autowired
	AdministratorKeyRepository administratorKeyRepository;
	
	@Override
	public AdministratorKey convert(String text){
		AdministratorKey result;
		int id;
		try {
			if (StringUtils.isEmpty(text)) {
				result = null;
			} else {
				id = Integer.valueOf(text);
				result = administratorKeyRepository.findOne(id);
			}
		} catch (Exception oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}