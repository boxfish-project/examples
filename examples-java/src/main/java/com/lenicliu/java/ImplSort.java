package com.lenicliu.java;

/**
 * Sort
 * 
 * @author Lenicliu 2015-01-08
 *
 */
public class ImplSort {

	public static int[] array() {
		return new int[] { 2, 6, 9, 3, 5, 1, 8, 0, 4, 7 };
	}

	/**
	 * Swap array[i] < - > array[j]
	 * 
	 * @param array
	 * @param i
	 * @param j
	 */
	public static void swap(int[] array, int i, int j) {
		if (i != j) {
			int t = array[i];
			array[i] = array[j];
			array[j] = t;
		}
	}

	/**
	 * Print array
	 * 
	 * @param array
	 * @param len
	 */
	public static void print(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(String.format("% 2d", array[i]) + " ");
		}
		System.out.println();
	}

	/**
	 * Bubble Sort
	 * 
	 * @param array
	 */
	public static void bubbleSort(int[] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				if (array[i] < array[j]) {
					swap(array, i, j);
				}
			}
		}
	}

	/**
	 * Bubble Sort Optimized
	 * 
	 * @param array
	 */
	public static void bubbleSortOptimized(int[] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = i; j < array.length; j++) {
				if (array[i] > array[j]) {
					swap(array, i, j);
				}
			}
		}
	}

	/**
	 * Insert Sort
	 * 
	 * @param array
	 */
	public static void insertSort(int[] array) {
		for (int i = 1; i < array.length; i++) {
			for (int j = i; j > 0; j--) {
				if (array[j] < array[j - 1]) {
					swap(array, j, j - 1);
				}
			}
		}
	}

	/**
	 * Select Sort
	 * 
	 * @param array
	 */
	public static void selectSort(int[] array) {
		for (int i = 0; i < array.length; i++) {
			int min = array[i];
			int index = i;
			for (int j = i + 1; j < array.length; j++) {
				if (array[j] < min) {
					index = j;
					min = array[j];
				}
			}
			swap(array, i, index);
		}
	}

	/**
	 * Quick Sort
	 * 
	 * @param array
	 */
	public static void quickSort(int[] array, int i, int j) {
		if (i < j) {
			int index = i + 1, m = array[i];
			for (int k = index; k < j + 1; k++) {
				if (array[k] < m) {
					swap(array, k, index++);
				}
			}
			swap(array, i, index - 1);
			quickSort(array, i, index - 1);
			quickSort(array, index, j);
		}
	}

	/**
	 * Quick Sort
	 * 
	 * @param array
	 */
	public static void quickSortOptimized(int[] array, int i, int j) {
		if (i < j) {
			int m = array[(i + j) / 2], l = i - 1, h = j + 1;
			while (true) {
				while (array[++l] < m) {
				}
				while (array[--h] > m) {
				}
				if (l >= h) {
					break;
				}
				swap(array, l, h);
			}
			quickSort(array, i, l - 1);
			quickSort(array, h + 1, j);
		}
	}

	public static void main(String[] args) {
		System.out.println("bubbleSort:");
		int[] array1 = array();
		print(array1);
		bubbleSort(array1);
		print(array1);

		System.out.println("bubbleSortOptimized:");
		int[] array2 = array();
		print(array2);
		bubbleSortOptimized(array2);
		print(array2);

		System.out.println("insertSort:");
		int[] array3 = array();
		print(array3);
		insertSort(array3);
		print(array3);

		System.out.println("selectSort:");
		int[] array4 = array();
		print(array4);
		selectSort(array4);
		print(array4);

		System.out.println("quickSort:");
		int[] array5 = array();
		print(array5);
		quickSort(array5, 0, array5.length - 1);
		print(array5);

		System.out.println("quickSortOptimized:");
		int[] array6 = array();
		print(array6);
		quickSortOptimized(array6, 0, array6.length - 1);
		print(array6);
	}
}