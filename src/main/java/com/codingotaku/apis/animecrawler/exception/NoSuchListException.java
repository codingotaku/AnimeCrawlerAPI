package com.codingotaku.apis.animecrawler.exception;

import java.util.NoSuchElementException;

import com.codingotaku.apis.animecrawler.Anime;

public class NoSuchListException extends NoSuchElementException {

	public NoSuchListException(Anime anime, int page) {
		super(String.format("Anime %s doesn't have %d pages", anime.name(), page));
	}

	public NoSuchListException(int page) {
		super(String.format("Selected server doesn't have %d pages", page));
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
