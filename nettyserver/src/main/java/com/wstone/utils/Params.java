package com.wstone.utils;

import io.netty.channel.Channel;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by shiwei on 2019/3/1.
 */
public class Params {

    public static Map<String,Channel> map = new ConcurrentHashMap();

}
