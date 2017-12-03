package com.john_the_baptist.didache;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

public class DidacheBot {
	
	public static String discordAuthKey;
	public static String biblesOrgAuthKey;
	
	public static User user;
	
	public static final String[] BOOKS_ALL = {"didache",
			"quran", "qur'an", "koran",
			"genesis", "exodus", "leviticus", "numbers", "deuteronomy", "joshua", "judges", "ruth", "1 samuel", "2 samuel", "1 kings", "2 kings", "1 chronicles", "2 chronicles", "ezra", "nehemiah", "esther", "job", "psalms", "psalm", "proverbs", "proverb", "ecclesiastes", "song of solomon", "isaiah", "jeremiah", "lamentations", "ezekiel", "daniel", "hosea", "joel", "amos", "obadiah", "jonah", "micah", "nahum", "habakkuk", "zephaniah", "haggai", "zechariah", "malachi", "1 esdras", "2 esdras", "3 esdras", "4 esdras", "tobit", "tobias", "judith", "wisdom", "wisdom of solomon", "ecclesiasticus", "sirach", "baruch", "epistle of jeremy", "1 maccabees", "2 maccabees", "matthew", "mark", "luke", "john", "acts", "romans", "1 corinthians", "2 corinthians", "galatians", "ephesians", "philippians", "colossians", "1 thessalonians", "2 thessalonians", "1 timothy", "2 timothy", "titus", "philemon", "hebrews", "james", "1 peter", "2 peter", "1 john", "2 john", "3 john", "jude", "revelation"
			};

	public static void main(String[] args) throws LoginException, IllegalArgumentException, InterruptedException, RateLimitedException {
		handleProperties();
		
		JDA jda = new JDABuilder(AccountType.BOT).setToken(discordAuthKey).buildBlocking();
		user = jda.getSelfUser();
		
		jda.getPresence().setGame(Game.playing("d!help"));
		
		jda.addEventListener(new MessageListener());
	}
	
	public static void handleProperties() {
		// properties file
		if ( new File("config.properties").exists() ) {
			Properties prop = new Properties();
			InputStream input = null;
			try {
				input = new FileInputStream("config.properties");
				prop.load(input);
				discordAuthKey = prop.getProperty("discordAuthKey");
				biblesOrgAuthKey = prop.getProperty("biblesOrgAuthKey");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			Properties prop = new Properties();
			OutputStream output = null;
			try {
				output = new FileOutputStream("config.properties");
				prop.setProperty("discordAuthKey", "");
				prop.setProperty("biblesOrgAuthKey", "");
				prop.store(output, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

}
