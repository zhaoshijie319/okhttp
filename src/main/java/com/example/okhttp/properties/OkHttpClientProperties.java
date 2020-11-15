package com.example.okhttp.properties;

import lombok.Getter;
import lombok.Setter;
import okhttp3.ConnectionPool;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

import java.io.File;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
@ConfigurationProperties(prefix = "spring.ok-client")
public class OkHttpClientProperties {
    private static final Duration DEFAULT_DURATION = Duration.ofMillis(5000L);
    private Duration readTimeout;
    private Duration callTimeout;
    private Duration connectTimeout;
    private Duration writeTimeout;
    private final OkHttpClientProperties.Cache cache;
    private final OkHttpClientProperties.Pool pool;

    public OkHttpClientProperties() {
        this.readTimeout = DEFAULT_DURATION;
        this.callTimeout = DEFAULT_DURATION;
        this.connectTimeout = DEFAULT_DURATION;
        this.writeTimeout = DEFAULT_DURATION;
        this.cache = new OkHttpClientProperties.Cache();
        this.pool = new OkHttpClientProperties.Pool();
    }

    public okhttp3.Cache buildCache() {
        if (StringUtils.hasText(this.cache.path)) {
            File file = new File(this.cache.path);
            return new okhttp3.Cache(file, this.cache.maxSize);
        } else {
            return null;
        }
    }

    public ConnectionPool buildConnectPool() {
        long seconds = this.pool.keepAlive.toMillis();
        return new ConnectionPool(this.pool.maxIdle, seconds, TimeUnit.MILLISECONDS);
    }

    public static class Pool {
        private int maxIdle = 10;
        private Duration keepAlive = Duration.ofMinutes(15L);
    }

    public static class Cache {
        private String path;
        private long maxSize;
    }
}
