package com.codingotaku.apis.animecrawler;

public class Source {
	public static final Source Anime1;
	public static final Source Sub;
	public static final Source Dub;
	static {
		Anime1 = new Source()
				.setListUrl("http://www.anime1.com/content/list/")
				.setListRegex("div.alph-list-box > h4:has(a[name]) + ul > li > a")
				.setNameRegex("h1.blue-main-title")
				.setDocRegex("div.detail-left > span > span:eq(3)")
				.setPosterRegex("div.detail-cover >a >img")
				.setEpRegex("div.left-left > ul.anime-list > li > a");

		Sub = new Source()
				.setListUrl("https://www.thewatchcartoononline.tv/subbed-anime-list")
				.setListRegex("div.ddmcc ul > li > a")
				.setNameRegex("div.h1-tag > a")
				.setDocRegex("div#sidebar_cat > p")
				.setPosterRegex("div#sidebar_cat > img")
				.setEpRegex("div.cat-eps > a");
		
		Dub = new Source()
				.setListUrl("https://www.thewatchcartoononline.tv/dubbed-anime-list")
				.setListRegex("div.ddmcc ul > li > a")
				.setNameRegex("div.h1-tag > a")
				.setDocRegex("div#sidebar_cat > p")
				.setPosterRegex("div#sidebar_cat > img")
				.setEpRegex("div.cat-eps > a");
	}

	private String docRegex;
	private String epRegex;
	private String epRegexAlt;
	private String listRegex;
	private String listUrl;
	private String posterRegex;
	private String vidRegex;
	private String nameRegex;
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
	
	String nameRegex() {
		return this.nameRegex;
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

	private Source setNameRegex(String nameRegex) {
		this.nameRegex = nameRegex;
		return this;
	}
	
	private Source setPageNav(PageNav pattenrs) {
		this.pagePattern = pattenrs;
		return this;
	}
}