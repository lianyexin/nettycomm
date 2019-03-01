package com.wstone.codc;

import com.wstone.pojo.DataPojo;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by shiwei on 2019/3/1.
 */
public class DataProtocolEncoder extends MessageToByteEncoder {


    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        DataPojo dataPojo = (DataPojo) o;
        byteBuf.writeShort(dataPojo.getHeader());
        byteBuf.writeShort(dataPojo.getLength());
        byteBuf.writeFloat(dataPojo.getData());
    }
}
