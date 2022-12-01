package adt.stack;

public class StackImpl<T> implements Stack<T> {

	private T[] array;
	private int top;

	@SuppressWarnings("unchecked")
	public StackImpl(int size) {
		array = (T[]) new Object[size];
		top = -1;
	}

	@Override
	public T top() {
		if(this.isEmpty()) return null;
		return this.array[top];
	}

	@Override
	public boolean isEmpty() {
		return this.top == -1;
	}

	@Override
	public boolean isFull() {
		return this.top == this.array.length-1;
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (isFull()) throw new StackOverflowException();
		if (element != null){
			array[++top] = element;
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (isEmpty()) throw new StackUnderflowException();
		T elementoPop = this.array[top];
		array[top--] = null;
		return elementoPop;
	}

}
