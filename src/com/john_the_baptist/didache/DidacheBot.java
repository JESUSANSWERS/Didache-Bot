package com.john_the_baptist.didache;

import javax.security.auth.login.LoginException;

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

	public static void main(String[] args) throws LoginException, IllegalArgumentException, InterruptedException, RateLimitedException {
		
		JDA jda = new JDABuilder(AccountType.BOT).setToken(DISCORD_AUTH_KEY).buildBlocking();
		user = jda.getSelfUser();
		
		jda.getPresence().setGame(Game.playing("d!help"));
		
		jda.addEventListener(new MessageListener());
	}
	
	

}
