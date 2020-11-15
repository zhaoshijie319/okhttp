package com.example.okhttp.customizer;

import com.example.okhttp.config.OkClientConfig;
import okhttp3.OkHttpClient;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Import({OkClientConfig.class})
@Configuration
public class RestTemplateWrapperOkHttpClientCustomizer implements RestTemplateCustomizer {
    @Resource
    private OkHttpClient okHttpClient;

    @Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.setRequestFactory(new OkHttp3ClientHttpRequestFactory(okHttpClient));
    }
}
