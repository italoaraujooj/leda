package sorting.linearSorting;

import sorting.AbstractSorting;

import java.util.Arrays;
import java.util.Optional;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala.
 *
 * Procure evitar desperdício de memória: AO INVÉS de alocar o array de contadores
 * com um tamanho arbitrariamente grande (por exemplo, com o maior valor de entrada possível),
 * aloque este array com o tamanho sendo o máximo inteiro presente no array a ser ordenado.
 *
 * Seu algoritmo deve assumir que o array de entrada nao possui numeros negativos,
 * ou seja, possui apenas numeros inteiros positivos e o zero.
 *
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (leftIndex >= 0 && rightIndex < array.length && leftIndex < rightIndex){
			countingSort(array, leftIndex, rightIndex);
		}
	}

	private void countingSort(Integer[] array, int leftIndex, int rightIndex) {
		int maior = achaMaior(array, leftIndex, rightIndex);
		Integer[] arrayContagem = new Integer[maior+1];

		preenche(arrayContagem, maior);

		for(int i = leftIndex; i<= rightIndex; i++){
			arrayContagem[array[i]]++;
		}

		for(int i = 1; i<= maior; i++){
			arrayContagem[i] += arrayContagem[i-1];
		}

		Integer[] arrayAux = new Integer[array.length];

		for(int i = rightIndex; i >= leftIndex; i--){
			arrayAux[arrayContagem[array[i]]-1 + leftIndex] = array[i];
			arrayContagem[array[i]]--;
		}

		copia(array, arrayAux, leftIndex, rightIndex);

	}

	private void copia(Integer[] array, Integer[] arrayAux, int leftIndex, int rightIndex) {
		for(int i= leftIndex; i <= rightIndex; i++){
			array[i] = arrayAux[i];
		}
	}

	private void preenche(Integer[] array, int tamanho){
		for(int i = 0; i<=tamanho; i++){
			array[i] = 0;
		}
	}

	private int achaMaior(Integer[] array, int leftIndex, int rightIndex){
		int maior = array[leftIndex];

		for(int i = leftIndex + 1; i<= rightIndex; i++){
			if (array[i] > maior) { maior = array[i]; }
		}
		return maior;
	}

}
