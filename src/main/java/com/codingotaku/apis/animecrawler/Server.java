package com.codingotaku.apis.animecrawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.codingotaku.apis.animecrawler.callbacks.AnimeFetchListener;
import com.codingotaku.apis.animecrawler.callbacks.EpisodeListListener;
import com.codingotaku.apis.animecrawler.callbacks.PosterListener;
import com.codingotaku.apis.animecrawler.callbacks.SynopsisListener;
import com.codingotaku.apis.animecrawler.exception.NoSuchListException;

class Server {
	private static Server server;

	private Server() {
		// Just making sure that an object won't be initialized elsewhere
	}

	static Server getInstance() {
		if (server == null) {
			server = new Server();
		}
		return server;

	}

	void listAllAnime(Source source, AnimeFetchListener listener) {
		new Thread(() -> {
			try {
				Document doc = Jsoup.connect(source.getListUrl()).get();
				Elements elements = doc.select(source.getListRegex());
				listener.loaded(generateAnimeList(source, elements), new Result());
			} catch (IOException e) {
				listener.loaded(null, new Result(e));
			}
		}).start();
	}

	void listAnime(Source source, int page, AnimeFetchListener listener) {
		if (source.isMultiPage()) {
			// TODO add servers that has multiple pages
			listener.loaded(null, new Result(new NoSuchListException(page)));
		} else if (page == 0) {
			listAllAnime(source, listener);
		} else {
			listener.loaded(null, new Result(new NoSuchListException(page)));
		}
	}

	void getSynopsis(Anime anime, SynopsisListener listener) {
		try {
			boolean append = anime.source.isAppendDomain();
			String url = anime.getDoc().select(anime.source.getDocRegex()).text();
			if (append) {
				url = anime.source.getDomain() + url;
			}
			listener.loaded(url, new Result());
		} catch (IOException e) {
			listener.loaded(null, new Result(e));
		}
	}

	private Elements getEpElements(Document doc, Source source) {
		Elements elements = doc.select(source.getEpRegex());
		if (elements.isEmpty() && source.getEpRegexAlt() != null) {
			elements = doc.select(source.getEpRegexAlt());
		}
		return elements;
	}

	private Element getEpLastPage(Document doc, PageNav pageNav) {
		Element last = doc.selectFirst(pageNav.last);
		if (last == null)
			last = doc.select(pageNav.lastAltr).last();
		return last;
	}

	void listAllEpisodes(Anime anime, EpisodeListListener listener) {
		new Thread(() -> {
			try {
				Source source = anime.source;
				Document doc = anime.getDoc();
				Elements elements = getEpElements(doc, source);
				if (source.isMultiEpList()) {
					PageNav pageNav = source.getPageNav();
					String pageUrl = anime.url;
					Element current = doc.selectFirst(pageNav.current);
					if (current == null) {
						listener.loaded(generateEpList(anime, source, elements), new Result());
						return;
					}
					int currentId = Integer.parseInt(current.text());
					int lastId;
					Element last = getEpLastPage(doc, pageNav);
					if (last == null || current.text().equals(last.text())) {
						// Give up, Can't find more pages
						listener.loaded(generateEpList(anime, source, elements), new Result());
						return;
					}

					Document tmp = Jsoup.connect(last.attr("href")).get();
					String txt = tmp.selectFirst(pageNav.current).text();
					lastId = Integer.parseInt(txt);

					List<Episode> episodes = new ArrayList<>();
					for (int i = currentId; i <= lastId; i++) {
						doc = Jsoup.connect(pageUrl + String.format(pageNav.pageFormat, i)).get();
						elements = getEpElements(doc, source);
						elements.forEach(element -> episodes.add(new Episode(anime.getName(), source, element)));
					}
					listener.loaded(new EpisodeList(1, 1, episodes), new Result());
				} else {
					listener.loaded(generateEpList(anime, source, elements), new Result());
				}
			} catch (IOException e) {
				listener.loaded(null, new Result(e));
			}
		}).start();

	}

	void listEpisodes(Anime anime, int page, EpisodeListListener listener) {
		new Thread(() -> {
			try {
				Source source = anime.source;
				if (source.isMultiEpList()) {
					Document doc = Jsoup.connect(anime.url + String.format(source.getPageNav().pageFormat, page)).get();
					List<Episode> episodes = new ArrayList<>();
					Elements elements = doc.select(source.getEpRegex());
					if (elements.isEmpty() && source.getEpRegexAlt() != null) {
						elements = doc.select(source.getEpRegexAlt());
					}
					if (elements.isEmpty())
						listener.loaded(null, new Result(new NoSuchListException(anime, page)));
					elements.forEach(element -> episodes.add(new Episode(anime.getName(), source, element)));
					listener.loaded(new EpisodeList(1, 1, episodes), new Result());
				} else if (page == 0) {
					listAllEpisodes(anime, listener);
				} else {
					listener.loaded(null, new Result(new NoSuchListException(anime, page)));
				}
			} catch (IOException e) {
				listener.loaded(null, new Result(e));
			}
		}).start();
	}

	void getPosterUrl(Anime anime, PosterListener listener) {
		try {
			listener.loaded(anime.getDoc().select(anime.source.getPosterRegex()).first().attr("src"), new Result());
		} catch (IOException e) {
			listener.loaded(null, new Result(e));
		}
	}

	String generateVideoUrl(Episode episode) throws IOException {
		Document doc = Jsoup.connect(episode.episodeUrl).get();
		Pattern pattern = Pattern.compile(episode.source.getVidRegex());
		Matcher matcher = pattern.matcher(doc.data());
		if (matcher.find())
			return matcher.group();
		return null;
	}

	private EpisodeList generateEpList(Anime anime, Source source, Elements elements) {
		List<Episode> episodes = new ArrayList<>();
		elements.forEach(element -> episodes.add(new Episode(anime.getName(), source, element)));
		return new EpisodeList(1, 1, Collections.unmodifiableList(episodes));
	}

	private AnimeList generateAnimeList(Source source, Elements elements) {
		List<Anime> list = new ArrayList<>();
		elements.forEach(element -> list.add(new Anime(source, element)));
		return new AnimeList(source, Collections.unmodifiableList(list));
	}

	public String getName(Anime anime) throws IOException {
		return anime.getDoc().select(anime.source.getNameRegex()).text();
	}
}
