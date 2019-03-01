package com.wstone.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by shiwei on 2019/3/1.
 */
@Component
public class ClientStarter implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private Client client;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
            client.start("localhost",51);
    }
}
