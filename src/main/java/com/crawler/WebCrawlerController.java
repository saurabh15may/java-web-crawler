package com.crawler;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crawler.model.PageTree;
import com.google.gson.Gson;

@RestController
public class WebCrawlerController {

	@ExceptionHandler({ IllegalArgumentException.class, NumberFormatException.class })
	void handleException(HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value());
	}

	@RequestMapping("/crawl")
	public String getCrawlTree(@RequestParam(value = "url") String url, @RequestParam(value = "depth") String depth,
			HttpServletResponse response) {

		if (isURLParameterEmptyOrNull(url)) {
			throw new IllegalArgumentException("The 'url' parameter must not be null or empty");
		}

		if (!isURLValid(url)) {
			throw new IllegalArgumentException("The 'url' parameter is Invalid");
		}

		if (isDepthParameterEmptyOrNull(depth)) {
			throw new IllegalArgumentException("The 'depth' parameter must not be null or empty");
		}

		if (!isParameterParsable(depth)) {
			throw new NumberFormatException("The 'depth' parameter should be a number");
		}

		PageTree responseData = null;

		try {
			responseData = new WebCrawler().getCrawlResponse(url, Integer.parseInt(depth));
		} catch (Exception e) {
			throw new IllegalArgumentException("Bad Request");
		}

		return getJSON(responseData);
	}

	public static String getJSON(PageTree node) {
		return new Gson().toJson(node);
	}

	public static boolean isURLValid(String url) {
		UrlValidator urlValidator = new UrlValidator();
		return urlValidator.isValid(url);
	}

	public static boolean isURLParameterEmptyOrNull(String url) {
		return (url == null || url.isEmpty());
	}

	public static boolean isDepthParameterEmptyOrNull(String depth) {
		return (depth == null || depth.isEmpty());
	}

	public static boolean isParameterParsable(String param) {
		try {
			Integer.parseInt(param);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}