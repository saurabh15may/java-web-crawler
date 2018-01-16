package com.crawler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.crawler.model.PageTree;

public class WebCrawlerTest {

	@Test
	public void isAlreadyCrawledIsValid() {
		WebCrawler webcrawler = new WebCrawler();
		webcrawler.getCrawURLs().add("https://www.google.com");

		assertTrue(webcrawler.isAlreadyCrawled("https://www.google.com"));

	}

	@Test
	public void isAlreadyCrawledIsInValid() {
		WebCrawler webcrawler = new WebCrawler();
		webcrawler.getCrawURLs().add("https://www.facebook.com");

		assertFalse(webcrawler.isAlreadyCrawled("https://www.google.com"));

	}

	@Test
	public void isCrawlDepthValidIsValid() {
		WebCrawler webcrawler = new WebCrawler();
		int CRAWL_DEPTH = 3;

		assertTrue(webcrawler.isCrawlDepthValid(3, CRAWL_DEPTH));

	}

	@Test
	public void isCrawlDepthValidInValid() {
		WebCrawler webcrawler = new WebCrawler();
		int CRAWL_DEPTH = 3;

		assertFalse(webcrawler.isCrawlDepthValid(4, CRAWL_DEPTH));

	}

	@Test
	public void crawlResponseIsValid() {
		WebCrawler webcrawler = new WebCrawler();
		String URL = "https://www.google.com";

		assertEquals(webcrawler.crawl(URL, new PageTree(), 1, 1, null).getClass(), new PageTree().getClass());

	}

}
