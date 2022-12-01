package adt.bst;

import adt.bt.BTNode;

import java.util.ArrayList;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.root);
	}

	private int height(BSTNode<T> node){
		int height = -1;
		if (!node.isEmpty()){
			height = 1 + Math.max(height((BSTNode<T>) node.getLeft()), height((BSTNode<T>) node.getRight()));
		}
		return height;
	}

	@Override
	public BSTNode<T> search(T element) {
		BSTNode<T> result = new BSTNode<T>();
		if(element != null && !this.isEmpty()) result = search(this.root, element);
		return result;
	}

	private BSTNode<T> search(BSTNode<T> node, T element){
		if(!node.isEmpty()){
			if(node.getData().equals(element)) return node;
			else if (element.compareTo(node.getData()) < 0) return search((BSTNode<T>) node.getLeft(), element);
			else if (element.compareTo(node.getData()) > 0) return search((BSTNode<T>) node.getRight(), element);
		}
		return new BSTNode<T>();
	}

	@Override
	@SuppressWarnings("unchecked")
	public void insert(T element) {
		if(element != null && search(element).isEmpty()){
			if(!isEmpty()) insert(this.root, element);
			else{
				BSTNode<T> newNode = (BSTNode<T>) new BSTNode.Builder<T>()
													.data(element)
													.left(new BTNode<T>())
													.right(new BTNode<T>())
													.parent(new BSTNode<T>())
													.build();
				this.root = newNode;
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void insert(BSTNode<T> node, T element){
		if(element.compareTo(node.getData()) < 0){
			if(node.getLeft().isEmpty()){
				BSTNode<T> newNode = (BSTNode<T>) new  BSTNode.Builder<T>()
						.data(element)
						.left(new BSTNode<T>())
						.right(new BSTNode<T>())
						.parent(node)
						.build();
				node.setLeft(newNode);
			} else insert((BSTNode<T>) node.getLeft(),element);
		}
		else if(element.compareTo(node.getData()) > 0){
			if(node.getRight().isEmpty()){
				BSTNode<T> newNode = (BSTNode<T>) new  BSTNode.Builder<T>()
						.data(element)
						.left(new BSTNode<T>())
						.right(new BSTNode<T>())
						.parent(node)
						.build();
				node.setRight(newNode);
			}else insert((BSTNode<T>) node.getRight(),element);
		}
	}

	@Override
	public BSTNode<T> maximum() {
		if(isEmpty()) return null;
		return maximum(this.root);
	}

	private BSTNode<T> maximum(BSTNode<T> node){
		if(node.getRight().getData() == null) return node;
		else return maximum((BSTNode<T>)node.getRight());
	}

	@Override
	public BSTNode<T> minimum() {
		if(isEmpty()) return null;
		return minimum(this.root);
	}

	private BSTNode<T> minimum(BSTNode<T> node){
		if(node.getLeft().getData() == null) return node;
		else return minimum((BSTNode<T>) node.getLeft());
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> result = null;
		if(element != null && !isEmpty() && !search(element).isEmpty()){
			if(this.search(element).getRight().isEmpty()){
				result = this.sucessor(this.search(element),element);
			} else return minimum((BSTNode<T>) this.search(element).getRight());
		}
		return result;
	}

	private BSTNode<T> sucessor(BSTNode<T> node, T element){
		if(node.getParent().isEmpty()) return null;
		else if(node.getParent().getData().compareTo(element) > 0) return (BSTNode<T>) node.getParent();
		else return sucessor((BSTNode<T>) node.getParent(), element);

	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> result = null;
		if(element != null && !isEmpty() && !search(element).isEmpty()){
			if(search(element).getLeft().isEmpty()){
				result = this.predecessor(this.search(element),element);
			} else return maximum((BSTNode<T>) search(element).getLeft());
		}
		return result;
	}

	private BSTNode<T> predecessor(BSTNode<T> node, T element){
		if(node.getParent().isEmpty()) return null;
		else if(node.getParent().getData().compareTo(element) < 0) return (BSTNode<T>) node.getParent();
		else return predecessor((BSTNode<T>) node.getParent(), element);
	}

	@Override
	public void remove(T element) {
		if(element != null && !this.isEmpty() && !this.search(element).isEmpty()) {
			BSTNode<T> node = this.search(element);
			this.remove(node, element);
		}
	}

	private void remove(BSTNode<T> node, T element){
		if(node.isLeaf()){
			if(node.equals(this.root)) this.root = new BSTNode<T>();
			else if (node.getParent().getLeft().equals(node)) node.getParent().setLeft(new BSTNode<T>());
			else if (node.getParent().getRight().equals(node)) node.getParent().setRight(new BSTNode<T>());
		}else if (!node.getLeft().isEmpty() && node.getRight().isEmpty()){
			if(node.equals(this.root)){
				this.root = (BSTNode<T>) node.getLeft();
				this.root.setParent(new BSTNode<T>());
			} else {
				node.getLeft().setParent(node.getParent());
				if(node.getParent().getData().compareTo(node.getLeft().getData()) > 0)
					node.getParent().setLeft(node.getLeft());
				else node.getParent().setRight(node.getLeft());
			}
		} else if(!node.getRight().isEmpty() && node.getLeft().isEmpty()){
			if(node.equals(this.root)){
				this.root = (BSTNode<T>) node.getRight();
				this.root.setParent(new BSTNode<T>());
			} else {
				node.getRight().setParent(node.getParent());
				if(node.getParent().getData().compareTo(node.getRight().getData()) > 0)
					node.getParent().setLeft(node.getRight());
				else node.getParent().setRight(node.getRight());
			}
		} else if(!node.getRight().isEmpty() && !node.getLeft().isEmpty()){
			BSTNode<T> sucessor = sucessor(element);
			node.setData(sucessor.getData());
			sucessor.setData(element);
			remove(sucessor,sucessor.getData());
		}
	}

	@Override
	public T[] preOrder() {
		T[] arrayPreOrder = (T[]) new Comparable[size()];
		if(isEmpty()) return arrayPreOrder;
		ArrayList<T> list = new ArrayList<T>();
		list = preOrder(this.root,list);
		list.toArray(arrayPreOrder);
		return arrayPreOrder;
	}

	private ArrayList<T> preOrder(BSTNode<T> node, ArrayList<T> list){
		if(!node.isEmpty()){
			list.add(node.getData());
			preOrder((BSTNode<T>)node.getLeft(),list);
			preOrder((BSTNode<T>)node.getRight(),list);
		}
		return list;
	}

	@Override
	public T[] order() {
		T[] arrayOrder = (T[]) new Comparable[size()];
		if(isEmpty()) return arrayOrder;
		ArrayList<T> list = new ArrayList<T>();
		list = order(this.root,list);
		list.toArray(arrayOrder);
		return arrayOrder;
	}

	private ArrayList<T> order(BSTNode<T> node, ArrayList<T> list){
		if(!node.isEmpty()){
			order((BSTNode<T>) node.getLeft(),list);
			list.add(node.getData());
			order((BSTNode<T>)node.getRight(),list);
		}
		return list;
	}

	@Override
	public T[] postOrder() {
		T[] arrayPostOrder = (T[]) new Comparable[size()];
		if(isEmpty()) return arrayPostOrder;
		ArrayList<T> list = new ArrayList<T>();
		list = postOrder(this.root,list);
		list.toArray(arrayPostOrder);
		return arrayPostOrder;
	}

	private ArrayList<T> postOrder(BSTNode<T> node, ArrayList<T> list){
		if(!node.isEmpty()){
			list.add(node.getData());
			postOrder((BSTNode<T>) node.getLeft(),list);
			postOrder((BSTNode<T>)node.getRight(),list);
		}
		return list;
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}
}
