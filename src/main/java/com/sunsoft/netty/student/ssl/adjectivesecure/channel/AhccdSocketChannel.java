package com.sunsoft.netty.student.ssl.adjectivesecure.channel;

import io.netty.channel.Channel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.nio.channels.SocketChannel;

public class AhccdSocketChannel extends NioSocketChannel {
    private String customerId;

    public AhccdSocketChannel(Channel parent, SocketChannel socket) {
        super(parent, socket);
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
