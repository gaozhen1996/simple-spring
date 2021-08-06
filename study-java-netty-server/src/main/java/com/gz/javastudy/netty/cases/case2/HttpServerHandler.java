/**
 * 
 */
package com.gz.javastudy.netty.cases.case2;



import org.apache.log4j.Logger;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

	private static final Logger logger = Logger.getLogger(HttpServerHandler.class);

	@Override
	protected void channelRead0(ChannelHandlerContext ctx,FullHttpRequest request) throws Exception {
		 if (!request.decoderResult().isSuccess()) {
		 	sendError(ctx, BAD_REQUEST);
		 	return;
		 }
//		 logger.debug("Http Server receive the request : " + request);
		 ByteBuf body = request.content().copy();
		 FullHttpResponse response = new DefaultFullHttpResponse(
	                HTTP_1_1, HttpResponseStatus.OK, body);
		 response.headers().set(HttpHeaderNames.CONTENT_LENGTH, body.readableBytes());
		 ctx.writeAndFlush(response).sync();
//		 logger.debug("Http Server send response succeed : " + response);
	}
	
	 @Override
	 public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	 }
	
	 private static void sendError(ChannelHandlerContext ctx, HttpResponseStatus status) {
		FullHttpResponse response = new DefaultFullHttpResponse(
				HTTP_1_1, status, Unpooled.copiedBuffer("Failure: " + status.toString() + "\r\n", CharsetUtil.UTF_8));
		response.headers().set(CONTENT_TYPE, "text/plain; charset=UTF-8");
		logger.error(response.toString());
		ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
	}
}
