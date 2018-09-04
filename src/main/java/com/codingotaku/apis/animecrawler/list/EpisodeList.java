package com.codingotaku.apis.animecrawler.list;

import java.util.List;

import com.codingotaku.apis.animecrawler.Episode;

public class EpisodeList {
	private final boolean hasNext;
	public final int page;
	public final int total;

	public EpisodeList(int page, int total) {
		this.page = page;
		this.total = total;
		hasNext = page < total;
	}

	public boolean hasNext() {
		return hasNext;
	}
	
	public List<Episode> getEpisodes(){
		//TODO List out all episodes
		return null;
	}
}
