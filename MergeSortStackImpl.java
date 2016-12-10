
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MergeSortStackImpl<T extends Comparable<? super T>> {

	private List<T> list = new ArrayList<>();
	private List<StackRemember> stack = new ArrayList<>();
	 /** Procedure to Divide the List into sublist
     *
     * @param x: List : Integer. Points to the start of the list
     * @param y: LinkedList : Integer. Points to the end of the List

     */

	public void mergeSort(int start, int end) {
		int middle = 0, leftMarker = 0, rightMarker = 0;
		int flag = 0;
		//base condition : return for 0 or 1 
		if (end - start < 1)
			return;
		//outer loop for whole process
		secondLoop: do {
			while (end - start >= 1) {
				middle = (start + end) / 2;
				//Left sub tree
				if (leftMarker == 0) {
					StackRemember sr = new StackRemember();
					sr.setLeft(start);
					sr.setRight(end);
					stack.add(sr);
					leftMarker = stack.get(stack.size() - 1).leftMarker;
					rightMarker = stack.get(stack.size() - 1).rightMarker;
					end = middle;
				} else if (rightMarker == 0) {
					//Right sub tree
					if (!(stack.get(stack.size() - 1).getLeft() == start
							&& stack.get(stack.size() - 1).getRight() == end)) {
						StackRemember sr = new StackRemember();
						sr.setLeft(start);
						sr.setRight(end);
						stack.add(sr);
						leftMarker = stack.get(stack.size() - 1).leftMarker;
						rightMarker = stack.get(stack.size() - 1).rightMarker;
					}
					if (leftMarker == 1)
						start = middle + 1;
				} else if (leftMarker == 1 && rightMarker == 1) {
					//break if both trees are completed
					break;
				}
			}
			
			//Mark left or right completed
			StackRemember st = new StackRemember();
			if (leftMarker == 0 || rightMarker == 0) {
				if (stack.size() > 0) {
					start = stack.get(stack.size() - 1).getLeft();
					end = stack.get(stack.size() - 1).getRight();
					leftMarker = stack.get(stack.size() - 1).leftMarker;
					rightMarker = stack.get(stack.size() - 1).rightMarker;
					//update left side marker
					if (leftMarker == 0) {
						st = stack.get(stack.size() - 1);
						st.updateLeftMarker(1);
						leftMarker = 1;
					} else if (rightMarker == 0) {
						//update right side marker
						st = stack.get(stack.size() - 1);
						st.updateRightMarker(1);
						rightMarker = 1;
					}
				}
			}
			//Removing completed elements
			if (leftMarker == 1 && rightMarker == 1) {
				merge(list, start, middle, middle + 1, end);
				stack.remove(stack.size() - 1);
				flag = 1;
			}
			// ************************
			if (stack.size() > 0) {
				start = stack.get(stack.size() - 1).getLeft();
				end = stack.get(stack.size() - 1).getRight();
				leftMarker = stack.get(stack.size() - 1).leftMarker;
				rightMarker = stack.get(stack.size() - 1).rightMarker;
				//Updating previous left side or right tree element
				if (flag == 1) {
					if (leftMarker == 0) {
						st = stack.get(stack.size() - 1);
						st.updateLeftMarker(1);
						leftMarker = 1;
					} else if (rightMarker == 0) {
						st = stack.get(stack.size() - 1);
						st.updateRightMarker(1);
						rightMarker = 1;
					}
				}
				flag = 0;
			}
			// ************************
			else {
				//break both the loops when stack is empty
				break secondLoop;
			}
		} while (true);
	}
	
	 /** Procedure to Merge the two sorted list
    *
    * @param x: LinkedList : Integer.
    * @param y: left : Int: points to the first element in the 1st sorted list
    * @param y: leftEnd : Int: points to the last element in the 1st sorted list
    * @param y: right : Int: points to the first element in the 2st sorted list
    * @param y: rightEnd : Int: points to the last elements in the 2st sorted list

    */

	public void merge(List<T> list, int left, int leftEnd, int right, int rightEnd) {
		// Copy only the sub list to sort and merge into Temp arraylist
		List<T> tmpList = new ArrayList<>(right - left + 1);
		int i = left;
		int j = right;

		// Copy which ever is smaller
		while (i <= leftEnd && j <= rightEnd) {
			if (list.get(i).compareTo(list.get(j)) < 0) {
				tmpList.add(list.get(i++));
			} else {
				tmpList.add(list.get(j++));
			}
		}
		// Copy remaining elements
		while (i <= leftEnd) {
			tmpList.add(list.get(i++));
		}
		while (j <= rightEnd) {
			tmpList.add(list.get(j++));
		}
		// Replace the actual list with sorted portion in the temp list
		for (int k = 0; k < tmpList.size(); k++)
			list.set(k + left, tmpList.get(k));
	}

	public void println() {
		for (T item : this.list) {
			System.out.print(item + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int n = 10000;
		MergeSortStackImpl<Integer> ms = new MergeSortStackImpl<>();
		Scanner in = new Scanner(System.in);

		for (int i = n; i > 0; i--) {
				ms.list.add(i);
		}
		in.close();
		System.out.println("Before sorting :: ");
		ms.println();
		Timer timer = new Timer();
		ms.mergeSort(0, ms.list.size() - 1);

		System.out.println(timer.end());
		System.out.println("After sorting :: ");
		ms.println();
	}
}