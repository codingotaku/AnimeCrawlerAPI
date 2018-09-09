package com.codingotaku.apis.animecrawler.callbacks;

import com.codingotaku.apis.animecrawler.AnimeList;
import com.codingotaku.apis.animecrawler.Result;

@FunctionalInterface
public interface AnimeFetchListener {
	public void loaded(AnimeList animeList, Result result);
}
