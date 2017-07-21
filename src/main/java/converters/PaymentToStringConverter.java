package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Payment;

@Component
@Transactional
public class PaymentToStringConverter implements Converter<Payment,String>{
	
	public String convert(Payment ar){
		String res;
		if(ar == null){
			res = null;
		}else{
			res = String.valueOf(ar.getId());
		}
		return res;
	}

}
