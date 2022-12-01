package orderStatistic;

import static util.Util.swap;

/**
 * O quickselect eh um algoritmo baseado no quicksort para
 * descobrir/selectionar, em tempo linear, a k-esima estatistica de ordem
 * (k-esimo menor elemento) de um conjunto de dados.
 *
 * O quiskselect escolhe um elemento para ser o pivot e particiona o array
 * inicial em dois subarrays da mesma forma que o quicksort (elementos menores
 * que o pivot a esquerda do pivot e elementos maiores que o pivot a direita
 * dele). Entretanto, ao inves de chamar o quicksort recursivo nas duas metades,
 * o quickselect eh executado (recursivamente) apenas na metade que contem o
 * elemento que ele procura (o k-esimo menor elemento). Isso reduz a
 * complexidade de O(n.log n) para O(n)
 *
 * @author adalberto e campelo
 *
 */
public class QuickSelect<T extends Comparable<T>> {

	/**
	 * O algoritmo quickselect usa a mesma abordagem do quicksort para calcular o
	 * k-esimo menor elemento (k-esima estatistica de ordem) de um determinado
	 * array de dados comparaveis. Primeiro ele escolhe um elemento como o pivot
	 * e particiona os dados em duas partes, baseando-se no pivot (exatemente da
	 * mesma forma que o quicksort). Depois disso, ele chama recursivamente o
	 * mesmo algoritmo em apenas uma das metades (a que contem o k-esimo menor
	 * elemento). Isso reduz a complexidade de O(n.log n) para O(n).
	 *
	 * Caso o array seja vazio ou a ordem (posicao) do elemento desejado esteja
	 * fora do tamanho do array, o metodo deve retornar null.
	 *
	 *
	 * @param array
	 *            o array de dados a procurar o k-esimo menor elemento
	 *            este array normalmente nao esta ordenado
	 * @param k
	 *            a ordem do elemento desejado. 1 significa primeiro menor
	 *            elemento, 2 significa segundo menor elemento e assim por
	 *            diante
	 * @return
	 *
	 */
	public T quickSelect(T[] array, int k) {
		if(array.length >= 1 && k > 0 && k <= array.length){
			return quickSelectLado( array, 0, array.length-1, k);
		} else{
			return null;
		}
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