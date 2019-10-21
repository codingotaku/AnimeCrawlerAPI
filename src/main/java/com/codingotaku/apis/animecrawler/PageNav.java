package com.codingotaku.apis.animecrawler;

public class PageNav {
	final String pageRegex;
	final String pageFormat;
	final String first;
	final String current;
	final String last;
	final String lastAltr;

	private PageNav(Builder builder) {
		this.pageRegex = builder.pageRegex;
		this.pageFormat = builder.pageFormat;
		this.first = builder.first;
		this.current = builder.current;
		this.last = builder.last;
		this.lastAltr = builder.lastAltr;
	};

	public static class Builder {
		String pageRegex;
		String pageFormat;
		String first;
		String current;
		String last;
		String lastAltr;

		public Builder setPageRegex(String regex) {
			this.pageRegex = regex;
			return this;
		}

		public Builder setPageFormat(String stringFormat) {
			this.pageFormat = stringFormat;
			return this;
		}

		public Builder setFirst(String xPath) {
			this.first = xPath;
			return this;
		}

		public Builder setLast(String xPath) {
			this.last = xPath;
			return this;
		}

		public Builder setLastAltr(String xPath) {
			this.lastAltr = xPath;
			return this;
		}

		public Builder setCurrent(String xPath) {
			this.current = xPath;
			return this;
		}

		public PageNav build() {
			return new PageNav(this);
		}
	}
}
