package com.john_the_baptist.didache.books;

import org.apache.commons.text.StringEscapeUtils;

import com.github.kevinsawicki.http.HttpRequest;
import com.john_the_baptist.didache.DidacheBot;
import com.sun.org.apache.xml.internal.security.utils.Base64;

public class Bible {
	
	public static String getVerse(String book, int chapter, int verse) {
		String json = HttpRequest.get("https://bibles.org/v2/eng-KJVA/passages.js?q[]=" + book + "+" + chapter + ":" + verse + "&version=eng-KJVA").header("Authorization", "Basic " + Base64.encode( (DidacheBot.biblesOrgAuthKey + ":x").getBytes() ) ).body();
		String clean = StringEscapeUtils.unescapeJson(json);
		if (!clean.contains("{\"type\":\"passages\",\"passages\":[]}},") && clean.contains("{\"response\":{\"search\":{\"result\":")) {
			String verseText = clean.split("</sup>")[1].split("</p>\",")[0];
			String finalText = verseText.replace("<span class=\"add\">", "").replace("</span>", "").replace("</p>", "").replace("<p class=\"p\">", "\n");
			if (finalText.substring(0, 1) == " ") {
				return finalText;
			} else {
				return finalText.substring(1);
			}
		}
		return null;
	}
	
}
