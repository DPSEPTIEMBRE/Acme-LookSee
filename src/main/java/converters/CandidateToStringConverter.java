package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Candidate;

@Component
@Transactional
public class CandidateToStringConverter implements Converter<Candidate,String>{
	
	public String convert(Candidate ar){
		String res;
		if(ar == null){
			res = null;
		}else{
			res = String.valueOf(ar.getId());
		}
		return res;
	}

}