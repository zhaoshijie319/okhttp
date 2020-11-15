package com.example.okhttp.properties;

import lombok.Getter;
import lombok.Setter;
import okhttp3.logging.HttpLoggingInterceptor;
import org.slf4j.Logger;
import org.slf4j.event.Level;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "spring.ok-client.http-logging")
public class OkHttpLoggingInterceptorProperties {
    private Level level;
    private HttpLoggingInterceptor.Level httpLevel;

    public OkHttpLoggingInterceptorProperties() {
        this.level = Level.DEBUG;
        this.httpLevel = HttpLoggingInterceptor.Level.NONE;
    }

    public HttpLoggingInterceptor buildInterceptor(final Logger logger) {
        Level level = this.getLevel();
        HttpLoggingInterceptor.Logger interceptorLogger;
        switch(level) {
            case TRACE:
                interceptorLogger = logger::trace;
                break;
            case ERROR:
                interceptorLogger = logger::error;
                break;
            case WARN:
                interceptorLogger = logger::warn;
                break;
            case INFO:
                interceptorLogger = logger::info;
                break;
            case DEBUG:
                interceptorLogger = (message) -> {
                    if (logger.isDebugEnabled()) {
                        logger.debug(message);
                    }
                };
                break;
            default:
                throw new RuntimeException("create http logging interceptor logger error.");
        }

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(interceptorLogger);
        interceptor.setLevel(this.getHttpLevel());
        return interceptor;
    }
}

