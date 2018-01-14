package com.crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.crawler.model.PageTree;

public class WebCrawler {
	private HashSet<String> links;
	private static final int MAX_DEPTH = 2;

	public WebCrawler() {
		links = new HashSet<>();
	}

	public void crawl(String URL, PageTree root, int depth, Document document) {
		if ((!links.contains(URL) && (depth < MAX_DEPTH))) {
			try {
				depth++;
				links.add(URL);

				if (document == null)
					document = Jsoup.connect(URL).get();

				String title = document.title();
				Elements linksOnPage = document.select("a[href]");

				root.setUrl(URL);
				root.setTitle(title);
				List<PageTree> children = root.getChild();

				for (Element page : linksOnPage) {
					String pUrl = page.attr("abs:href");
					// Document doc = Jsoup.connect(pUrl).get();
					// String pTitle = doc.title();
					String pTitle = "";

					PageTree childNode = new PageTree(pUrl, pTitle, new ArrayList<PageTree>());

					children.add(childNode);

					System.out.println(depth + " - " + pUrl);

					crawl(pUrl, childNode, depth, null);

				}
				root.setChild(children);

			} catch (IOException e) {
				System.err.println("For '" + URL + "': " + e.getMessage());
			}
		}

	}
	
	public static String getCrawlResponse(String URL) {
		PageTree root = new PageTree();
		root.setChild(new ArrayList<PageTree>());
		new WebCrawler().crawl(URL, root, 0, null);

		return getJSON(root);

	}


	/*public static void main(String[] args) {
		PageTree root = new PageTree();
		root.setChild(new ArrayList<PageTree>());
		new WebCrawler().crawl("http://www.vogella.com/", root, 0, null);

		System.out.println(getJSON(root));

	}*/

	private static String getJSON(PageTree node) {
		return new Gson().toJson(node);
	}
}