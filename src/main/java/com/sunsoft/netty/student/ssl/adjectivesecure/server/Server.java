package com.sunsoft.netty.student.ssl.adjectivesecure.server;

import com.sunsoft.netty.student.ssl.adjectivesecure.channel.AhccdServerSocketChannel;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import javax.net.ssl.SSLContext;

public final class Server {

    static final int PORT = Integer.parseInt(System.getProperty("port", "8090"));

    public static void main(String[] args) throws Exception {
        SSLContext sslCtx = SslContextFactory.getServerContext();

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup).channel(AhccdServerSocketChannel.class).handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ServerInitializer(sslCtx));

            b.bind(PORT).sync().channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
