package com.crawler;

import java.util.ArrayList;
import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.crawler.model.PageTree;

public class WebCrawler {
	private HashSet<String> crawURLs;

	public WebCrawler() {
		crawURLs = new HashSet<>();
	}

	public HashSet<String> getCrawURLs() {
		return crawURLs;
	}

	public boolean isAlreadyCrawled(String URL) {
		return crawURLs.contains(URL);
	}

	public boolean isCrawlDepthValid(int currentDepth, final int CRAWL_DEPTH) {
		return currentDepth <= CRAWL_DEPTH;
	}

	public PageTree getCrawlResponse(String URL, final int CRAWL_DEPTH) {
		PageTree root = new PageTree();
		root.setChild(new ArrayList<PageTree>());

		return new WebCrawler().crawl(URL, root, 1, CRAWL_DEPTH, null);

	}

	public PageTree crawl(String URL, PageTree root, int currentDepth, final int CRAWL_DEPTH, Document document) {
		if (!isAlreadyCrawled(URL) && isCrawlDepthValid(currentDepth, CRAWL_DEPTH)) {
			try {
				currentDepth++;
				crawURLs.add(URL);

				if (document == null) {
					document = Jsoup.connect(URL).get();
					root.setUrl(URL);
					root.setTitle(document.title());
				}

				Elements linksOnPage = document.select("a[href]");

				for (Element page : linksOnPage) {
					String pUrl = page.attr("abs:href");

					if (!isAlreadyCrawled(pUrl)) {
						Document doc = Jsoup.connect(pUrl).get();
						PageTree childNode = new PageTree(pUrl, doc.title(), new ArrayList<PageTree>());

						root.getChild().add(childNode);
						// Recursive crawling till the crawl depth
						crawl(pUrl, childNode, currentDepth, CRAWL_DEPTH, doc);
					}
				}

			} catch (Exception e) {
				System.err.println(URL + " - " + e.getMessage());
			}
		}
		return root;

	}

}