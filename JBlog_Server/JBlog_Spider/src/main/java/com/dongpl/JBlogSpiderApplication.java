package com.dongpl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import us.codecraft.webmagic.scheduler.RedisScheduler;
import com.dongpl.utils.IdWorker;

@SpringBootApplication
@EnableScheduling
public class JBlogSpiderApplication {

    @Value("${spring.redis.host}")
    private String redis_host;

    public static void main(String[] args) {
        SpringApplication.run(JBlogSpiderApplication.class, args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1,1);
    }

    @Bean
    public RedisScheduler redisScheduler() {
        return new RedisScheduler(redis_host);
    }

}
