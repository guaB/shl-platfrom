package com.shl.evet.service;

import com.shl.evet.event.UserRegisterEvent;
import com.shl.evet.module.User;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * @author songhonglei
 * @version 1.0
 * @description
 * @date 2019/11/30
 */
@Service
public class UserRegisterService implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;


    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public boolean register(User user) throws InterruptedException {

        //用户注册
        System.out.println("[service]用户["  + user + "]注册成功！");

        //消息发布
        applicationEventPublisher.publishEvent(new UserRegisterEvent(this, user));

        return true;
    }

}
