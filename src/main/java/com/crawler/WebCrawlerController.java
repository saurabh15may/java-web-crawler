package com.crawler;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebCrawlerController {

    @RequestMapping("/crawl")
    public String greeting(@RequestParam(value="url", defaultValue="https://www.google.com") String url) {
        
    	return WebCrawler.getCrawlResponse(url);
    }
}