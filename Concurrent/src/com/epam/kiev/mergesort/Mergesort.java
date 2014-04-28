package com.epam.kiev.mergesort;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class Mergesort {
	
	private ExecutorService service;
	
	public Mergesort(ExecutorService service){
		this.service = service;
	}

	public int[] sort(int[] a) {
		if (a.length == 1) {
			return a;
		}
		int middle = a.length / 2;
		final int[] left = new int[middle];
		final int[] right = new int[a.length - middle];

		for (int i = 0; i < a.length; i++) {
			if (i < middle) {
				left[i] = a[i];
			} else {
				right[i - middle] = a[i];
			}
		}		
		Future<int[]> future;
		try {
			future = service.submit(new Callable<int[]>() {
				@Override
				public int[] call() throws Exception {				
					return sort(right);
				}
			});		
			return merge(sort(left),
					future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}	
		return null;
		
	}

	private int[] merge(int[] left, int[] right) {
		int[] result = new int[left.length + right.length];

		int i = 0, j = 0, k = 0;

		while (k < result.length) {
			if (i == left.length) {
				result[k] = right[j];
				j++;
			} else if (j == right.length) {
				result[k] = left[i];
				i++;
			} else {
				if (left[i] < right[j]) {
					result[k] = left[i];
					i++;
				} else {
					result[k] = right[j];
					j++;
				}
			}
			k++;
		}
		return result;
	}

}
