package com.wstone.handler;

import com.wstone.pojo.DataPojo;

import com.wstone.utils.Params;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by shiwei on 2019/3/1.
 */
@Slf4j
public class SendThread extends Thread {


    public void run() {
        try {

            int i = 0;
            Channel channel = null;
            while (true) {
                Thread.sleep(10 * 1000);
                log.info("RUN METHODS:"+i);

                if(Params.map.containsKey("127.0.0.1")) {
                    channel = Params.map.get("127.0.0.1");
                }else{
                    continue;
                }
                if(channel == null || !channel.isActive()){
                    continue;
                }

                DataPojo dataPojo = new DataPojo((short) 0x6868, (short) 4, (++i));
                channel.writeAndFlush(dataPojo);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
