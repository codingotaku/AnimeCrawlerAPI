package com.codingotaku.apis.animecrawler;

import com.codingotaku.apis.animecrawler.callbacks.AnimeFetchListener;
import com.codingotaku.apis.animecrawler.callbacks.EpisodeListListener;
import com.codingotaku.apis.animecrawler.list.AnimeList;
import com.codingotaku.apis.animecrawler.list.EpisodeList;
import com.google.common.base.Preconditions;

public class AnimeCrawlerAPI {
	public AnimeList listAnime(Source source) {
		Preconditions.checkNotNull(source, "Source provided is null");
		return Server.listAnime(source);
	}

	public AnimeList listAnime(Source source, int page) {
		Preconditions.checkArgument(page >= 0, String.format("Invalid page number %d", page));
		return Server.listAnime(source, page);
	}

	public void listAnime(Source source, AnimeFetchListener listener) {
		Preconditions.checkNotNull(source, "Source provided is null");
		Preconditions.checkNotNull(listener, "Listener provided is null");
		new Thread(() -> listener.loaded(Server.listAnime(source))).start();
	}

	public void listAnime(Source source, int page, AnimeFetchListener listener) {
		Preconditions.checkNotNull(source, "Source provided is null");
		Preconditions.checkArgument(page >= 0, String.format("Invalid page number %d", page));
		Preconditions.checkNotNull(listener, "Listener provided is null");
		new Thread(() -> listener.loaded(Server.listAnime(source, page))).start();
	}

	public String getSynopsys(Anime anime) {
		Preconditions.checkNotNull(anime, "Anime provided is null");
		return Server.getSynopsys(anime);
	}

	public EpisodeList getEpisodeList(Anime anime) {
		Preconditions.checkNotNull(anime, "Anime provided is null");
		return Server.getEpisodeList(anime);
	}

	public EpisodeList getEpisodeList(Anime anime, int page) {
		Preconditions.checkNotNull(anime, "Anime provided is null");
		Preconditions.checkArgument(page >= 0, String.format("Invalid page number %d", page));
		return Server.getEpisodeList(anime, page);
	}

	public void getEpisodeList(Anime anime, EpisodeListListener listener) {
		Preconditions.checkNotNull(anime, "Anime provided is null");
		new Thread(() -> listener.loaded(Server.getEpisodeList(anime))).start();
	}

	public void getEpisodeList(Anime anime, int page, EpisodeListListener listener) {
		Preconditions.checkNotNull(anime, "Anime provided is null");
		Preconditions.checkArgument(page >= 0, String.format("Invalid page number %d", page));
		new Thread(() -> listener.loaded(Server.getEpisodeList(anime))).start();
	}

	public String getPosterUrl(Anime anime) {
		Preconditions.checkNotNull(anime, "Anime provided is null");
		return Server.getPosterUrl(anime);
	}
}
