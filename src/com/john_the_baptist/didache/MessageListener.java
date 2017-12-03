package com.john_the_baptist.didache;

import java.util.function.Consumer;

import org.apache.commons.lang3.ArrayUtils;

import com.john_the_baptist.didache.books.Bible;
import com.john_the_baptist.didache.books.Bible2;
import com.john_the_baptist.didache.books.Didache;
import com.john_the_baptist.didache.books.Quran;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {
	
	Message lastBibleBotMessage = null;
	
	@Override
    public void onMessageReceived(MessageReceivedEvent event) {
		/*
		if (event.isFromType(ChannelType.PRIVATE))
		{
			System.out.printf("[PM] %s: %s\n", event.getAuthor().getName(),
						event.getMessage().getContent());
		}
		else
		{
			System.out.printf("[%s][%s] %s: %s\n", event.getGuild().getName(),
						event.getTextChannel().getName(), event.getMember().getEffectiveName(),
						event.getMessage().getContent());
        }
        */
		
		if (!event.getAuthor().isBot()) {
			if (event.getMessage().getContent().contentEquals("d!help")) {
				event.getChannel().sendMessage(
						"**This bot is made by Banská Bystrica** (<@346948823165173771>)"
						+ "\nType \\\"" + DidacheBot.user.getAsMention() + " Didache 15:1\\\" to try it out"
						+ "\nCurrently supports: Didache, Qur'an, Bible."
						+ "\n*Very sorry for the lag when quoting Bible verses,"
						+ " the bot relies on an API that is slow to respond,"
						+ " it's not an issue with the bot* - Banská"
						).queue();
			} else if (event.getMessage().isMentioned(DidacheBot.user)) {
				String message = event.getMessage().getContent().toLowerCase();
				
				Verse verse = Verse.processText(message);
				
				if (verse.getBook().matches("didache")) {
					if (Didache.getVerse(verse.getChapter(), verse.getVerse()) != null) {
						event.getChannel().sendMessage("**" + verse.getBookProper() + " " + verse.getChapter() + " - Charles H. Hoole (CHH)**\n\n```html\n <" + verse.getVerse() + "> " + Didache.getVerse(verse.getChapter(), verse.getVerse()) + "\n```").queue();
					} else {
						event.getChannel().sendMessage("**Invalid verse.**").queue();
					}
				} else if (verse.getBook().matches("quran|qur'an|koran")) {
					if (Quran.getVerse(verse.getChapter(), verse.getVerse()) != null) {
						event.getChannel().sendMessage("**" + verse.getBookProper() + " " + verse.getChapter() + " - The Message of the Qur'an (ASAD)**\n\n```html\n <" + verse.getVerse() + "> " + Quran.getVerse(verse.getChapter(), verse.getVerse()) + "\n```").queue();
					} else {
						event.getChannel().sendMessage("**Invalid verse.**").queue();
					}
				} else if (verse.getBook().matches("genesis|exodus|leviticus|numbers|deuteronomy|joshua|judges|ruth|1 samuel|2 samuel|1 kings|2 kings|1 chronicles|2 chronicles|ezra|nehemiah|esther|job|psalms|psalm|proverbs|proverb|ecclesiastes|song of solomon|isaiah|jeremiah|lamentations|ezekiel|daniel|hosea|joel|amos|obadiah|jonah|micah|nahum|habakkuk|zephaniah|haggai|zechariah|malachi|1 esdras|2 esdras|3 esdras|4 esdras|tobit|tobias|judith|wisdom|wisdom of solomon|ecclesiasticus|sirach|baruch|epistle of jeremy|1 maccabees|2 maccabees|matthew|mark|luke|john|acts|romans|1 corinthians|2 corinthians|galatians|ephesians|philippians|colossians|1 thessalonians|2 thessalonians|1 timothy|2 timothy|titus|philemon|hebrews|james|1 peter|2 peter|1 john|2 john|3 john|jude|revelation")) {
					if (Bible.getVerse(verse.getBook(), verse.getChapter(), verse.getVerse()) != null) {
						event.getChannel().sendMessage("**" + verse.getBookProper() + " " + verse.getChapter() + " - New English Translation (NET)**\n\n```html\n <" + verse.getVerse() + "> " + Bible2.getVerse(verse.getBook(), verse.getChapter(), verse.getVerse()) + "\n```")
							.queue(new Consumer<Message>() {
								@Override
						        public void accept(Message t)
						        {
									
						            if (lastBibleBotMessage != null) {
						            	lastBibleBotMessage.delete().reason(event.getMessage().getAuthor().getName() + " pinged Didache Bot to get the verse, so the Bible Bot's response was auto-deleted.").queue();
						            }
						        }
							});
						//King James Version with Apocrypha, American Edition (KJVA)
					} else {
						event.getChannel().sendMessage("**Invalid verse.**").queue();
					}
				}
				
				
			}
			
			
		} else if (event.getMessage().getAuthor().getId().contentEquals("361033318273384449")) {
			lastBibleBotMessage = event.getMessage();
		}
		
		
		
    }
	
}
