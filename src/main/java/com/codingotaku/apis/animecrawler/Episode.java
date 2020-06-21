package com.codingotaku.apis.animecrawler;

import java.io.IOException;

import org.jsoup.nodes.Element;

public class Episode {
	private String videoUrl = null;
	String episodeUrl;
	final String title;
	final String animeName;
	final Source source;

	Episode(String animeName,Source source, Element element) {
		this.animeName = animeName;
		this.source = source;
		this.episodeUrl = element.attr("href");
		this.title = element.text();
		if (source.isAppendDomain()) {
			this.episodeUrl = source.getDomain() + episodeUrl;
		}
	}

	public String getTitle() {
		return title;
	}
	
	public String getanimeName(){
		return animeName;
	}

	public String getVideoUrl() throws IOException {
		if (videoUrl == null) {
			videoUrl = Server.getInstance().generateVideoUrl(this);
		}
		return videoUrl;
	}

	@Override
	public String toString() {
		return this.getTitle();
	}

}
