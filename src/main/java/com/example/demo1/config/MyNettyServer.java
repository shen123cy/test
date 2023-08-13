package com.example.demo1.config;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

import javax.annotation.PostConstruct;

//@Configuration
public class MyNettyServer {


    @PostConstruct
    public void startNettyServer() {
//        String hostAddress = InetAddress.getLocalHost().getHostAddress();
        try {
            initServer();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void initServer() throws InterruptedException {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap sb = new ServerBootstrap();
            sb.option(ChannelOption.SO_BACKLOG, 1024);
            sb.group(group, bossGroup)//设置时间循环对象，前者用来处理accept事件，后者用于处理已经建立的连接的io
                    .channel(NioServerSocketChannel.class)// 指定使用的channel ;  用它来建立新accept的连接，用于构造serversocketchannel的工厂类
                    .localAddress(8002)//绑定监听端口
                    .childHandler(new ChannelInitializer<SocketChannel>() {//绑定客户端连接时候触发操作
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            System.out.println("收到新连接:" + ch.localAddress());
                            ch.pipeline().addLast("http-codec", new HttpServerCodec());//websocket协议本身是基于http协议的，所以这边也要使用http解编码器
                            //以块的方式来写的处理器
                            ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());//加入对写大数据流的支持
                            ch.pipeline().addLast("aggregator", new HttpObjectAggregator(8192));//对HttpMessage进行聚合，聚合成FullHttpRequest或FullHttpResponse
                            ch.pipeline().addLast(new WebSocketServerProtocolHandler("/ws", "WebSocket", true, 65536 * 10));
                            ch.pipeline().addLast(new MyWebSocketHandler());
                        }
                    });
            //bind方法会创建一个servverchannel，并且会将当前的channel注册到eventloop上面
            //会为其绑定本地端口，并对其进行初始化，为其的pipeline加一些默认的handler
            ChannelFuture cf = sb.bind(8002).sync(); // 服务器异步创建绑定
            System.out.println(MyNettyServer.class + " 启动正在监听： " + cf.channel().localAddress());
            cf.channel().closeFuture().sync(); // 关闭服务器通道
        } finally {
            group.shutdownGracefully().sync(); // 释放线程池资源
            bossGroup.shutdownGracefully().sync();
        }
    }
}
