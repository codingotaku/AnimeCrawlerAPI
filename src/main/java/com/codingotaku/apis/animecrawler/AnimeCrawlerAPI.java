package com.codingotaku.apis.animecrawler;

import com.codingotaku.apis.animecrawler.callbacks.AnimeFetchListener;
import com.codingotaku.apis.animecrawler.callbacks.EpisodeListListener;
import com.codingotaku.apis.animecrawler.callbacks.PosterListener;
import com.codingotaku.apis.animecrawler.callbacks.SynopsisListener;
import com.codingotaku.apis.animecrawler.helpers.Preconditions;


public class AnimeCrawlerAPI {
	private String errorMessage =  "Anime provided is null";

	public void listAllAnime(Source source, AnimeFetchListener listener) {
		Preconditions.checkNotNull(source, "Source provided is null");
		Preconditions.checkNotNull(listener, "Listener provided is null");
		Server.listAllAnime(source, listener);
	}

	public void listAnime(Source source, int page, AnimeFetchListener listener){
		Preconditions.checkNotNull(source, "Source provided is null");
		Preconditions.checkArgument(page >= 0, String.format("Invalid page number %d", page));
		Preconditions.checkNotNull(listener, "Listener provided is null");
		Server.listAnime(source, page, listener);
	}

	public void getSynopsys(Anime anime, SynopsisListener listener){
		Preconditions.checkNotNull(anime, errorMessage);
		Server.getSynopsis(anime, listener);
	}

	public void listAllEpisodes(Anime anime, EpisodeListListener listener){
		Preconditions.checkNotNull(anime, errorMessage);
		Server.listAllEpisodes(anime, listener);
	}

	public void listEpisodes(Anime anime, int page, EpisodeListListener listener){
		Preconditions.checkNotNull(anime, errorMessage);
		Preconditions.checkArgument(page >= 0, String.format("Invalid page number %d", page));
		Server.listEpisodes(anime, page, listener);
	}

	public void getPosterUrl(Anime anime, PosterListener listener){
		Preconditions.checkNotNull(anime, errorMessage);
		Server.getPosterUrl(anime, listener);
	}
}
