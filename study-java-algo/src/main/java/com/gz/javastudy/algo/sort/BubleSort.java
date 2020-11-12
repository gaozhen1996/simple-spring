package com.gz.javastudy.algo.sort;

import java.util.Random;

/**
 * 
 * <p>
 * Description:冒泡排序
 * <p>
 * 
 * @author gaozhen
 * @date 2018年6月30日
 * @Version 1.0
 */
public class BubleSort {
	
	public static void main(String[] agrs) {
		final int MAX_SIZE = 10;
		int[] array = new int[MAX_SIZE];
		Random random = new Random();
		for (int i = 0; i < MAX_SIZE; i++) {
			array[i] = random.nextInt(MAX_SIZE);
		}
		System.out.println("排序前:");
		print(array);
		System.out.println("开始排序...");
		array = sort(array);
		System.out.println("排序完成");
		System.out.println("排序后:");
		print(array);
	}

	public static int[] sort(int[] nums) {
		for (int i = 0; i < nums.length - 1; i++) {
			/**
			 * 每做完一次循环，第nums.length-i大移到合适的位置
			 */
			for (int j = 0; j < nums.length - 1 - i; j++) {
				if (nums[j] > nums[j + 1]) {
					int temp = nums[j];
					nums[j] = nums[j + 1];
					nums[j + 1] = temp;
				}
			}
			print(nums);
		}
		return nums;
	}

	public static void print(int[] nums) {
		for (int i : nums) {
			System.out.print(i + "\t");
		}
		System.out.println();
	}

}
