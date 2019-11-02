package com.gz.javastudy.algo.sort;

import java.util.Random;

/**
 * 
* <p>
* Description:插入排序
* <p>
* @author gaozhen
* @date 2018年6月30日
* @Version 1.1
 */
public class InsertSort {

	   public void insertSort(int[] list) {
			System.out.println("sort start...");
	        // 第1个数肯定是有序的，从第2个数开始遍历，依次插入有序序列
			for(int i=1;i<list.length;i++) {
				for(int j=i;j>0;j--) {
					if(list[j]<list[j-1]) {
						int temp = list[j];
						list[j]=list[j-1];
						list[j-1]=temp;
					}else {
						break;
					}
				}
				printPart(list);
			}
			System.out.println("sort success...");
	    }
	
	
	   
	    // 打印序列
	    public void printPart(int[] list) {
	        for (int i = 0; i < list.length; i++) {
	            System.out.print(list[i]+"\t");
	        }
	        System.out.println();
	    }
	 
	    public static void main(String[] args) {
	        // 初始化一个随机序列
	        final int MAX_SIZE = 10;
	        int[] array = new int[MAX_SIZE];
	        Random random = new Random();
	        for (int i = 0; i < MAX_SIZE; i++) {
	            array[i] = random.nextInt(MAX_SIZE);
	        }
	        array = new int[]{9,8,5,7,3,6,7,8,1,5};
	        // 调用冒泡排序方法
	        InsertSort insert = new InsertSort();
	        System.out.println("排序前:\t");
	        insert.printPart(array);
	        insert.insertSort(array);
	        System.out.println("排序后:\t");
	        insert.printPart(array);
	    }
	
}
