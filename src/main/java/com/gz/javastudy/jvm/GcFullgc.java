package com.gz.javastudy.jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * 
* <p>
* Description:触发服务端gc 
* -Xms41m -Xmx41m -Xmn10m -XX:+UseParallelGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps
* 堆空间 = 年轻代 + 年老代  比例：年轻代：年老代 = (1/3) : (2/3)
* 年轻代 = Edem + from + to
* 比例:  Edem : from : to = 8 : 1 : 1
* 
* <p>
* @author gaozhen
* @date 2020年8月24日
* @Version 1.1
 */
public class GcFullgc {
	private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception {
    	Scanner scanner = new Scanner(System.in);
    	
    	List<byte[]> caches = new ArrayList<byte[]>();
    	GcFullgc gcServerFullgc = new GcFullgc();
        
    	while (true) {
    		System.out.println("请输入命令：");
        	String line = scanner.next();
        	int index = line.indexOf(":");
        	String sizeStr = line.substring(index+1, line.length());
        	int size = Integer.parseInt(sizeStr);
        	if(line.contains("rm")) {
        		gcServerFullgc.removeObject(caches,size);
        	}else if(line.contains("add")) {
        		gcServerFullgc.addObject(caches, size);
        	}else if(line.contains("exit")){
        		scanner.close();
        		System.exit(0);
        	}
//        	System.out.println(ClassLayout.parseInstance(caches).toPrintable());
		}

    }

    private void addObject(List<byte[]> caches,int size) {
        for (int i = 0; i < size; i++){
            caches.add(new byte[3 * _1MB]);
        }
    }
    
    private void removeObject(List<byte[]> caches,int size) {
        for (int i = caches.size(); i < size; i--){
            caches.remove(i);
        }
    }
    
}
