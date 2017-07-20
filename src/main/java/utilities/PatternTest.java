package utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest {
	
	public static void main(String[] args) {
		///d{4}-/d{2}-/d{2}-[a-zA-Z]{5}
		Pattern p = Pattern.compile("(\\+\\d{2} \\(\\d{1,3}\\) \\d{4,})|(\\+\\d{2} \\d{4,})");
		Matcher m = p.matcher("+34 () 123454545454");
		
		System.out.println(m.matches());
	}

}
