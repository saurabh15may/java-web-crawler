# java-web-crawler
This application is developed in Spring Boot which exposes a REST endpoint which takes a URL and Depth(Number) as a parameter and create a tree of child pages linked to the URL till the specified depth and returns it in JSON format

#### Please find the steps to build, test and run the REST API

##### Run the below command to build the application and run its test cases
>gradlew build

##### Run the below command to deploy the REST API.
>gradlew bootrun

#### In POSTMAN (Get) or Web browser, enter the below REST endpoint to test. 
It takes 2 parameter:
>"url" - Website url

>"depth" - Depth to which you want the API to Crawl the website (Number). Ideally,  for a crawl depth of 2 or 3, the API will return sufficient data (depends on website). A value greater than 3 might take time to process due to deep crawling and you might experience delay in getting the JSON response. This time depends on the size of the website.
```sh
http://localhost:8080/crawl?url=XXXX&depth=XXXX

Positive Test Scenarios
http://localhost:8080/crawl?url=https://saurabh1.com&depth=3
http://localhost:8080/crawl?url=https://www.google.com.au&depth=1

Negative Test scenarios
http://localhost:8080/crawl
http://localhost:8080/crawl?url=https://saurabh1.com
http://localhost:8080/crawl?depth=2
http://localhost:8080/crawl?url=abc.pqr
http://localhost:8080/crawl?depth=ABC
http://localhost:8080/crawl?url=https://saurabh1.com&depth=-5
http://localhost:8080/crawl?url=https://saurabh1.com&depth=100
```


