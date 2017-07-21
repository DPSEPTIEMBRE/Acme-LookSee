package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.ActivityReport;

@Component
@Transactional
public class ActivityReportToStringConverter implements Converter<ActivityReport,String>{
	
	public String convert(ActivityReport ar){
		String res;
		if(ar == null){
			res = null;
		}else{
			res = String.valueOf(ar.getId());
		}
		return res;
	}

}
