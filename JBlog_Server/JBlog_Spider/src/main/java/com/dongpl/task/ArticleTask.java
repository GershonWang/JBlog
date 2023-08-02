package com.dongpl.task;

import com.dongpl.processor.ArticleTxtPipeline;
import com.dongpl.processor.ArticleProcessor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.RedisScheduler;

import javax.annotation.Resource;

@Component
public class ArticleTask {

    @Resource
    private ArticleProcessor articleProcessor;

    @Resource
    private ArticleTxtPipeline articleTxtPipeline;

    @Resource
    private RedisScheduler redisScheduler;

    /**
     * 爬取ai数据
     */
    @Scheduled(cron="0 * * * * ?")
    public void aiTask(){
        System.out.println("爬取AI文章");
        Spider spider = Spider.create(articleProcessor);
        spider.addUrl("https://blog.csdn.net/nav/ai");
        articleTxtPipeline.setChannelId("ai");
        spider.addPipeline(articleTxtPipeline);
        spider.setScheduler(redisScheduler);
        spider.start();
    }

    /**
     * 爬取db数据
     */
    @Scheduled(cron="20 17 11 * * ?")
    public void dbTask(){
        System.out.println("爬取DB文章");
        Spider spider = Spider.create(articleProcessor);
        spider.addUrl("https://blog.csdn.net/nav/db");
        articleTxtPipeline.setChannelId("db");
        spider.addPipeline(articleTxtPipeline);
        spider.setScheduler(redisScheduler);
        spider.start();
    }

    /**
     * 爬取web数据
     */
    @Scheduled(cron="20 27 11 * * ?")
    public void webTask(){
        System.out.println("爬取WEB文章");
        Spider spider = Spider.create(articleProcessor);
        spider.addUrl("https://blog.csdn.net/nav/web");
        articleTxtPipeline.setChannelId("web");
        spider.addPipeline(articleTxtPipeline);
        spider.setScheduler(redisScheduler);
        spider.start();
    }

}
