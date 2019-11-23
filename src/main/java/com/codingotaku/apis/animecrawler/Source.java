package com.codingotaku.apis.animecrawler;

public class Source {
	public static class SourceBuilder {
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
		private boolean appendDomain;
		private String domain;

		public SourceBuilder() {
			vidRegex = "(http[s]?:\\/\\/[^\\/]*.*.mp4\\\\??[^\\\"\\']*)";
			epRegexAlt = null;
			multiPage = false;
			multiEpList = false;
			pagePattern = null;
		}

		public SourceBuilder setDocRegex(String docRegex) {
			this.docRegex = docRegex;
			return this;
		}

		public SourceBuilder setEpRegex(String epRegex) {
			this.epRegex = epRegex;
			return this;
		}

		public SourceBuilder setEpRegexAlt(String string) {
			this.epRegexAlt = string;
			return this;
		}

		public SourceBuilder setListRegex(String listRegex) {
			this.listRegex = listRegex;
			return this;
		}

		public SourceBuilder setListUrl(String listUrl) {
			this.listUrl = listUrl;
			return this;
		}

		public SourceBuilder setPosterRegex(String posterRegex) {
			this.posterRegex = posterRegex;
			return this;
		}

		public SourceBuilder setVidRegex(String vidRegex) {
			this.vidRegex = vidRegex;
			return this;
		}

		public SourceBuilder setMultiPage(boolean multiPage) {
			this.multiPage = multiPage;
			return this;
		}

		public SourceBuilder setMultiEpList(boolean multiEpList) {
			this.multiEpList = multiEpList;
			return this;
		}

		public SourceBuilder setNameRegex(String nameRegex) {
			this.nameRegex = nameRegex;
			return this;
		}

		public SourceBuilder setPageNav(PageNav pattenrs) {
			this.pagePattern = pattenrs;
			return this;
		}

		public SourceBuilder setDomain(String domain) {
			this.appendDomain = true;
			this.domain = domain;
			return this;
		}

		public Source build() {
			Source source = new Source();
			source.docRegex = this.docRegex;
			source.epRegex = this.epRegex;
			source.epRegexAlt = this.epRegexAlt;
			source.listRegex = this.listRegex;
			source.listUrl = this.listUrl;
			source.posterRegex = this.posterRegex;
			source.vidRegex = this.vidRegex;
			source.nameRegex = this.nameRegex;
			source.multiPage = this.multiPage;
			source.multiEpList = this.multiEpList;
			source.pageNav = this.pagePattern;
			source.appendDomain = this.appendDomain;
			source.domain = this.domain;
			return source;
		}

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
	private PageNav pageNav;
	private boolean appendDomain;
	private String domain;

	private Source() {

	}

	public PageNav getPageNav() {
		return pageNav;
	}

	public String getDocRegex() {
		return docRegex;
	}

	public String getEpRegex() {
		return epRegex;
	}

	public String getEpRegexAlt() {
		return epRegexAlt;
	}

	public String getListRegex() {
		return listRegex;
	}

	public String getListUrl() {
		return listUrl;
	}

	public String getPosterRegex() {
		return posterRegex;
	}

	public String getVidRegex() {
		return vidRegex;
	}

	public String getNameRegex() {
		return this.nameRegex;
	}

	public boolean isMultiPage() {
		return multiPage;
	}

	public boolean isMultiEpList() {
		return multiEpList;
	}

	public boolean isAppendDomain() {
		return appendDomain;
	}

	public String getDomain() {
		return domain;
	}
}