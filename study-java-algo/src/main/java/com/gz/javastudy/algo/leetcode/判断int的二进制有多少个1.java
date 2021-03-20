package com.gz.javastudy.algo.leetcode;

import java.io.*;
/**
 * 
* <p>
* Description:TODO(这里用一句话描述这个类的作用) 
题目描述
输入一个int型的正整数，计算出该int型数据在内存中存储时1的个数。

输入描述:
 输入一个整数（int类型）

输出描述:
 这个数转换成2进制后，输出1的个数

示例1
输入
5
输出
2

101
* @author gaozhen
* @date 2021年3月15日
* @Version 1.1
 */
public class 判断int的二进制有多少个1 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		InputStream in = System.in;
		int num;
		byte[] bytes = new byte[1024];
		while((num = in.read(bytes)) > 0){
		    String str = new String(bytes,0,num-1);

		    int a = Integer.valueOf(str).intValue();
		    int count = 0;
		    while(a>0) {
		    	count = count + (a&1);
		    	a = a>>1;
		    }
		    System.out.println(count);
		}		
		
	
	}
}