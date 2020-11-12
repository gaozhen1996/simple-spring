package com.gz.javastudy.algo.sort;

import java.util.Arrays;
import java.util.Random;

public class ShellSort {

	public static void main(String[] args) {
		final int MAX_SIZE = 10;
		int[] a = new int[MAX_SIZE];
		Random random = new Random();
		for (int i = 0; i < MAX_SIZE; i++) {
			a[i] = random.nextInt(MAX_SIZE);
		}
		// int[] a = {9,8,7,6,5,4,3,2,1,0};
		System.out.println("排序前");
		printPart(a);
		shellSortSmallToBig(a);
		System.out.println("\n排序后");
		printPart(a);
	}

	public static void shellSortSmallToBig(int[] a) {
		// 缩小增量
		int temp;
		for (int grew = a.length / 2; grew > 0; grew /= 2) {
			// 分组排序
			for (int i = grew; i < a.length; i++) {
				for (int j = i - grew; j >= 0; j -= grew) {
					if (a[j] > a[j + grew]) {
						temp = a[j];
						a[j] = a[j + grew];
						a[j + grew] = temp;
					}
				}
			}
		}
	}

	// 打印序列
	public static void printPart(int[] list) {
		System.out.println(Arrays.toString(list));
	}

}
