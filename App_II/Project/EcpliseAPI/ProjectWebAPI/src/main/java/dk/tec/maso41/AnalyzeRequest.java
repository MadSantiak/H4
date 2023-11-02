package dk.tec.maso41;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalyzeRequest {

	private MatchEnum match;
	private int id;
	
	public MatchEnum getMatch() {
		return match;
	}
	
	public int getId() {
		return id;
	}
	
	public AnalyzeRequest(String pathInfo) {
		Matcher matcher = Pattern.compile("/Person/([0-9]+)").matcher(pathInfo);
		System.out.print(pathInfo);
		if (matcher.find()) {
			match = MatchEnum.MatchPersonId;
			id = Integer.parseInt(matcher.group(1));
		} else {
			matcher = Pattern.compile("/Person").matcher(pathInfo);
			if (matcher.find()) {
				match = MatchEnum.MatchPerson;
			} else {
				match = MatchEnum.MatchNo;
			}
		}
		
		
	}

}
