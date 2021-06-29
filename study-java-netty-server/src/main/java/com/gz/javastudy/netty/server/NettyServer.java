package com.gz.javastudy.netty.server;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {
    public static  String SERVER_PATH = "/netty";
    public static void main(String[] args) throws Exception {
        start();
    }
    public static void start() {
        /**
         * 新建两个线程组，boss线程组启动一条线程监听OP_ACCEPT事件，
         * worker线程组默认启动cpu核数*2的线程,监听客户端连接
         * 的OP_READ和OP_WRITE事件,处理IO事件
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // ServerBootstrap为netty服务启动辅助类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup);
            // 设置TCP socket通道为NioServerSocketChannel，
            // 假如是UDP通信的话，则设置为DatagramChannel
            serverBootstrap.channel(NioServerSocketChannel.class);
            //设置TCP参数
            serverBootstrap.option(ChannelOption.SO_BACKLOG,128)
            /**
             * 当有客户端注册写事件时，初始化Handle
             * 并将Handler加入管道中
             */
            .childHandler(new ChannelInitializer<SocketChannel>(){

                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new ServerHandler());
                }
            });
            //同步绑定端口
            ChannelFuture future = serverBootstrap.bind(8080).sync();
            //阻塞主线程，直到Socket通道关闭
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 最终关闭线程组
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}

