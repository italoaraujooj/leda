package orderStatistic;

import java.util.Comparator;

import static util.Util.swap;

/**
 * Uma implementacao da interface KLargest que usa estatisticas de ordem para 
 * retornar um array com os k maiores elementos de um conjunto de dados/array.
 * 
 * A k-esima estatistica de ordem de um conjunto de dados eh o k-esimo menor
 * elemento desse conjunto. Por exemplo, considere o array [4,8,6,9,12,1]. 
 * A 3a estatistica de ordem eh 6, a 6a estatistica de ordem eh 12.
 * 
 * Note que, para selecionar os k maiores elementos, pode-se pegar todas as 
 * estatisticas de ordem maiores que k. 
 * 
 * Requisitos do algoritmo:
 * - DEVE ser in-place. Porem, voce pode modificar o array original.
 *   Voce pode criar ainda um array de tamanho k que vai armazenar apenas
 *   os elementos a serem retornados.
 * - Voce DEVE usar alguma ideia de algoritmo de ordenacao visto em sala 
 *   para calcular estatisticas de ordem.
 * - Se a entrada for invalida, deve-se retornar um array vazio (por exemplo,
 *   ao solicitar os 5 maiores elementos em um array que soh contem 3 elementos).
 *   Este metodo NUNCA deve retornar null.
 * 
 * @author campelo and adalberto
 *
 * @param <T>
 */
public class KLargestOrderStatisticsImpl<T extends Comparable<T>> implements KLargest<T>{

	@Override
	public T[] getKLargest(T[] array, int k) {
		if(array.length >= 1 && k <= array.length && k > 0){
			T[] KLargests = (T[]) new Comparable[k];
			int cont = 0;
			for (int i = array.length-k; i < array.length; i++){
				KLargests[cont] = orderStatistics(array, i+1);
				cont++;
			}
			return KLargests;
		}
		return null;
	}

	/**
	 * Metodo que retorna a k-esima estatistica de ordem de um array, usando
	 * a ideia de algum algoritmo de ordenacao visto em sala. Caso nao exista 
	 * a k-esima estatistica de ordem, entao retorna null.
	 * 
	 * Obs: o valor de k deve ser entre 1 e N.
	 * 
	 * @param array
	 * @param k
	 * @return
	 */
	public T orderStatistics(T[] array, int k){
		if(k <= array.length && k > 0){
			return quickSelectLado(array, 0, array.length-1, k);
		} else return null;
	}

	private T quickSelectLado(T[] array, int esq, int dir, int k){
		if( esq < dir){
			int indexPivot = particiona(array, esq, dir);
			if(k-1 > indexPivot) return quickSelectLado(array, indexPivot+1, dir, k);
			else if (k-1 < indexPivot) return quickSelectLado(array, esq, indexPivot-1, k);
			else return array[indexPivot];
		} else if (esq == dir){
			return array[esq];
		} else return null;
	}

	private int particiona(T[] array, int esq, int dir){
		int indexPivot = escolhePivot(array, esq, dir);
		swap(array, indexPivot, dir-1);
		T pivot = array[dir-1];
		int i = dir - 1;

		for (int j = dir - 2; j>= esq; j--){
			if(array[j].compareTo(pivot) >= 0){
				i-=1;
				swap(array, i, j);
			}
		}

		swap(array, dir-1, i);
		return i;
	}

	private int escolhePivot(T[] array, int esq, int dir){
		int meio = (esq+dir)/2;

		if(array[dir].compareTo(array[meio]) < 0) swap(array, dir, meio);
		if(array[meio].compareTo(array[esq]) < 0) swap(array, meio, esq);
		if(array[dir].compareTo(array[meio]) < 0) swap(array, dir, meio);

		return meio;
	}
}
