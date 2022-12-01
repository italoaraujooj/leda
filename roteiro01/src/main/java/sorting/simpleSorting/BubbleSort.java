package sorting.simpleSorting;

import sorting.AbstractSorting;

import static util.Util.swap;

/**
 * The bubble sort algorithm iterates over the array multiple times, pushing big
 * elements to the right by swapping adjacent elements, until the array is
 * sorted.
 */
public class BubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		T[] lista = array;
		for (int i = 0; i < array.length - 1; i++ ){
			boolean taOrdenado = true;
			for (int j = 0; j < array.length - 1; j++){
				 if(array[j].compareTo(array[j + 1]) > 0){
					swap(array, j, j+1);
					taOrdenado = false;
				}
			}
			if (taOrdenado){
				break;
			}
		}
	}
}
