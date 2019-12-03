package com.shl.evet.event;

import com.shl.evet.module.User;
import org.springframework.context.ApplicationEvent;

import java.util.concurrent.TimeUnit;

/**
 * @author songhonglei
 * @version 1.0
 * @description 用户注册事件
 * @date 2019/11/30
 */
public class UserRegisterEvent extends ApplicationEvent {

    private User user;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public UserRegisterEvent(Object source, User user) throws InterruptedException {
        super(source);
        TimeUnit.SECONDS.sleep(3);
        this.user = user;
    }
}
