package com.wstone.server;

import com.wstone.handler.SendThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by shiwei on 2019/3/1.
 */
@Component
public class ServerStarter implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private Server server;


    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
            new SendThread().start();
            server.start(51);


    }
}
