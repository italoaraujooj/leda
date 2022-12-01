package sorting.divideAndConquer.quicksort3;

import sorting.AbstractSorting;
import static util.Util.swap;


/**
 * A classe QuickSortMedianOfThree representa uma variação do QuickSort que
 * funciona de forma ligeiramente diferente. Relembre que quando o pivô
 * escolhido divide o array aproximadamente na metade, o QuickSort tem um
 * desempenho perto do ótimo. Para aproximar a entrada do caso ótimo, diversas
 * abordagens podem ser utilizadas. Uma delas é usar a mediana de 3 para achar o
 * pivô. Essa técnica consiste no seguinte:
 * 1. Comparar o elemento mais a esquerda, o central e o mais a direita do intervalo.
 * 2. Ordenar os elementos, tal que: A[left] < A[center] < A[right].
 * 3. Adotar o A[center] como pivô.
 * 4. Colocar o pivô na penúltima posição A[right-1].
 * 5. Aplicar o particionamento considerando o vetor menor, de A[left+1] até A[right-1].
 * 6. Aplicar o algoritmo na particao a esquerda e na particao a direita do pivô.
 */
public class QuickSortMedianOfThree<T extends Comparable<T>> extends
		AbstractSorting<T> {

	public void sort(T[] array, int leftIndex, int rightIndex) {
		if(leftIndex >= 0 && rightIndex < array.length && leftIndex < rightIndex){
			int indexPivot = partition(array, leftIndex, rightIndex);
			sort(array, leftIndex, indexPivot-1);
			sort(array, indexPivot+1, rightIndex);
		}
	}

	private int partition(T[] array, int esq, int dir){
		int indexPivot = escolhePivot(array, esq, dir);
		swap(array, indexPivot, dir-1);

		T pivot = array[dir-1];
		int i = dir-1;

		for(int j = dir-2; j >= esq; j--){
			if(array[j].compareTo(pivot) >= 0){
				i--;
				swap(array, i, j);
			}
		}

		swap(array, dir-1, i);
		return i;
	}

	private int escolhePivot(T[] array, int esq, int dir){
		int meio = (esq+dir)/2;

		if (array[dir].compareTo(array[meio]) < 0) swap(array, dir, meio);
		if (array[meio].compareTo(array[esq]) < 0) swap(array, meio, esq);
		if (array[dir].compareTo(array[meio]) < 0) swap(array, dir, meio);

		return meio;
	}
}
