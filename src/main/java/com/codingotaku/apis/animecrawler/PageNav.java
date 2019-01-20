package com.codingotaku.apis.animecrawler;

class PageNav {
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

	static class Builder {
		String pageRegex;
		String pageFormat;
		String first;
		String current;
		String last;
		String lastAltr;

		Builder setPageRegex(String regex) {
			this.pageRegex = regex;
			return this;
		}

		Builder setPageFormat(String stringFormat) {
			this.pageFormat = stringFormat;
			return this;
		}

		Builder setFirst(String xPath) {
			this.first = xPath;
			return this;
		}

		Builder setLast(String xPath) {
			this.last = xPath;
			return this;
		}

		Builder setLastAltr(String xPath) {
			this.lastAltr = xPath;
			return this;
		}

		Builder setCurrent(String xPath) {
			this.current = xPath;
			return this;
		}

		PageNav build() {
			return new PageNav(this);
		}
	}
}
