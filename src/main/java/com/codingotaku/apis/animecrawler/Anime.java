package com.codingotaku.apis.animecrawler;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.codingotaku.apis.animecrawler.callbacks.EpisodeListListener;
import com.codingotaku.apis.animecrawler.callbacks.PosterListener;
import com.codingotaku.apis.animecrawler.callbacks.SynopsisListener;
import com.codingotaku.apis.animecrawler.helpers.Preconditions;

public class Anime {
	String url;
	Source source;
	Document doc = null;
	private String name;
	private String errorMessage = "Anime provided is null";

	Anime(Source source, Element element) {
		this.source = source;
		name = element.text();
		url = element.attr("href");
		if (source.isAppendDomain()) {
			url = source.getDomain() + url;
		}
	}
	public Source getSource() {
		return this.source;
	}
	public String getUrl() {
		return url;
	}

	Document getDoc() throws IOException {
		if (doc == null)
			doc = Jsoup.connect(url).get();
		return doc;
	}

	public void getSynopsis(SynopsisListener listener) {
		Preconditions.checkNotNull(this, errorMessage);
		Server.getSynopsis(this, listener);
	}

	public void listAllEpisodes(EpisodeListListener listener) {
		Preconditions.checkNotNull(this, errorMessage);
		Server.listAllEpisodes(this, listener);
	}

	public void listEpisodes(int page, EpisodeListListener listener) {
		Preconditions.checkNotNull(this, errorMessage);
		Preconditions.checkArgument(page >= 0, String.format("Invalid page number %d", page));
		Server.listEpisodes(this, page, listener);
	}

	public void getPosterUrl(PosterListener listener) {
		Preconditions.checkNotNull(this, errorMessage);
		Server.getPosterUrl(this, listener);
	}

	public String getName() {
		if (name != null)
			return name;
		Preconditions.checkNotNull(this, errorMessage);
		try {
			return Server.getName(this);
		} catch (IOException e) {
			return "none";
		}
	}

	@Override public String toString() {
		return this.getName();
	}
}
