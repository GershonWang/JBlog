package com.dongpl.pipeline;

import com.dongpl.dao.ArticleRepository;
import com.dongpl.entity.Article;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;

@Component
public class ArticleTxtPipeline implements Pipeline {

    @Resource
    private ArticleRepository articleRepository;

    private String channelId; // 频道ID

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        String title = resultItems.get("title");
        String content= resultItems.get("content");
        Article article=new Article();
        article.setChannelId(channelId);
        article.setTitle(title);
        article.setContent(content);
        articleRepository.save(article);
    }

}
