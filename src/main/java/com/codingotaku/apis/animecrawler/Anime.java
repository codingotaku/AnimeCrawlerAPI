package com.codingotaku.apis.animecrawler;

import com.codingotaku.apis.animecrawler.callbacks.EpisodeListListener;
import com.codingotaku.apis.animecrawler.list.EpisodeList;
import com.google.common.base.Preconditions;

public class Anime {
	private String synopsys =null;
	private String poster =null;

	Anime(Source source) {

	}

	public String getSynopsys() {
		if(synopsys!=null) return synopsys;
		Preconditions.checkNotNull(this, "Anime provided is null");
		return Server.getSynopsys(this);
	}

	public EpisodeList getEpisodeList() {
		Preconditions.checkNotNull(this, "Anime provided is null");
		return Server.getEpisodeList(this);
	}

	public EpisodeList getEpisodeList(int page) {
		Preconditions.checkNotNull(this, "Anime provided is null");
		Preconditions.checkArgument(page >= 0, String.format("Invalid page number %d", page));
		return Server.getEpisodeList(this, page);
	}

	public void getEpisodeList(EpisodeListListener listener) {
		Preconditions.checkNotNull(this, "Anime provided is null");
		new Thread(() -> listener.loaded(Server.getEpisodeList(this))).start();
	}

	public void getEpisodeList(int page, EpisodeListListener listener) {
		Preconditions.checkNotNull(this, "Anime provided is null");
		Preconditions.checkArgument(page >= 0, String.format("Invalid page number %d", page));
		new Thread(() -> listener.loaded(Server.getEpisodeList(this))).start();
	}

	public String getPosterUrl() {
		if(poster!=null) return poster;
		Preconditions.checkNotNull(this, "Anime provided is null");
		return Server.getPosterUrl(this);
	}
}
