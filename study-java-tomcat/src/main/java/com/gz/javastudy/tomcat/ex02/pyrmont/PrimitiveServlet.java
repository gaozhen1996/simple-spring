package com.gz.javastudy.tomcat.ex02.pyrmont;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 一个简单的Servlet实现
 */
public class PrimitiveServlet implements  Servlet {

    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init");
    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("from service");
        String responseHead = "HTTP/1.1 200 ok\r\n text/html\r\nContent-Length: "+"\r\n\r\n";
        PrintWriter out = servletResponse.getWriter();
        out.println(responseHead);
        out.println("Hello,Rose are red.");
        out.print("Violets are blue.");
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {
        System.out.println("destroy");
    }
}
