/**
 * 
 */
package com.gz.javastudy.netty.cases.case2;

import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;


public class HttpResponse {
	
	private HttpHeaders header;
	
	private FullHttpResponse response;
	
	private byte [] body;
	
	public HttpResponse(FullHttpResponse response) {
		System.out.println("HttpResponse:"+Thread.currentThread().getName());
		this.header = response.headers();
		this.response = response;
		if (response.content() != null) {
			body = new byte[response.content().readableBytes()];
			response.content().getBytes(0, body);
		}
	}

//	public HttpResponse(FullHttpResponse response) {
//		this.header = response.headers();
//		this.response = response;
//	}
	
	public HttpHeaders header()
	{
		return header;
	}

//	public byte [] body()
//	{
//		return body = response.content() != null ?
//				response.content().array() : null;
//	}

//	public byte [] body() {
//		body = new byte[response.content().readableBytes()];
//		response.content().getBytes(0, body);
//		return body;
//	}

	public byte [] body(){
		System.out.println("body:"+Thread.currentThread().getName());
		return body;
	}
}
