package com.codingotaku.apis.animecrawler;

import java.net.URI;

import com.codingotaku.apis.animecrawler.callbacks.AnimeFetchListener;
import com.codingotaku.apis.animecrawler.callbacks.EpisodeListListener;
import com.codingotaku.apis.animecrawler.list.AnimeList;
import com.codingotaku.apis.animecrawler.list.EpisodeList;

public class AnimeCrawlerAPI {
	public AnimeList listAnime(Source source) {
		return null;
	}

	public AnimeList listAnime(Source source, int page) {
		return null;
	}

	public void listAnime(Source source, AnimeFetchListener listener) {
	}

	public void listAnime(Source source, int page, AnimeFetchListener listener) {
	}

	public String getSynopsys(Anime anime) {
		return null;
	}

	public EpisodeList getEpisodeList(Anime anime) {
		return null;
	}

	public EpisodeList getEpisodeList(Anime anime, int page) {
		return null;
	}

	public void getEpisodeList(Anime anime, EpisodeListListener listener) {
	}

	public void getEpisodeList(Anime anime, int page, EpisodeListListener listener) {
	}
	
	public URI getPosterUrl(Anime anime) {
		return null;
	}
}
