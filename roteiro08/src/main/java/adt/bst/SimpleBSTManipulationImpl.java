package adt.bst;

/**
 * - Esta eh a unica classe que pode ser modificada 
 * @author adalbertocajueiro
 *
 * @param <T>
 */
public class SimpleBSTManipulationImpl<T extends Comparable<T>> implements SimpleBSTManipulation<T> {

	@Override
	public boolean equals(BST<T> tree1, BST<T> tree2) {
		boolean equals;
		if (tree1.isEmpty() && tree2.isEmpty()) equals = true;
		else if (tree1.size() != tree2.size()) equals = false;
		else {
			equals = this.equals((BSTNode<T>) tree1.getRoot(), (BSTNode<T>) tree2.getRoot());
		}
		return equals;
	}

	private boolean equals(BSTNode<T> node1, BSTNode<T> node2) {
		boolean equals = false;
		equals = node1.equals(node2);

		if (equals) {
			if (!node1.getLeft().isEmpty() && !node2.getLeft().isEmpty()) {
				equals = equals((BSTNode<T>) node1.getLeft(), (BSTNode<T>) node2.getLeft());
			} else if (!node1.getRight().isEmpty() && !node2.getRight().isEmpty()) {
				equals = equals((BSTNode<T>) node1.getRight(), (BSTNode<T>) node2.getRight());
			}
		}
		return equals;
	}

	@Override
	public boolean isSimilar(BST<T> tree1, BST<T> tree2) {
		boolean isSimilar;
		if (tree1.isEmpty() && tree2.isEmpty()) isSimilar = true;
		else if (tree1.size() != tree2.size()) isSimilar = false;
		else {
			isSimilar = this.isSimilar((BSTNode<T>) tree1.getRoot(), (BSTNode<T>) tree2.getRoot());
		}

		return isSimilar;
	}

	private boolean isSimilar(BSTNode<T> node1, BSTNode<T> node2) {
		boolean isSimilar = false;
		isSimilar = node1.isLeaf() && node2.isLeaf();

		if (!isSimilar) {
			boolean isSimilarLeft = false;
			boolean isSimilarRight = false;
			if ((!node1.getLeft().isEmpty() && !node2.getLeft().isEmpty()))
				isSimilarLeft = isSimilar((BSTNode<T>) node1.getLeft(), (BSTNode<T>) node2.getLeft());
			else if (node1.getLeft().isEmpty() && node2.getLeft().isEmpty()) isSimilarLeft = true;

			if (!node1.getRight().isEmpty() && !node2.getRight().isEmpty())
				isSimilarRight = isSimilar((BSTNode<T>) node1.getRight(), (BSTNode<T>) node2.getRight());
			else if (node1.getRight().isEmpty() && node2.getRight().isEmpty()) isSimilarRight = true;

			isSimilar = isSimilarLeft && isSimilarRight;
		}
		return isSimilar;
	}

	@Override
	public T orderStatistic(BST<T> tree, int k) {
		T result = null;
		if (k >= 1 && k <= tree.size()) {
			result = orderStatistic(tree, tree.minimum(), k - 1);
		}
		return result;
	}

	private T orderStatistic(BST<T> tree, BSTNode<T> node, int k) {
		T result = null;
		if (k == 0)
			result = node.getData();
		else
			result = orderStatistic(tree, tree.sucessor(node.getData()), k - 1);
		return result;
	}
}
