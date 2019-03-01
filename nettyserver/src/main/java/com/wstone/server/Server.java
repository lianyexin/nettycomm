package com.wstone.server;

import org.springframework.stereotype.Component;

/**
 * Created by shiwei on 2019/3/1.
 */
@Component
public class Server {

    private final int MAX_FRAME_LENGTH = 1024 * 1024;  //最大长度
    private final int LENGTH_FIELD_LENGTH = 2;  //长度字段所占的字节数
    private final int LENGTH_FIELD_OFFSET = 1;  //长度偏移
    private final int LENGTH_ADJUSTMENT = 0;
    private final int INITIAL_BYTES_TO_STRIP = 0;

    public void start(int port){



    }


}
