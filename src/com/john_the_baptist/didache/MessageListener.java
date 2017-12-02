package com.john_the_baptist.didache;

import com.john_the_baptist.didache.books.Bible;
import com.john_the_baptist.didache.books.Didache;
import com.john_the_baptist.didache.books.Quran;

import net.dv8tion.jda.core.entities.impl.UserImpl;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {
	
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
						+ "\nType \\\"Didache 15:1\\\" to try it out"
						+ "\nCurrently supports Didache, Qur'an."
						+ "\n*Very sorry for the lag when quoting Bible verses,"
						+ " the bot relies on an API that is slow to respond,"
						+ " it's not an issue with the bot* - Banská"
						).queue();
			} else if (event.getMessage().isMentioned(DidacheBot.user)) {
				String messageDirty = event.getMessage().getContent().toLowerCase();
				String message = messageDirty.replace("@didache bot", "");
				
				String[] books = {"didache",
						"quran", "qur'an", "koran",
						"genesis", "exodus", "leviticus", "numbers", "deuteronomy"};
				String book = books[0];
				
				for (int i = 1; i < books.length; i++) {
					// if there is no instance, the last index is -1, so when you add the length of deuteronomy
					// it thinks it's quoted later than the qur'an, even though it's never quoted at all
					if ( message.contains(books[i])) {
						// the reason book.length() is added is to ensure that "John" doesn't take priority over "1 John", for example
						if ( message.lastIndexOf(books[i]) + books[i].length() > message.lastIndexOf(book) + book.length() ) {
							book = books[i];
						}
						// this could be an OR statement in the first if, but it's not for readability.
						// this ensures that if two books are in the same place, the one with the longer...
						// ...name is considered the requested book, because of the "1 John" / "John" issue.
						else if ( message.lastIndexOf(books[i]) + books[i].length() == message.lastIndexOf(book) + book.length() ) {
							if (books[i].length() > book.length()) {
								book = books[i];
							}
						}
					}
					
				}
				String bookProper = book.substring(0, 1).toUpperCase() + book.substring(1);
				
				Integer lastNumInst = 0;
				for (Integer i=0; i<10; i++) {
					int lastInst = message.lastIndexOf(i.toString());
					if (lastInst > lastNumInst) {
						lastNumInst = lastInst;
					}
				}
				String afterBookName = (String) message.subSequence(message.lastIndexOf(book) + book.length() + 1, lastNumInst + 1);
				
				Integer chapter = Integer.valueOf(afterBookName.split(":")[0]);
				Integer verse = Integer.valueOf(afterBookName.split(":")[1]);
				//event.getChannel().sendMessage("Book: " + book + "\nChapter: " + chapter + "\nVerse: " + verse).queue();
				
				if (book=="didache") {
					if (Didache.getVerse(chapter, verse) != null) {
						event.getChannel().sendMessage("**Didache " + chapter + ":" + verse + " - Charles H. Hoole (CHH)**\n```html\n <" + verse + "> " + Didache.getVerse(chapter, verse) + "\n```").queue();
					} else {
						event.getChannel().sendMessage("**Invalid verse.**").queue();
					}
				} else if (book=="quran" || book=="qu'ran" || book=="koran") {
					if (Quran.getVerse(chapter, verse) != null) {
						event.getChannel().sendMessage("**Qur'an " + chapter + ":" + verse + " - The Message of the Qur'an (ASAD)**\n```html\n <" + verse + "> " + Quran.getVerse(chapter, verse) + "\n```").queue();
					} else {
						event.getChannel().sendMessage("**Invalid verse.**").queue();
					}
				} else if (book.matches("genesis|exodus|leviticus|numbers|deuteronomy")) {
					if (Bible.getVerse(book, chapter, verse) != null) {
						event.getChannel().sendMessage("**" + bookProper + " " + chapter + ":" + verse + " - King James Version with Apocrypha, American Edition (KJVA)**\n```html\n <" + verse + "> " + Bible.getVerse(book, chapter, verse) + "\n```").queue();
					}
				}
				
				
			}
			
			
		}
		
		
		
		
    }
	
}
