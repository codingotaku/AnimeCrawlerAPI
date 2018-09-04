package com.codingotaku.apis.animecrawler;

import com.codingotaku.apis.animecrawler.callbacks.EpisodeListListener;
import com.codingotaku.apis.animecrawler.list.EpisodeList;

public class Anime {
	private String synopsys;
	private String poster;

	Anime(Source source) {

	}

	public String getSynopsys() {
		return synopsys;
	}

	public EpisodeList getEpisodeList() {
		return null;
	}

	public EpisodeList getEpisodeList(int page) {
		return null;
	}

	public void getEpisodeList(EpisodeListListener listener) {
	}

	public void getEpisodeList(int page, EpisodeListListener listener) {
	}

	public String getPosterLink() {
		return poster;
	}
}
