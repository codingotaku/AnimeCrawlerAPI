package com.codingotaku.apis.animecrawler;

import java.io.IOException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.codingotaku.apis.animecrawler.callbacks.EpisodeListListener;
import com.codingotaku.apis.animecrawler.callbacks.PosterListener;
import com.codingotaku.apis.animecrawler.callbacks.SynopsysListener;
import com.google.common.base.Preconditions;

public class Anime {
	String url;
	Source source;
	Document doc = null;
	private String name;

	Anime(Source source, Element element) {
		this.source = source;
		name = element.text();
		url = element.attr("href");
	}

	Document getDoc() throws IOException {
		if (doc == null)
			doc = Jsoup.parse(new URL(url), 60000);
		return doc;
	}

	public void getSynopsys(SynopsysListener listener) throws IOException {
		Preconditions.checkNotNull(this, "Anime provided is null");
		Server.getSynopsys(this, listener);
	}

	public void listAllEpisodes(EpisodeListListener listener) {
		Preconditions.checkNotNull(this, "Anime provided is null");
		Server.listAllEpisodes(this, listener);
	}

	public void listEpisodes(int page, EpisodeListListener listener) {
		Preconditions.checkNotNull(this, "Anime provided is null");
		Preconditions.checkArgument(page >= 0, String.format("Invalid page number %d", page));
		Server.listEpisodes(this, page, listener);
	}

	public void getPosterUrl(PosterListener listener) throws IOException {
		Preconditions.checkNotNull(this, "Anime provided is null");
		Server.getPosterUrl(this, listener);
	}

	public String getName() {
		if (name != null)
			return name;
		Preconditions.checkNotNull(this, "Anime provided is null");
		try {
			return Server.getName(this);
		} catch (IOException e) {
			return "none";
		}
	}

	@Override
	public String toString() {
		return this.getName();
	}
}
