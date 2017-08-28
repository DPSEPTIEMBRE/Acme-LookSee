package converters;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import security.LoginService;
import security.UserAccount;

@Component
@Transactional
public class StringToUserAccountConverter  implements Converter<String, UserAccount> {
	
	@Autowired
	LoginService loginService;

	public UserAccount convert(String text){
		UserAccount result;
		int id;
		try {
			if (StringUtils.isEmpty(text)) {
				result = null;
			} else {
				id = Integer.valueOf(text);
				result = loginService.findOne(id);
			}
		} catch (Exception oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
