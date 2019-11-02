package com.gz.javastudy.algo.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 
 * <p>
 * Description:快速排序
 * <p>
 * 
 * @author gaozhen
 * @date 2018年8月14日
 * @Version 1.1
 */
public class QuickSort {
	public static void main(String[] args) {
		final int MAX_SIZE = 10;
		int[] a = new int[MAX_SIZE];
		Random random = new Random();
		for (int i = 0; i < MAX_SIZE; i++) {
			a[i] = random.nextInt(MAX_SIZE);
		}
		System.out.println("排序前");
		printPart(a);
		quickSort(a);
		System.out.println("排序后");
		printPart(a);
	}

	public static void quickSort(int[] a) {
		if (a.length > 0) {
			quickSort(a, 0, a.length - 1);
		}
	}

	// 打印序列
	public static void printPart(int[] list) {
		System.out.println(Arrays.toString(list));
	}

	private static void quickSort(int[] a, int low, int high) {
		/**
		 * 校验参数
		 */
		if (low >= high) {
			return;
		}

		int i = low;// 左指针
		int j = high;// 右指针
		int key = a[low];// 标准线

		while (i < j) {
			while (a[j] >= key && i < j) {
				j--;
			}

			while (a[i] <= key && i < j) {
				i++;
			}

			if (i < j) {
				int temp = a[i];
				a[i] = a[j];
				a[j] = temp;
			}
		}

		/**
		 * 调整key的位置
		 */
		int temp = a[i];
		a[i] = a[low];
		a[low] = temp;

		/**
		 * 左子序列快排
		 */
		printPart(a);
		quickSort(a, low, i - 1);
		/**
		 * 右子序列快排
		 */
		quickSort(a, i + 1, high);

	}
}