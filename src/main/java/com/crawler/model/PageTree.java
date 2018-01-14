package com.crawler.model;

import java.util.List;

public class PageTree {

	String url;
	String title;
	List<PageTree> child;

	public PageTree() {
	}

	public PageTree(String url, String title, List<PageTree> child) {
		super();
		this.url = url;
		this.title = title;
		this.child = child;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<PageTree> getChild() {
		return child;
	}

	public void setChild(List<PageTree> child) {
		this.child = child;
	}

}
