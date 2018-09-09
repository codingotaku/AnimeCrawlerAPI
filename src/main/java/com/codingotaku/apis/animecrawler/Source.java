package com.codingotaku.apis.animecrawler;

public class Source {
	public static final Source Anime1;
	public static final Source AnimeXD;
	static {
		Anime1 = new Source()
				.setListUrl("http://www.anime1.com/content/list/")
				.setListRegex("div.alph-list-box > h4:has(a[name]) + ul > li > a")
				.setDocRegex("div.detail-left > span > span:eq(3)")
				.setPosterRegex("div.detail-cover >a >img")
				.setEpRegex("div.left-left > ul.anime-list > li > a");

		AnimeXD = new Source()
				.setListUrl("http://www.animexd.me/home/anime-list")
				.setListRegex("div.container-left > div.container-item:has(div.ci-title) > ul.arrow-list > li > a")
				.setDocRegex("p.anime-details")
				.setPosterRegex("div.animeDetail-image > img")
				.setEpRegex("div.ci-contents > div.tnContent:nth-child(2) > ul > li > a")
				.setEpRegexAlt("div.ci-contents > div.tnContent:nth-child(1) > ul > li > a");
	}

	private String docRegex;
	private String epRegex;
	private String epRegexAlt;
	private String listRegex;
	private String listUrl;
	private String posterRegex;
	private String vidRegex;
	private String name;
	private boolean multiPage;
	private boolean multiEpList;
	private PageNav pagePattern;

	private Source() {
		vidRegex = "(http[s]?:\\/\\/[^\\/]*.*.mp4\\\\??[^\\\"\\']*)";
		epRegexAlt = null;
		multiPage = false;
		multiEpList = false;
		pagePattern = null;
	}

	PageNav pageNav() {
		return pagePattern;
	}

	String docRegex() {
		return docRegex;
	}

	String epRegex() {
		return epRegex;
	}

	String epRegexAlt() {
		return epRegexAlt;
	}

	String listRegex() {
		return listRegex;
	}

	String listUrl() {
		return listUrl;
	}

	String posterRegex() {
		return posterRegex;
	}

	String vidRegex() {
		return vidRegex;
	}

	boolean isMultiPage() {
		return multiPage;
	}

	boolean isMultiEpList() {
		return multiEpList;
	}

	private Source setDocRegex(String docRegex) {
		this.docRegex = docRegex;
		return this;
	}

	private Source setEpRegex(String epRegex) {
		this.epRegex = epRegex;
		return this;
	}

	private Source setEpRegexAlt(String string) {
		this.epRegexAlt = string;
		return this;
	}

	private Source setListRegex(String listRegex) {
		this.listRegex = listRegex;
		return this;
	}

	private Source setListUrl(String listUrl) {
		this.listUrl = listUrl;
		return this;
	}

	private Source setPosterRegex(String posterRegex) {
		this.posterRegex = posterRegex;
		return this;
	}

	private Source setVidRegex(String vidRegex) {
		this.vidRegex = vidRegex;
		return this;
	}

	private Source setMultiPage(boolean multiPage) {
		this.multiPage = multiPage;
		return this;
	}

	private Source setMultiEpList(boolean multiEpList) {
		this.multiEpList = multiEpList;
		return this;
	}

	public String name() {
		return this.name;
	}

	private Source setPageNav(PageNav pattenrs) {
		this.pagePattern = pattenrs;
		return this;
	}
}
