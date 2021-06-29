package com.gz.javastudy.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

//Sharable注解表示此Handler对所有Channel共享，无状态，注意多线程并发
@ChannelHandler.Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {
	/**
	 * 读取客户端发送的数据
	 */
	 @Override
	 public void channelRead(ChannelHandlerContext ctx, Object msg) {
		if(msg instanceof ByteBuf){
			System.out.println(((ByteBuf) msg).toString(Charset.defaultCharset()));
		}
		//ctx.channel().writeAndFlush("msg has recived!");
	 }


}
