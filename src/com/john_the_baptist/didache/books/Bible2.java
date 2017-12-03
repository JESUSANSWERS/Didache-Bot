package com.john_the_baptist.didache.books;

import com.github.kevinsawicki.http.HttpRequest;

public class Bible2 {
	
	public static String getVerse(String book, int chapter, int verse) {
		String html = HttpRequest.get("http://labs.bible.org/api/?passage=" + book + "%20" + chapter + ":" + verse).header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36").body();
		if ( html.split("<b>")[1].split("</b>")[0].contentEquals(chapter + ":" + verse) ) {
			String verseText = html.split("<b>" + chapter + ":" + verse + "</b> ")[1];
			return verseText;
		}
		return null;
	}
	
}

/*
package com.john_the_baptist.didache.books;

import org.apache.commons.text.StringEscapeUtils;

import com.github.kevinsawicki.http.HttpRequest;

public class Bible2 {
	
	public static String getVerse(String book, int chapter, int verse) {
		String json = HttpRequest.get("http://getbible.net/json?p=" + book + " " + chapter + ":" + verse).body();
		String clean = StringEscapeUtils.unescapeJson(json);
		System.out.println(clean);
		if (!json.contains("NULL")) {
			String verseText = clean.split(",\"verse\":\"")[1].split("\n\"}}}],")[0];
			return verseText;
		}
		return null;
	}
	
}
*/