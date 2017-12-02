package com.john_the_baptist.didache.books;

import org.apache.commons.text.StringEscapeUtils;

import com.github.kevinsawicki.http.HttpRequest;
import com.john_the_baptist.didache.DidacheBot;
import com.sun.org.apache.xml.internal.security.utils.Base64;

public class Bible {
	
	public static String getVerse(String book, int chapter, int verse) {
		String json = HttpRequest.get("https://bibles.org/v2/eng-KJVA/passages.js?q[]=" + book + "+" + chapter + ":" + verse + "&version=eng-KJVA").header("Authorization", "Basic " + Base64.encode( (DidacheBot.BIBLES_AUTH_KEY + ":x").getBytes() ) ).body();
		String clean = StringEscapeUtils.unescapeJson(json);
		if (!clean.contains("{\"type\":\"passages\",\"passages\":[]}},") && clean.contains("{\"response\":{\"search\":{\"result\":")) {
			String verseText = clean.split("</sup>")[1].split("</p>\",")[0];
			return verseText;
		}
		return null;
	}
	
}