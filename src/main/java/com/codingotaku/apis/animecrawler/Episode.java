package com.codingotaku.apis.animecrawler;

import java.io.IOException;

import org.jsoup.nodes.Element;

public class Episode {
	private String videoUrl = null;
	String episodeUrl;
	String title;
	Source source;

	Episode(Source source, Element element) {
		this.source = source;
		this.episodeUrl = element.attr("href");
		this.title = element.text();
	}

	public String getTitle() {
		return title;
	}

	public String getVideoUrl() throws IOException {
		if (videoUrl == null) {
			videoUrl = Server.generateVideoUrl(this);
		}
		return videoUrl;
	}

	
}
