package com.wstone.handler;

import com.wstone.pojo.DataPojo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by shiwei on 2019/3/1.
 */
@Slf4j
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 直接转化成协议消息实体
        DataPojo myProtocolBean = (DataPojo) msg;
        log.info("----------------------------------------");
        log.info("getMsg:" +  "0x"+Integer.toHexString(myProtocolBean.getHeader()));
        log.info("length:" + myProtocolBean.getLength());
        log.info("date  :" + myProtocolBean.getData());
        log.info("----------------------------------------");
        ReferenceCountUtil.release(msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        DataPojo dataPojo = new DataPojo((short)0x6868,(short)4,2.4f);
        ctx.channel().writeAndFlush(dataPojo);

    }
}
