package com.codingotaku.apis.animecrawler;

import java.util.List;

import com.codingotaku.apis.animecrawler.callbacks.AnimeFetchListener;

public class AnimeList {
	int page;
	int total;
	boolean hasNext;
	private Source source;
	private List<Anime> animes;

	AnimeList(Source source, List<Anime> animes) {
		this.animes = animes;
		this.source = source;
	}

	public void getNextPage(AnimeFetchListener listener) {
		Server.getInstance().listAnime(source, page + 1, listener);
	}

	public List<Anime> getAnimes() {
		return animes;
	}
}
