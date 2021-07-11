package com.gz.javastudy.netty.client;

import java.nio.charset.Charset;

import com.alibaba.fastjson.JSONObject;
import com.gz.javastudy.netty.asyn.RequestFuture;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class NettyClient {
    //开启一个线程组
    public static EventLoopGroup group = new NioEventLoopGroup(100);

    public static Bootstrap getBootstrap() {
        //客户端启动辅助类
        Bootstrap bootstrap = new Bootstrap();
        //设置socket通道
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.group(group);
        //设置内存分配器
        bootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
        final ClientHandler handler = new ClientHandler();
        //把promise对象赋给handler，用于获取返回服务器响应结果
//		handler.setPromise(promise);
        //把handler加入到管道中
        bootstrap.handler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel ch)
                    throws Exception {
                //新增，前缀为4个字节大小的int类型作为长度的解码器
                //第一个参数是包最大大小，第二个参数是长度值偏移量，
                // 由于编码时长度值在最前面，无偏移，此处设置为0
                //第三个参数长度值占用的字节数，
                //第四个参数是长度值的调节，假如请求包的大小是20个字节，
                // 长度值没包含本身的话应该是20，假如长度值包含了本身就是24，
                // 需要调整4个字节
                //第五个参数,解析时候需要跳过的字节长,此处为4，跳过长度值字节数
                ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,
                        0, 4, 0, 4));
                //把接收到的Bytebuf数据包转换成String
                ch.pipeline().addLast(new StringDecoder());
                //业务逻辑处理handler
                ch.pipeline().addLast(handler);
                //在消息体前面新增4个字节的长度值,第一个参数长度值字节数大小，
                //第二个参数长度值是否要包含长度值本身大小
                ch.pipeline().addLast(new LengthFieldPrepender(4, false));
                //把字符串消息转换成ByteBuf
                ch.pipeline().addLast(new StringEncoder(Charset.forName("utf-8")));
            }
        });
        return bootstrap;
    }


    public static Object sendRequest(RequestFuture request) throws Exception {
        try {
            String requestStr = JSONObject.toJSONString(request);

//			ChannelFuture future = ChannelFutureManager.get();
            ChannelFuture future = getBootstrap().connect("127.0.0.1", 8080).sync();
            future.channel().writeAndFlush(requestStr);
            //同步等待响应结果，当promise有值了才会继续往下执行
            Object result = request.get();
			System.out.println("A:"+result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void main(String[] args) throws Exception {
        NettyClient client = new NettyClient();
        for (int i = 0; i < 100; i++) {
            RequestFuture request = new RequestFuture();
            //设置参数
            request.setRequest("" + i);
            Object result = client.sendRequest(request);
            System.out.println(result);
        }
    }
}
