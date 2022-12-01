package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos:
 * - Alocar o tamanho minimo possivel para o array de contadores (C)
 * - Ser capaz de ordenar arrays contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (leftIndex >= 0 && rightIndex < array.length && leftIndex < rightIndex){
			extendedCountingSort(array, leftIndex, rightIndex);
		}
	}

	private void extendedCountingSort(Integer[] array, int leftIndex, int rightIndex) {
		int menor = achaMenor(array, leftIndex, rightIndex);
		int maior = achaMaior(array, leftIndex, rightIndex);

		Integer[] arrayContagem = new Integer[maior-menor + 1];

		preenche(arrayContagem);

		for(int i = leftIndex; i<= rightIndex; i++){
			arrayContagem[array[i] - menor]++;
		}

		for (int i = 1; i<arrayContagem.length; i++){
			arrayContagem[i] += arrayContagem[i-1];
		}

		Integer[] arrayAux = new Integer[array.length];

		for (int i = rightIndex; i>= leftIndex; i--){
			arrayAux[arrayContagem[array[i] - menor]-1 + leftIndex] = array[i];
			arrayContagem[array[i] - menor]--;
		}

		copia(array, arrayAux, leftIndex, rightIndex);

	}

	private void copia(Integer[] array, Integer[] arrayAux, int leftIndex, int rightIndex) {
		for(int i= leftIndex; i <= rightIndex; i++){
			array[i] = arrayAux[i];
		}
	}

	private void preenche(Integer[] array){
		for(int i = 0; i < array.length; i++){
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

	private int achaMenor(Integer[] array, int leftIndex, int rightIndex){
		int menor = array[leftIndex];

		for (int i = leftIndex+1; i<=rightIndex; i++) {
			if (array[i] < menor) {
				menor = array[i];
			}
		}
		return menor;
	}

}
