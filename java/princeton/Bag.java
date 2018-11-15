/**
 * 
 */
package princeton;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Lee
 * 引用http://algs4.cs.princeton.edu/44sp/Bag.java.html
 * 实现一个单向列表
 */
public class Bag<T> implements Iterable<T> {
	private int N;
	private Node<T> first;
	
	private static class Node<T>{
		private T item;
		private Node<T> next;
	}
	
	public Bag() {
		first = null;
		N =0;
	}
	
	public boolean isEmpty(){
		return first == null;
	}

	public int size(){
		return N;
	}
	
	/**
	 * @param item 添加到单项列表的头位置
	 */
	public void add(T item){
		Node<T> oldfirst = first;
		first = new Node<T>();
		first.item = item;
		first.next = oldfirst;
		N++;
	}
	
	public Iterator<T> iterator(){
		return new ListIterator<T>(first);
	}
	
	private class ListIterator<T> implements Iterator<T>{
		private Node<T> current;
		public ListIterator(Node<T> first){
			current = first;
		}
		@Override
		public boolean hasNext() {
			return current != null;
		}
		@Override
		public T next() {
			if(!hasNext()) throw new NoSuchElementException();
			T item = current.item;
			current = current.next;
			return item;
		}
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Bag<String> bag = new Bag<String>();
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNextLine()){
			String item = scanner.nextLine();
			if(item.length()<=0)break;
			bag.add(item);
		}
		System.out.println("size of bag = "+bag.size());
		for(String str : bag){
			System.out.println(str);
		}
	}

}
