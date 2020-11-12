package com.gz.javastudy.algo.sort;

import java.util.Arrays;

public class SelectSort {

	   public static void main(String[] args) {  
	        int[] a = {1,2,4,5,7,4,5,3,9,0};  
	        System.out.println("排序前");
	        printPart(a); 
	        sort(a);
	        System.out.println("排序后");
	        printPart(a);
	    }  
	  
	    
	    // 打印序列
	    public static void printPart(int[] list) {
	    	 	System.out.println(Arrays.toString(list));  
	    }
	 
	    private static void sort(int[] a) {  
	    		for(int i=0;i<a.length;i++) {
	    			int minIndex = i;
	    			//找到从i到a.length序列中数值最小的
	    			for(int j=i;j<a.length;j++) {
	    				if(a[j]<a[minIndex]) {
	    					minIndex = j;
	    				}
	    			}
	    			//交换位置
	    			int temp = a[minIndex];
	    			a[minIndex] = a[i];
	    			a[i]=temp;
	    		}
	    }  
	
}
