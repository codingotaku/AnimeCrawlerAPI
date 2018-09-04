package com.codingotaku.apis.animecrawler;

public class Episode {
	private String videoUrl = null;

	public String getVideoUrl() {
		if (videoUrl == null) {
			videoUrl = regenerateVidoeUrl();
		}
		return videoUrl;
	}

	public String regenerateVidoeUrl() {
		// TODO generate video URL
		return null;
	}
}
