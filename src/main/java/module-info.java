module anime_crawler_api {
	exports com.codingotaku.apis.animecrawler.exception;
	exports com.codingotaku.apis.animecrawler;
	exports com.codingotaku.apis.animecrawler.helpers;
	exports com.codingotaku.apis.animecrawler.callbacks;

	requires org.jsoup;
	opens com.codingotaku.apis.animecrawler;
}
