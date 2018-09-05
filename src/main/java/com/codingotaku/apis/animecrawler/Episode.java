package com.codingotaku.apis.animecrawler;

public class Episode {
	private String videoUrl = null;
	private EpisodeData data = null;
	Source source;

	Episode(Source source, EpisodeData data) {
		this.source = source;
		this.data = data;
	}

	public String getVideoUrl() {
		if (videoUrl == null) {
			videoUrl = regenerateVidoeUrl();
		}
		return videoUrl;
	}

	public String regenerateVidoeUrl() {
		return Server.generateVideoUrl(this);
	}

	public boolean hasNext() {
		return data.next != null;
	}

	public boolean hasPrev() {
		return data.prev != null;
	}
}
