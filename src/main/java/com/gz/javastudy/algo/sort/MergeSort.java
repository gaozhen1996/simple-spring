package com.gz.javastudy.algo.sort;

import java.util.Arrays;

/**
 * 
 * <p>
 * Description:并归排序
 * <p>
 * 
 * @author gaozhen
 * @date 2018年8月13日
 * @Version 1.1
 */
public class MergeSort {
	public static void main(String[] args) {
		int[] arr = { 9, 8, 0, 6, 5, 4, 3, 2, 1 };
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}

	public static void sort(int[] arr) {
		int[] temp = new int[arr.length];// 在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
		sort(arr, 0, arr.length - 1, temp);
	}

	private static void sort(int[] arr, int left, int right, int[] temp) {
		if (left < right) {
			int mid = (left + right) / 2;
			// 分左子序列
			sort(arr, left, mid, temp);
			// 分右子序列
			sort(arr, mid + 1, right, temp);
			// 治
			merge(arr, left, mid, right, temp);
		}

	}

	private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
		// 左子序列的下标索引
		int i = left;
		// 右子序列的下标索引
		int j = mid + 1;
		// 临时数组下标索引
		int k = 0;
		while (i <= mid && j <= right) {
			if (arr[i] < arr[j]) {
				temp[k] = arr[i];
				i++;
			} else {
				temp[k] = arr[j];
				j++;
			}
			k++;
		}
		while (i <= mid) {
			temp[k] = arr[i];
			i++;
			k++;
		}
		while (j <= right) {
			temp[k] = arr[j];
			j++;
			k++;
		}
		//将temp中的元素全部拷贝到原数组中
		System.arraycopy(temp,0,arr,left,right-left+1);
	}

	
}