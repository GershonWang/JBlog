package com.dongpl.processor;

import com.dongpl.dao.ArticleRepository;
import com.dongpl.entity.Article;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import com.dongpl.utils.IdWorker;

import javax.annotation.Resource;

@Component
public class ArticleTxtPipeline implements Pipeline {

    @Resource
    private ArticleRepository articleRepository;

    @Resource
    private IdWorker idWorker;

    private String channelId; // 频道ID

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        String title = resultItems.get("title");
        String content= resultItems.get("content");
        Article article=new Article();
        article.setId(String.valueOf(idWorker.nextId()));
        article.setChannelId(channelId);
        article.setTitle(title);
        article.setContent(content);
        articleRepository.save(article);
    }

}
