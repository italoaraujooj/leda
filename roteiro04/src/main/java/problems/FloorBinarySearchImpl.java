package problems;

import static util.Util.swap;

public class FloorBinarySearchImpl implements Floor {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		quickSort(array, 0, array.length-1);
		return buscaBinaria(array, 0, array.length-1, x);
	}

	private Integer buscaBinaria(Integer[] array, int esq, int dir, Integer x){
		if(esq>dir) return null;
		if(x.compareTo(array[esq]) < 0) return null;

		int meio = (esq+dir)/2;

		if(x.compareTo(array[dir]) >= 0) return array[dir];
		if(meio == esq) return array[esq];
		if(array[meio].compareTo(x) == 0) return array[meio];
		else if (array[meio].compareTo(x)<0) return buscaBinaria(array, meio, dir, x);
		else return buscaBinaria(array, esq, meio-1, x);
	}

	private void quickSort(Integer[] array, int esq, int dir){
		if(esq >= 0 && dir > array.length && esq < dir){
			int indexPivot = particiona(array, esq, dir);
			quickSort(array, esq, indexPivot-1);
			quickSort(array, indexPivot+1, dir);
		}
	}

	private int particiona(Integer[] array, int esq, int dir){
		int indexPivot = escolhePivot(array, esq, dir);
		swap(array, indexPivot, dir-1);
		Integer pivot = array[dir-1];
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

	private int escolhePivot(Integer[] array, int esq, int dir){
		int meio = (esq+dir)/2;

		if(array[dir].compareTo(array[meio]) < 0) swap(array, dir, meio);
		if(array[meio].compareTo(array[esq]) < 0) swap(array, meio, esq);
		if(array[dir].compareTo(array[meio]) < 0) swap(array, dir, meio);

		return meio;
	}

}

