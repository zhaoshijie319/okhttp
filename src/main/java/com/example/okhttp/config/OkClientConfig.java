package com.example.okhttp.config;

import com.example.okhttp.customizer.OkHttpClientBuilderCustomizer;
import com.example.okhttp.properties.OkHttpClientProperties;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@ConditionalOnClass({OkHttpClient.Builder.class})
@EnableConfigurationProperties({OkHttpClientProperties.class})
public class OkClientConfig {

    @Bean
    @ConditionalOnMissingBean
    public OkHttpClient.Builder okHttpClientBuilder(OkHttpClientProperties properties, ObjectProvider<OkHttpClientBuilderCustomizer> builderCustomizers) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(properties.getReadTimeout().toMillis(), TimeUnit.MILLISECONDS)
                .connectTimeout(properties.getConnectTimeout().toMillis(), TimeUnit.MILLISECONDS)
                .writeTimeout(properties.getWriteTimeout().toMillis(), TimeUnit.MILLISECONDS)
                .connectionPool(properties.buildConnectPool());
        Optional.ofNullable(properties.buildCache()).ifPresent(builder::cache);
        builderCustomizers.orderedStream().forEach((customizer) -> {
            customizer.customize(builder);
        });
        return builder;
    }

    @Bean
    @ConditionalOnMissingBean
    public OkHttpClient okHttpClient(OkHttpClient.Builder okHttpClientBuilder) {
        return okHttpClientBuilder.build();
    }
}
