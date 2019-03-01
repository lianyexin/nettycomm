package com.wstone.server;

import com.wstone.codc.DataProtocolDecoder;
import com.wstone.codc.DataProtocolEncoder;
import com.wstone.handler.ServerInHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * Created by shiwei on 2019/3/1.
 */
@Component
@Slf4j
public class Server {

    private final int MAX_FRAME_LENGTH = 1024 * 1024;  //最大长度
    private final int LENGTH_FIELD_LENGTH = 2;  //长度字段所占的字节数
    private final int LENGTH_FIELD_OFFSET = 2;  //长度偏移
    private final int LENGTH_ADJUSTMENT = 0;
    private final int INITIAL_BYTES_TO_STRIP = 0;

    public void start(int port) {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port));

            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {

                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new DataProtocolEncoder());
                    ch.pipeline().addLast(new DataProtocolDecoder(MAX_FRAME_LENGTH, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH, LENGTH_ADJUSTMENT, INITIAL_BYTES_TO_STRIP, false));
                    ch.pipeline().addLast(new ServerInHandler());
                }
            }).option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture future = serverBootstrap.bind(port).sync();
            log.info("Server start listen at " + port);
            future.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            log.info("CLIENT DISCONNECTION");
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }


}
