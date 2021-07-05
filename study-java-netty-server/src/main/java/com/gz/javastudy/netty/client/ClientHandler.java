package com.gz.javastudy.netty.client;

import com.alibaba.fastjson.JSONObject;
import com.gz.javastudy.netty.asyn.RequestFuture;
import com.gz.javastudy.netty.asyn.Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		//读取服务器返回的响应结果，并转换成Response对象，
		//由于经过了StringDecoder解码器，因此msg为String类型
		Response response = JSONObject.parseObject(msg.toString(), Response.class);
		RequestFuture.received(response);
		//设置响应结果，并唤醒主线程
//		promise.setSuccess(response);
	}
	
}
