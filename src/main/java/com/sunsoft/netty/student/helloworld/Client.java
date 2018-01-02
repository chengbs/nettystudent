package com.sunsoft.netty.student.helloworld;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.IOException;

public class Client {

    public static void main(String[] args) throws InterruptedException, IOException {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) {
                        socketChannel.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                        socketChannel.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
//                        socketChannel.pipeline().addLast(new ReadTimeoutHandler(5)); // 5秒后未与服务器通信，则断开连接。
                        socketChannel.pipeline().addLast(new ClientHandler());
                    }
                });
        ChannelFuture future = bootstrap.connect("127.0.0.1", 8379).sync();

        RequestInfo request = new RequestInfo();
        request.setIp("127.0.0.1");
        request.setData("数据信息:sdfahajkshfkjhasdkjhfkjqhewiurfbjkfsdgq3rkfldfmnqwjklh结束");

        byte[] gzipData = GzipUtils.gzip(request.getData().getBytes());
        request.setZipData(gzipData);
        future.channel().writeAndFlush(request);

//        future.channel().writeAndFlush(Unpooled.copiedBuffer("777".getBytes()));
        future.channel().closeFuture().sync();
        workerGroup.shutdownGracefully();
    }

}