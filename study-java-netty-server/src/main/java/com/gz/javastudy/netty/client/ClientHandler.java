package com.gz.javastudy.netty.client;

import com.alibaba.fastjson.JSONObject;
import com.gz.javastudy.netty.asyn.RequestFuture;
import com.gz.javastudy.netty.asyn.Response;
import com.gz.javastudy.netty.server.ServerHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.logging.Logger;

public class ClientHandler extends ChannelInboundHandlerAdapter {

	private static final Logger logger = Logger.getLogger(ClientHandler.class.getName());

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		logger.info("接收消息："+msg.toString());
		//读取服务器返回的响应结果，并转换成Response对象，
		//由于经过了StringDecoder解码器，因此msg为String类型
		Response response = JSONObject.parseObject(msg.toString(), Response.class);
		RequestFuture.received(response);

	}

}