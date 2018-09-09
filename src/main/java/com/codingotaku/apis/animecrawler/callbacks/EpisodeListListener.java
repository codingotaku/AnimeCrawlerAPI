package com.codingotaku.apis.animecrawler.callbacks;

import com.codingotaku.apis.animecrawler.EpisodeList;
import com.codingotaku.apis.animecrawler.Result;

@FunctionalInterface
public interface EpisodeListListener {
	public void loaded(EpisodeList animeList,Result result);
}
