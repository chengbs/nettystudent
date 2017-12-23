package com.sunsoft.netty.student.ssl.adjectivesecure.server;


import com.sunsoft.netty.student.ssl.adjectivesecure.handler.CertParseHandler;
import com.sunsoft.netty.student.ssl.adjectivesecure.handler.ServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;

/**
 * Creates a newly configured {@link ChannelPipeline} for a new channel.
 */
public class ServerInitializer extends ChannelInitializer<SocketChannel> {

    private final javax.net.ssl.SSLContext sslCtx;

    public ServerInitializer(javax.net.ssl.SSLContext sslCtx) {
        this.sslCtx = sslCtx;
    }

    @Override
    public void initChannel(SocketChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();

        SSLEngine sslEngine = sslCtx.createSSLEngine();
        sslEngine.setUseClientMode(false);
        sslEngine.setNeedClientAuth(true);
        pipeline.addLast(new SslHandler(sslEngine));
        pipeline.addLast(new CertParseHandler());

        // On top of the SSL handler, add the text line codec.
        pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        pipeline.addLast(new StringDecoder());
        pipeline.addLast(new StringEncoder());

        // and then business logic.
        pipeline.addLast(new ServerHandler());
    }
}
