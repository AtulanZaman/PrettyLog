package bk.tailfile.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseLog {

	public static ParserObject parser (String line) {
		ParserObject p = new ParserObject();
		Pattern pattern = Pattern.compile("; (\\d{4}-\\d{2}-\\d{2}) (\\d{2}:\\d{2}:\\d{2},\\d{3}) ([^ ]*) \\[(.*)\\] (.*)$");
		Matcher matcher = pattern.matcher(line);
		if(matcher.matches() && matcher.groupCount() == 5){
			p = new ParserObject(
				matcher.group(1)+" "+matcher.group(2),
				matcher.group(3),
				matcher.group(4),
				matcher.group(5)
			);
			System.out.println(p);
		}else{
			p = new ParserObject("", "", "", line.trim());
		}
		return p;
	}
}