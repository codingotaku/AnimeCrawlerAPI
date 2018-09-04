package com.codingotaku.apis.animecrawler.callbacks;

import com.codingotaku.apis.animecrawler.list.EpisodeList;

@FunctionalInterface
public interface EpisodeListListener {
	public void loaded(EpisodeList animeList);
}
