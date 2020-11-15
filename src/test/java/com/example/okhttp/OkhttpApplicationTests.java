package com.example.okhttp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@SpringBootTest
class OkhttpApplicationTests {

    @Resource
    private RestTemplate restTemplate;

    @Test
    void postForEntity() {
        String url = "url";
        String requestStr = "{ \"key1\": \"value1\", \"key2\": \"key2\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, org.springframework.http.MediaType.APPLICATION_JSON_VALUE);
        ResponseEntity<String> entity = restTemplate.postForEntity(url, new HttpEntity<>(requestStr, headers), String.class);
        System.out.println(entity);
    }

    @Test
    void exchange() {
        String url = "url";
        String requestStr = "{ \"key1\": \"value1\", \"key2\": \"key2\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, org.springframework.http.MediaType.APPLICATION_JSON_VALUE);
        ResponseEntity<String> entity = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(requestStr, headers), String.class);
        System.out.println(entity);
    }

    @Test
    void getForEntity() {
        String url = "https://www.baidu.com/";
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        System.out.println(entity);
    }
}
