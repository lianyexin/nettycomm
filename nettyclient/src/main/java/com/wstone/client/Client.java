package com.wstone.client;

import com.wstone.codc.DataProtocolDecoder;
import com.wstone.codc.DataProtocolEncoder;
import com.wstone.handler.ClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by shiwei on 2019/3/1.
 */
@Slf4j
@Component
public class Client {

    private final int MAX_FRAME_LENGTH = 1024 * 1024;  //最大长度
    private final int LENGTH_FIELD_LENGTH = 2;  //长度字段所占的字节数
    private final int LENGTH_FIELD_OFFSET = 2;  //长度偏移
    private final int LENGTH_ADJUSTMENT = 0;
    private final int INITIAL_BYTES_TO_STRIP = 0;

    public void start(String ip,int port){
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bs = new Bootstrap();
            bs.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new DataProtocolDecoder(MAX_FRAME_LENGTH, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH, LENGTH_ADJUSTMENT, INITIAL_BYTES_TO_STRIP, false));
                            ch.pipeline().addLast(new DataProtocolEncoder());
                            ch.pipeline().addLast(new ClientHandler());

                        }
                    });

            ChannelFuture future = bs.connect(ip,port).sync();

            future.channel().closeFuture().sync();


        }catch (InterruptedException e) {
            log.info("CONNECTION DISCONNECTION");
        } finally {
            group.shutdownGracefully();
        }
    }
}
