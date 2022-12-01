package sorting.divideAndConquer.hybridMergesort;

import sorting.AbstractSorting;
import static util.Util.swap;

/**
 * A classe HybridMergeSort representa a implementação de uma variação do
 * MergeSort que pode fazer uso do InsertionSort (um algoritmo híbrido) da
 * seguinte forma: o MergeSort é aplicado a entradas maiores a um determinado
 * limite. Caso a entrada tenha tamanho menor ou igual ao limite o algoritmo usa
 * o InsertionSort.
 * 
 * A implementação híbrida deve considerar os seguintes detalhes:
 * - Ter contadores das quantidades de MergeSorts e InsertionSorts aplicados, de forma
 *   que essa informação possa ser capturada pelo teste.
 * - A cada chamado do método de sort(T[] array) esses contadores são resetados. E a cada chamada
 *   interna de um merge ou insertion, os contadores MERGESORT_APPLICATIONS e
 *   INSERTIONSORT_APPLICATIONS são incrementados.
 * - O InsertionSort utilizado no algoritmo híbrido deve ser in-place.
 */
public class HybridMergeSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * For inputs with size less or equal to this value, the insertionsort
	 * algorithm will be used instead of the mergesort.
	 */
	public static final int SIZE_LIMIT = 4;

	protected static int MERGESORT_APPLICATIONS = 0;
	protected static int INSERTIONSORT_APPLICATIONS = 0;

	public void sort(T[] array, int leftIndex, int rightIndex) {
		MERGESORT_APPLICATIONS = 0;
		INSERTIONSORT_APPLICATIONS = 0;

		if(leftIndex >= 0 && rightIndex < array.length && leftIndex < rightIndex){
			hybridMergeSort(array, leftIndex, rightIndex);
		}
	}


	private void hybridMergeSort(T[] array, int leftIndex, int rightIndex){
		if(leftIndex >= 0 && rightIndex < array.length && leftIndex < rightIndex){
			if(rightIndex - leftIndex + 1 <= SIZE_LIMIT){
				insertionSort(array, leftIndex, rightIndex);
			} else{
				MERGESORT_APPLICATIONS++;
				int meio = (leftIndex+rightIndex)/2;
				this.hybridMergeSort(array, leftIndex, meio);
				this.hybridMergeSort(array, meio+1, rightIndex);
				merge(array, leftIndex, meio, rightIndex);
			}
		}
	}

	private void insertionSort(T[] array, int leftIndex, int rightIndex){
		if(leftIndex >= 0 && rightIndex < array.length && leftIndex < rightIndex){
			INSERTIONSORT_APPLICATIONS++;
			for (int i = leftIndex+1; i <= rightIndex; i++){
				int j = i;
				while(j > leftIndex && (array[j].compareTo(array[j-1]) < 0)){
					swap(array, j, j-1);
					j -= 1;
				}
			}
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
