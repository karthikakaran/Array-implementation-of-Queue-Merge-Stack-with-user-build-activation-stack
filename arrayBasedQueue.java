
/**
 * Array-based implementation of the queue.
 * @author Kritika Srivastava
 */

import java.util.*;

class QueueAr<T> {

	private T[] theArray;
	private int currentSize = 0;
	private int size = 0;
	private int front = 0;
	private int rear = -1;
	private double full = 90;
	private double empty = 25;
	private float percentageFull;
	final int defaultCapacity = 16;
	float a;

	public QueueAr() {
		theArray = (T[]) new Object[this.defaultCapacity];
	}

	/**
	 * Procedure to check whether the Array is empty or not
	 * 
	 * @return boolean
	 */
	public boolean isEmpty() {
		return currentSize == 0;
	}

	/**
	 * Procedure to add elements in the Queue
	 *
	 * @param item:
	 *            Array:Integer
	 */

	public void offer(T item) {

		if ((size - 1) == theArray.length) {
			reshuffle();
		}
		theArray[++rear] = item;
		currentSize++;
		size++;
		if (currentSize != 0) {

			percentageFull = ((float) currentSize / (theArray.length)) * 100;
			if (percentageFull > full) {
				increaseSize();
			}
		}
	}

	/**
	 * Procedure to delete elements from the queue @ return: <T> Return null
	 * when the Array is Empty otherwise returns the value that is to deleted
	 */

	public T poll() {
		if (isEmpty())
			return null;

		T frontItem = theArray[front];
		theArray[front] = null;
		front = increment(front);
		currentSize--;
		percentageFull = ((float) currentSize / (theArray.length)) * 100;
		if (theArray.length > defaultCapacity && percentageFull < empty) {
			decreaseSize();
		}

		return frontItem;
	}

	/**
	 * Procedure to increase the front position of the Array @ return: Integer
	 */

	private int increment(int x) {
		if (++x == theArray.length)
			x = 0;
		return x;
	}

	/**
	 * Procedure to reshuffle the Array if the actual size of the array is equal
	 * to the number of elements in the array and elements in the queue are less
	 * than the size of the array
	 * 
	 * @variable tempArr :int: To temporarily store the elements of the array
	 */

	private void reshuffle() {
		T[] tempArr = (T[]) new Object[theArray.length];
		System.arraycopy(theArray, front, tempArr, 0, currentSize);
		theArray = (T[]) new Object[tempArr.length];
		System.arraycopy(tempArr, front, theArray, 0, tempArr.length);
		front = 0;
		rear = currentSize - 1;
		size = currentSize;

	}

	/**
	 * Procedure to increase the size of the Array
	 */
	private boolean increaseSize() {
		T[] tempArr = (T[]) new Object[2 * theArray.length];
		System.arraycopy(theArray, front, tempArr, 0, currentSize);
		theArray = (T[]) new Object[tempArr.length];
		System.arraycopy(tempArr, front, theArray, 0, tempArr.length);
		front = 0;
		rear = currentSize - 1;
		size = currentSize;
		return true;

	}

	/**
	 * Procedure to decrease the size of the Array
	 */

	private void decreaseSize() {
		int halfLength = (theArray.length) / 2;
		if (halfLength > defaultCapacity) {
			T[] tempArr = (T[]) new Object[halfLength];
			System.arraycopy(theArray, front, tempArr, 0, currentSize);
			theArray = (T[]) new Object[halfLength];
			System.arraycopy(tempArr, 0, theArray, 0, tempArr.length);
		}

		else {
			T[] tempArr = (T[]) new Object[defaultCapacity];
			System.arraycopy(theArray, front, tempArr, 0, currentSize);
			theArray = (T[]) new Object[defaultCapacity];
			System.arraycopy(tempArr, 0, theArray, 0, tempArr.length);

		}
		front = 0;
		rear = currentSize - 1;
		size = currentSize;
	}

	public void printArray() {
		if (currentSize == 0) {
			System.out.println("queue is empty");
		}

		else {
			System.out.println("Elements in the queue are");
			for (int i = front; i <= rear; i++) {
				System.out.print(theArray[i]);
				System.out.print(" ");
			}
		}
		System.out.println();

	}

}

public class arrayBasedQueue {
	public static void main(String args[]) {
		QueueAr<Integer> intQueue = new QueueAr<Integer>();
		int temp, item, flag = 0;
		Integer n;

		Scanner reader1 = new Scanner(System.in); // Reading from System.in
		Scanner reader2 = new Scanner(System.in);
		while (flag == 0) {

			System.out.println("Press 0 to add elements to the queue");
			System.out.println("Press 1 to delete elements from the queue");
			System.out.println("Press 2 to print elements of the queue");
			System.out.println("Press 3 to end");

			System.out.println("Enter a number: ");
			temp = reader1.nextInt();
			switch (temp) {
			case 0: {
				System.out.println("Enter the number to add");
				item = reader2.nextInt();
				intQueue.offer(item);
				intQueue.printArray();
				break;
			}
			case 1: {
				n = intQueue.poll();
				if (n == null)
					System.out.println("Queue is empty");
				else {
					System.out.println("The element that is deleted: " + n);
					intQueue.printArray();
				}
				break;
			}
			case 2: {
				intQueue.printArray();
				break;
			}

			case 3: {
				flag = 1;
				break;
			}

			}
		}
	}

}
