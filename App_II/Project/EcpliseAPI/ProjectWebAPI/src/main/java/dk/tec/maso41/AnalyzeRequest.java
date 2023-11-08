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
	
	/**
	 * We analyse the request sent to the API so we know whether the GET/POST/DELETE/PUT
	 * is in regards to a Person, Haircolor or Programming Language.
	 * Note we take the potential for IDs first to avoid false-positives, should we check generalized paths first.
	 * @param pathInfo
	 */
	public AnalyzeRequest(String pathInfo) {
		Matcher personMatcher = Pattern.compile("/Person/([0-9]+)").matcher(pathInfo);
		Matcher colorMatcher = Pattern.compile("/Haircolor/([0-9]+)").matcher(pathInfo);
		Matcher progMatcher = Pattern.compile("/ProgrammingLanguage/([0-9]+)").matcher(pathInfo);
		
		if (personMatcher.find()) {
			match = MatchEnum.MatchPersonId;
			id = Integer.parseInt(personMatcher.group(1));
		} else if (colorMatcher.find()) {
			match = MatchEnum.MatchHaircolorId;
			id = Integer.parseInt(colorMatcher.group(1));
		} else if (progMatcher.find()) {
			match = MatchEnum.MatchProgrammingLanguageId;
			id = Integer.parseInt(progMatcher.group(1));
		}
		else	{
			personMatcher = Pattern.compile("/Person").matcher(pathInfo);
			colorMatcher = Pattern.compile("/Haircolor").matcher(pathInfo);
			progMatcher = Pattern.compile("/ProgrammingLanguage").matcher(pathInfo);
			
			if (personMatcher.find()) {
				match = MatchEnum.MatchPerson;
			} else if (colorMatcher.find()) {
				match = MatchEnum.MatchHaircolor;
			} else if (progMatcher.find()) {
				match = MatchEnum.MatchProgrammingLanguage;
			} else {
				match = MatchEnum.MatchNo;
			}
		}
	}
}
