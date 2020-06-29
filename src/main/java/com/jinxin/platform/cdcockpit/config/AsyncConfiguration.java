package com.jinxin.platform.cdcockpit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

/**
 * @author Huang LingSong
 * 2019-10-09 9:19
 */
@Configuration
@EnableAsync
public class AsyncConfiguration {
    @Bean("cdcockpitTaskExecutor")
    public ExecutorService asyncExecutor() {
        return new ThreadPoolExecutor(2, 5, 60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(5), new ThreadPoolExecutor.AbortPolicy());
    }
}
