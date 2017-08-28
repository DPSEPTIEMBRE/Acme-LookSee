package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import security.Authority;

@Component
@Transactional
public class StringToAuthorityConverter implements Converter<String, Authority> {

	public Authority convert(String str) {
		Authority res = new Authority();
		res.setAuthority(str);
		
		return res;
	}

}
