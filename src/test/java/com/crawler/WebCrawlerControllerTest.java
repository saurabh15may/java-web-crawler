package com.crawler;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class WebCrawlerControllerTest {

	@Test
	public void URLParameterIsNotNullOrEmpty() {
		assertFalse(WebCrawlerController.isURLParameterEmptyOrNull("https://www.google.com"));
		assertFalse(WebCrawlerController.isURLParameterEmptyOrNull("www"));
	}

	@Test
	public void URLParameterIsNullOrEmpty() {
		assertTrue(WebCrawlerController.isURLParameterEmptyOrNull(null));
		assertTrue(WebCrawlerController.isURLParameterEmptyOrNull(""));
	}

	@Test
	public void URLIsValid() {
		assertTrue(WebCrawlerController.isURLValid("https://www.google.com"));
		assertTrue(WebCrawlerController.isURLValid("http://www.google.com"));
		assertTrue(WebCrawlerController.isURLValid("http://google.com"));
	}

	@Test
	public void URLIsInvalid() {
		assertFalse(WebCrawlerController.isURLValid("abc.com"));
		assertFalse(WebCrawlerController.isURLValid("abc.xyz"));
	}

	@Test
	public void DepthParameterIsNotNullOrEmpty() {
		assertFalse(WebCrawlerController.isDepthParameterEmptyOrNull("5"));
		assertFalse(WebCrawlerController.isDepthParameterEmptyOrNull("A"));
	}

	@Test
	public void DepthParameterIsNullOrEmpty() {
		assertTrue(WebCrawlerController.isDepthParameterEmptyOrNull(null));
		assertTrue(WebCrawlerController.isDepthParameterEmptyOrNull(""));
	}

	@Test
	public void ParameterIsParsable() {
		assertTrue(WebCrawlerController.isParameterParsable("3"));
	}

	@Test
	public void ParameterIsNotParsable() {
		assertFalse(WebCrawlerController.isParameterParsable("xy"));
		assertFalse(WebCrawlerController.isParameterParsable("ab"));
	}
}
