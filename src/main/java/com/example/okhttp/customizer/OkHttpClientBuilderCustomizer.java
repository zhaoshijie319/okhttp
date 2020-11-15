package com.example.okhttp.customizer;

import okhttp3.OkHttpClient;

@FunctionalInterface
public interface OkHttpClientBuilderCustomizer {
    void customize(OkHttpClient.Builder builder);
}
