package com.gz.javastudy.netty.server;

import com.alibaba.fastjson.JSONObject;
import com.gz.javastudy.netty.asyn.RequestFuture;
import com.gz.javastudy.netty.asyn.Response;
import com.gz.javastudy.netty.core.Mediator;
import com.gz.javastudy.netty.spring.InitLoadRemoteMethod;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.logging.Logger;

//Sharable注解表示此Handler对所有Channel共享，无状态，注意多线程并发
@ChannelHandler.Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {

	private static final Logger logger = Logger.getLogger(ServerHandler.class.getName());

	/**
	 * 读取客户端发送的数据
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		logger.info("接收消息:"+msg);
		//获取客户端发送的请求，并转换成RequestFuture对象，
		//由于经过StringDecoder解码器，因此msg为String类型
		RequestFuture request = JSONObject.parseObject((String) msg,RequestFuture.class);
		//获取请求id
		long id = request.getId();
		 //构建响应结果
		Response response = Mediator.process(request);
		response.setId(id);
		//把响应结果返回给客户端
		logger.info("发送消息:"+JSONObject.toJSONString(response));
		ctx.channel().write(Unpooled.copiedBuffer(JSONObject.toJSONString(response).getBytes()));
		ctx.channel().unsafe().flush();
	}

	@Override
	public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		super.channelInactive(ctx);
	}

	/***
	 * 这个方法会在发生异常时触发
	 *
	 * @param ctx
	 * @param cause
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		/**
		 * exceptionCaught() 事件处理方法是当出现 Throwable 对象才会被调用，即当 Netty 由于 IO
		 * 错误或者处理器在处理事件时抛出的异常时。在大部分情况下，捕获的异常应该被记录下来 并且把关联的 channel
		 * 给关闭掉。然而这个方法的处理方式会在遇到不同异常的情况下有不 同的实现，比如你可能想在关闭连接之前发送一个错误码的响应消息。
		 */
		// 出现异常就关闭
		System.out.println("发生异常了----------------------------");
		cause.printStackTrace();
		ctx.close();
	}
}
