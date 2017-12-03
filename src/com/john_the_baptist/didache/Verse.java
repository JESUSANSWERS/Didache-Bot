package com.john_the_baptist.didache;

public class Verse {
	
	private String book;
	private Integer chapter;
	private Integer verse;
	
	public Verse(String book, Integer chapter, Integer verse) {
		this.book = book;
		this.chapter = chapter;
		this.verse = verse;
	}
	
	public static Verse processText(String message) {
		String book = DidacheBot.BOOKS_ALL[0];
		
		for (int i = 1; i < DidacheBot.BOOKS_ALL.length; i++) {
			// if there is no instance, the last index is -1, so when you add the length of deuteronomy
			// it thinks it's quoted later than the qur'an, even though it's never quoted at all
			if ( message.contains(DidacheBot.BOOKS_ALL[i]) ) {
				// message.lastIndexOf(book) + book.length() could be greater than the first
				// value compared if it's never quoted, for example: if James is quoted from
				// the book name is too short and -1+7 > 0+5
				// the if statement is used to ensure that the book var is actually used in the msg
				// the default value for book is books[0] and that is the only time when the book
				// variable wouldn't be contained in the msg.
				if ( message.contains(book) ) {
					// the reason book.length() is added is to ensure that "John" doesn't take priority over "1 John", for example
					if ( message.lastIndexOf(DidacheBot.BOOKS_ALL[i]) + DidacheBot.BOOKS_ALL[i].length() > message.lastIndexOf(book) + book.length() ) {
						book = DidacheBot.BOOKS_ALL[i];
					}
					// this could be an OR statement in the first if, but it's not for readability.
					// this ensures that if two books are in the same place, the one with the longer...
					// ...name is considered the requested book, because of the "1 John" / "John" issue.
					else if ( message.lastIndexOf(DidacheBot.BOOKS_ALL[i]) + DidacheBot.BOOKS_ALL[i].length() == message.lastIndexOf(book) + book.length() ) {
						if (DidacheBot.BOOKS_ALL[i].length() > book.length()) {
							book = DidacheBot.BOOKS_ALL[i];
						}
					}
				// case where book is never mentioned within msg
				// read comments above the if statement for more info
				} else {
					book = DidacheBot.BOOKS_ALL[i];
				}
			}
		}
		
		
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
		
		return new Verse(book, chapter, verse);
	}
	
	public String getBook() {
		return this.book;
	}
	
	public Integer getChapter() {
		return this.chapter;
	}
	
	public Integer getVerse() {
		return this.verse;
	}
	
	public String getBookProper() {
		return this.book.substring(0, 1).toUpperCase() + this.book.substring(1);
	}
	
}
