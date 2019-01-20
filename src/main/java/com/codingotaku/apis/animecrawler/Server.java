package com.codingotaku.apis.animecrawler;

import java.io.IOException;
import java.net.URL;
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
import com.codingotaku.apis.animecrawler.exception.NoSuchListException;

class Server {
	static void listAllAnime(Source source, AnimeFetchListener listener) {
		new Thread(() -> {
			try {
				Document doc = Jsoup.parse(new URL(source.listUrl()), 60000);
				Elements elements = doc.select(source.listRegex());
				listener.loaded(generateAnimeList(source, elements), new Result());
			} catch (IOException e) {
				listener.loaded(null, new Result(e));
			}
		}).start();
	}

	static void listAnime(Source source, int page, AnimeFetchListener listener) {
		if (source.isMultiPage()) {
			// TODO add servers that has multiple pages
			listener.loaded(null, new Result(new NoSuchListException(page)));
		} else if (page == 0) {
			listAllAnime(source, listener);
		} else {
			// TODO Throw exception
			listener.loaded(null, new Result(new NoSuchListException(page)));
		}
	}

	static String getSynopsys(Anime anime) throws IOException {
		String synopsys = anime.getDoc().select(anime.source.docRegex()).text();
		return synopsys;
	}

	static void listAllEpisodes(Anime anime, EpisodeListListener listener) {
		new Thread(() -> {
			try {
				Source source = anime.source;
				Document doc = anime.getDoc();
				Elements elements = doc.select(source.epRegex());
				if (elements.isEmpty() && source.epRegexAlt() != null) {
					elements = doc.select(source.epRegexAlt());
				}
				if (source.isMultiEpList()) {
					PageNav pageNav = source.pageNav();
					String pageUrl = anime.url;
					Element current = doc.selectFirst(pageNav.current);
					if (current == null) {
						listener.loaded(generateEpList(source, elements), new Result());
						return;
					}
					int currentId = Integer.parseInt(current.text());
					int lastId;
					Element last = doc.selectFirst(pageNav.last);
					if (last == null) last = doc.select(pageNav.lastAltr).last();
					if (last == null || current.text().equals(last.text())) {
						// Give up, Can't find more pages
						listener.loaded(generateEpList(source, elements), new Result());
						return;
					} else {
						Document tmp = Jsoup.parse(new URL(last.attr("href")), 60000);
						String txt = tmp.selectFirst(pageNav.current).text();
						lastId = Integer.parseInt(txt);
					}

					List<Episode> episodes = new ArrayList<>();
					for (int i = currentId; i <= lastId; i++) {
						doc = Jsoup.parse(new URL(pageUrl + String.format(pageNav.pageFormat, i)), 60000);
						elements = doc.select(source.epRegex());
						if (elements.isEmpty() && source.epRegexAlt() != null) {
							elements = doc.select(source.epRegexAlt());
						}
						elements.forEach(element -> episodes.add(new Episode(source, element)));
					}
					listener.loaded(new EpisodeList(1, 1, episodes), new Result());
				} else {
					listener.loaded(generateEpList(source, elements), new Result());
				}
			} catch (IOException e) {
				listener.loaded(null, new Result(e));
			}
		}).start();

	}

	static void listEpisodes(Anime anime, int page, EpisodeListListener listener) {
		new Thread(() -> {
			try {
				Source source = anime.source;
				if (source.isMultiEpList()) {
					Document doc = Jsoup.parse(new URL(anime.url + String.format(source.pageNav().pageFormat, page)),
							60000);
					List<Episode> episodes = new ArrayList<>();
					Elements elements = doc.select(source.epRegex());
					if (elements.isEmpty() && source.epRegexAlt() != null) {
						elements = doc.select(source.epRegexAlt());
					}
					if (elements.isEmpty()) listener.loaded(null, new Result(new NoSuchListException(anime, page)));
					elements.forEach(element -> episodes.add(new Episode(source, element)));
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

	static String getPosterUrl(Anime anime) throws IOException {
		return anime.getDoc().select(anime.source.posterRegex()).first().attr("src");
	}

	static String generateVideoUrl(Episode episode) throws IOException {
		Document doc = Jsoup.parse(new URL(episode.episodeUrl), 60000);
		System.out.println(doc.toString());
		Pattern pattern = Pattern.compile(episode.source.vidRegex());
		Matcher matcher = pattern.matcher(doc.data());
		if (matcher.find()) return matcher.group();
		return null;
	}

	private static EpisodeList generateEpList(Source source, Elements elements) {
		List<Episode> episodes = new ArrayList<>();
		elements.forEach(element -> episodes.add(new Episode(source, element)));
		return new EpisodeList(1, 1, Collections.unmodifiableList(episodes));
	}

	private static AnimeList generateAnimeList(Source source, Elements elements) {
		List<Anime> list = new ArrayList<Anime>();
		elements.forEach(element -> list.add(new Anime(source, element)));
		return new AnimeList(source, Collections.unmodifiableList(list));
	}

	public static String getName(Anime anime) throws IOException {
		return anime.getDoc().select(anime.source.nameRegex()).text();
	}
}
