package com.codingotaku.apis.animecrawler.callbacks;

import com.codingotaku.apis.animecrawler.list.AnimeList;

@FunctionalInterface
public interface AnimeFetchListener {
	public void loaded(AnimeList animeList);
}
