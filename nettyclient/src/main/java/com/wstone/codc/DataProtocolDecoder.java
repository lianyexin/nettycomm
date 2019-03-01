package com.wstone.codc;

import com.wstone.pojo.DataPojo;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by shiwei on 2019/3/1.
 */
@Slf4j
public class DataProtocolDecoder extends LengthFieldBasedFrameDecoder {

    public DataProtocolDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment,
                                 int initialBytesToStrip, boolean failFast) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip, failFast);
    }


    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        //在这里调用父类的方法,实现指得到想要的部分,我在这里全部都要,也可以只要body部分
        in = (ByteBuf) super.decode(ctx,in);

        if(in == null){
            return null;
        }
        //if(in.readableBytes()<HEADER_SIZE){ throw new Exception("字节数不足"); }
        //读取报头字段
        int header = in.readUnsignedShort();

        if(header == 0x6868) {
            log.info("接收到新数据，开始解析");
        }else {
            log.info("无法识别数据");
        }
        //读取length字段
        int length = in.readUnsignedShort();
        // 处理数据
        float data = in.readFloat();

        DataPojo dataPojo = new DataPojo((short)header,(short)length,data);

        return dataPojo;

    }


}
