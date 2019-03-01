package com.wstone.handler;

import com.wstone.pojo.DataPojo;
import com.wstone.utils.Params;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * Created by shiwei on 2019/3/1.
 */
@Slf4j
public class ServerInHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        DataPojo myProtocolBean = (DataPojo) msg; // 直接转化成协议消息实体
        log.info("----------------------------------------");
        log.info("getMsg:" +  "0x"+Integer.toHexString(myProtocolBean.getHeader()));
        log.info("length:" + myProtocolBean.getLength());
        log.info("date  :" + myProtocolBean.getData());
        log.info("----------------------------------------");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);

        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String ip = insocket.getAddress().getHostAddress();
        log.info("client["+ip+"] is connected");
        Params.map.put(ip, ctx.channel());

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String ip = insocket.getAddress().getHostAddress();
        log.info("client["+ip+"] is disconnected");
    }
}
