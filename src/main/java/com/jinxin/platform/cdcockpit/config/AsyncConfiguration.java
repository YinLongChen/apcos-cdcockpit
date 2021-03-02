package com.jinxin.platform.cdcockpit.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(mapper);
        return converter;
    }


}
