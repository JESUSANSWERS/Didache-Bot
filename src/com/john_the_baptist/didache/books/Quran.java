package com.john_the_baptist.didache.books;

import org.apache.commons.text.StringEscapeUtils;

import com.github.kevinsawicki.http.HttpRequest;

public class Quran {
	
	public static String getVerse(int chapter, int verse) {
		String json = HttpRequest.get("http://api.alquran.cloud/ayah/" + chapter + ":" + verse + "/en.asad").body();
		if (json.contains("{\"code\":200,\"status\":\"OK\",\"data\":{\"number\":")) {
			String verseText = json.split(",\"text\":\"")[1].split("\",\"edition\"")[0];
			return StringEscapeUtils.unescapeJson(verseText);
		}
		return null;
	}
	
}
