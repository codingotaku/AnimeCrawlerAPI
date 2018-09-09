package com.codingotaku.apis.animecrawler;

import java.util.List;

public class EpisodeList {
	private final boolean hasNext;
	private final int page;
	private final int total;
	private final List<Episode> episodes;

	public EpisodeList(int page, int total, List<Episode> episodes) {
		this.page = page;
		this.total = total;
		this.hasNext = page < total;
		this.episodes = episodes;
	}

	public boolean hasNext() {
		return hasNext;
	}

	public int pageNum() {
		return page;
	}

	public int totalPages() {
		return total;
	}

	public List<Episode> episodes() {
		return episodes;
	}
}
