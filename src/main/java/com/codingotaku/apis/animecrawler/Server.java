package com.codingotaku.apis.animecrawler;

import com.codingotaku.apis.animecrawler.callbacks.AnimeFetchListener;
import com.codingotaku.apis.animecrawler.callbacks.EpisodeListListener;
import com.codingotaku.apis.animecrawler.list.AnimeList;
import com.codingotaku.apis.animecrawler.list.EpisodeList;

class Server {
	static AnimeList listAnime(Source source) {
		return null;
	}

	static AnimeList listAnime(Source source, int page) {
		return null;
	}

	static void listAnime(Source source, AnimeFetchListener listener) {
	}

	static void listAnime(Source source, int page, AnimeFetchListener listener) {
	}

	static String getSynopsys(Anime anime) {
		return null;
	}

	static EpisodeList getEpisodeList(Anime anime) {
		return null;
	}

	static EpisodeList getEpisodeList(Anime anime, int page) {
		return null;
	}

	static void getEpisodeList(Anime anime, EpisodeListListener listener) {
	}

	static void getEpisodeList(Anime anime, int page, EpisodeListListener listener) {
	}
	
	static String getPosterUrl(Anime anime) {
		return null;
	}

	public static String generateVideoUrl(Episode episode) {
		return null;
	}
}
