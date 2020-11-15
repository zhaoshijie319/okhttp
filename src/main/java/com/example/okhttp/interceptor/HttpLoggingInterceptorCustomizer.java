package com.example.okhttp.interceptor;

import com.example.okhttp.customizer.OkHttpClientBuilderCustomizer;
import com.example.okhttp.properties.OkHttpLoggingInterceptorProperties;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Slf4j
@Configuration
@ConditionalOnClass({HttpLoggingInterceptor.class})
@EnableConfigurationProperties({OkHttpLoggingInterceptorProperties.class})
public class HttpLoggingInterceptorCustomizer implements OkHttpClientBuilderCustomizer {
    @Resource
    private OkHttpLoggingInterceptorProperties properties;

    public void customize(OkHttpClient.Builder builder) {
        builder.addInterceptor(this.properties.buildInterceptor(log));
    }
}
