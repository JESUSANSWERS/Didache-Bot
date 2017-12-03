package com.john_the_baptist.didache;

import javax.security.auth.login.LoginException;

import org.apache.commons.lang3.ArrayUtils;

import com.john_the_baptist.didache.books.Bible;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

public class DidacheBot {
	
	public static final String DISCORD_AUTH_KEY = "";
	public static final String BIBLES_AUTH_KEY = "";
	
	public static User user;
	
	public static final String[] BOOKS_ALL = {"didache",
			"quran", "qur'an", "koran",
			"genesis", "exodus", "leviticus", "numbers", "deuteronomy", "joshua", "judges", "ruth", "1 samuel", "2 samuel", "1 kings", "2 kings", "1 chronicles", "2 chronicles", "ezra", "nehemiah", "esther", "job", "psalms", "psalm", "proverbs", "proverb", "ecclesiastes", "song of solomon", "isaiah", "jeremiah", "lamentations", "ezekiel", "daniel", "hosea", "joel", "amos", "obadiah", "jonah", "micah", "nahum", "habakkuk", "zephaniah", "haggai", "zechariah", "malachi", "1 esdras", "2 esdras", "3 esdras", "4 esdras", "tobit", "tobias", "judith", "wisdom", "wisdom of solomon", "ecclesiasticus", "sirach", "baruch", "epistle of jeremy", "1 maccabees", "2 maccabees", "matthew", "mark", "luke", "john", "acts", "romans", "1 corinthians", "2 corinthians", "galatians", "ephesians", "philippians", "colossians", "1 thessalonians", "2 thessalonians", "1 timothy", "2 timothy", "titus", "philemon", "hebrews", "james", "1 peter", "2 peter", "1 john", "2 john", "3 john", "jude", "revelation"
			};

	public static void main(String[] args) throws LoginException, IllegalArgumentException, InterruptedException, RateLimitedException {
		
		JDA jda = new JDABuilder(AccountType.BOT).setToken(DISCORD_AUTH_KEY).buildBlocking();
		user = jda.getSelfUser();
		
		jda.getPresence().setGame(Game.playing("d!help"));
		
		jda.addEventListener(new MessageListener());
	}
	
	

}
