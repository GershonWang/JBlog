package com.dongpl.task;

import com.dongpl.pipeline.UserPipeline;
import com.dongpl.processor.UserProcessor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.RedisScheduler;

import javax.annotation.Resource;

@Component
public class UserTask {

    @Resource
    private RedisScheduler redisScheduler;

    @Resource
    private UserPipeline userPipeline;

    @Resource
    private UserProcessor userProcessor;

    /**
     * 爬取用户数据
     */
    @Scheduled(cron="0 56 22 * * ?")
    public void userTask(){
        System.out.println("爬取用户");
        Spider spider = Spider.create(userProcessor);
        spider.addUrl("https://blog.csdn.net");
        spider.addPipeline(userPipeline);
        spider.setScheduler(redisScheduler);
        spider.start();
    }

}
