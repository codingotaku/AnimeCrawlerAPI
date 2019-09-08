package com.codingotaku.apis.animecrawler;

import java.io.IOException;

import com.codingotaku.apis.animecrawler.callbacks.AnimeFetchListener;
import com.codingotaku.apis.animecrawler.callbacks.EpisodeListListener;
import com.codingotaku.apis.animecrawler.callbacks.SynopsysListener;
import com.google.common.base.Preconditions;

public class AnimeCrawlerAPI {

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

	public void getSynopsys(Anime anime, SynopsysListener listener){
		Preconditions.checkNotNull(anime, "Anime provided is null");
		Server.getSynopsys(anime, listener);
	}

	public void listAllEpisodes(Anime anime, EpisodeListListener listener){
		Preconditions.checkNotNull(anime, "Anime provided is null");
		Server.listAllEpisodes(anime, listener);
	}

	public void listEpisodes(Anime anime, int page, EpisodeListListener listener){
		Preconditions.checkNotNull(anime, "Anime provided is null");
		Preconditions.checkArgument(page >= 0, String.format("Invalid page number %d", page));
		Server.listEpisodes(anime, page, listener);
	}

	public String getPosterUrl(Anime anime) throws IOException {
		Preconditions.checkNotNull(anime, "Anime provided is null");
		return Server.getPosterUrl(anime);
	}
}
