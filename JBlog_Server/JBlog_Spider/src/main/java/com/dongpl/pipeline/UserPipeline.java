package com.dongpl.pipeline;

import com.dongpl.dao.UserRepository;
import com.dongpl.entity.User;
import com.dongpl.utils.DownloadUtil;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;
import java.io.IOException;

@Component
public class UserPipeline implements Pipeline {

    @Resource
    private UserRepository userRepository;

    @Override
    public void process(ResultItems resultItems, Task task) {
        User user = new User();
        user.setNickname(resultItems.get("nickname"));
        String image = resultItems.get("image");//图片地址
        String fileName = image.substring(image.lastIndexOf("/")+1);
        user.setAvatar(fileName);
        userRepository.save(user);
        try {
            String userHome = System.getProperty("user.home");
            //下载图片
            DownloadUtil.download(image,fileName,userHome + "/userImage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
