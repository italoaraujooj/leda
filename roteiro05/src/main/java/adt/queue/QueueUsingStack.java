package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> stack1;
	private Stack<T> stack2;

	public QueueUsingStack(int size) {
		stack1 = new StackImpl<T>(size);
		stack2 = new StackImpl<T>(size);
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(stack1.isFull())
			throw new QueueOverflowException();

		if(element != null){
			while(!stack1.isEmpty()){
				try{
					stack2.push(stack1.pop());
				} catch(StackUnderflowException e ){
					throw new QueueOverflowException();
				} catch(StackOverflowException e){
					throw new QueueOverflowException();
				}
			}

			try {
				stack1.push(element);
			} catch(StackOverflowException e){
				throw new QueueOverflowException();
			}

			while(!stack2.isEmpty()){
				try{
					stack1.push(stack2.pop());
				} catch(StackUnderflowException e ){
					throw new QueueOverflowException();
				}catch(StackOverflowException e){
					throw new QueueOverflowException();
				}
			}
		}

	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if(stack1.isEmpty())
			throw new QueueUnderflowException();

		T elementDequeue = stack1.top();

		try {
			stack1.pop();
		} catch (StackUnderflowException e ){
			throw new QueueUnderflowException();
		}

		return elementDequeue;
	}

	@Override
	public T head() {
		if(stack1.isEmpty())
			return null;

		return stack1.top();
	}

	@Override
	public boolean isEmpty() {
		return stack1.isEmpty();
	}

	@Override
	public boolean isFull() {
		return stack1.isFull();
	}

}
