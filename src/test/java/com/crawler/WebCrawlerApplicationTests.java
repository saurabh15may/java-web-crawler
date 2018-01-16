package com.crawler;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.crawler.model.PageTree;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebCrawlerApplication.class)
@WebAppConfiguration
public class WebCrawlerApplicationTests {

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void crawlAPIIsSuccess() throws Exception {
		mockMvc.perform(get("/crawl?url=https://www.google.com&depth=1")).andExpect(status().isOk());
	}

	@Test
	public void crawlAPIWithoutAnyParamIsBadRequest() throws Exception {
		mockMvc.perform(get("/crawl")).andExpect(status().isBadRequest());
	}

	@Test
	public void crawlAPIWithoutDepthParamIsBadRequest() throws Exception {
		mockMvc.perform(get("/crawl?url=https://www.google.com")).andExpect(status().isBadRequest());
	}

	@Test
	public void crawlAPIWithoutURLParamIsBadRequest() throws Exception {
		mockMvc.perform(get("/crawl?depth=1")).andExpect(status().isBadRequest());
	}

	@Test
	public void crawlResonseIsValid() throws Exception {
		mockMvc.perform(get("/crawl?url=https://www.google.com&depth=1").content(new Gson().toJson(new PageTree()))
				.contentType(contentType)).andExpect(status().isOk());
	}

}