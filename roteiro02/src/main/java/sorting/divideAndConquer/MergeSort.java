package sorting.divideAndConquer;

import sorting.AbstractSorting;
import util.Util;

import static util.Util.swap;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if(leftIndex >= 0 && rightIndex < array.length && leftIndex<rightIndex){
			int meio = (leftIndex+rightIndex)/2;
			sort(array, leftIndex, meio);
			sort(array, meio+1, rightIndex);

			merge(array, leftIndex, meio, rightIndex);
		}

	}

	private void merge(T[] array, int inicio, int meio, int fim){
		T[] helper = (T[]) new Comparable[array.length];
		for(int i= inicio; i<=fim; i++){
			helper[i] = array[i];
		}

		int i = inicio;
		int j = meio+1;
		int k = inicio;

		while(i <= meio && j<= fim){
			if(helper[i].compareTo(helper[j]) < 0){
				array[k] = helper[i];
				i++;
				k++;
			} else{
				array[k] = helper[j];
				j++;
				k++;
			}
		}

		while(i<=meio){
			array[k] = helper[i];
			i++;
			k++;

		}

		while(j<=fim){
			array[k] = helper[j];
			j++;
			k++;
		}


	}
}
