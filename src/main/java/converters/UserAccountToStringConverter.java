package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import security.UserAccount;

@Component
@Transactional
public class UserAccountToStringConverter implements Converter<UserAccount, String> {

	public String convert(UserAccount u){
		String res;
		if(u == null){
			res = null;
		}else{
			res = String.valueOf(u.getId());
		}
		return res;
	}
}
