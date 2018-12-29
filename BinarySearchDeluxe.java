package cs12b;
import java.util.Arrays;
import java.util.Comparator;
/*
 * Tennessee Philips Ward, 1614708, and Kelsy Lee, 1587641
 * Class 12B
 * BinarySearchDeluxe.java
 * This program takes in the comparators created in Term.java and finds the first index and last index of a term
 * from an array of terms based on weight or alphabetical order
 * see main for test examples
 */
public class BinarySearchDeluxe {

//returns the index of the first key in a[] that equals the search key or -1 if no such key
	public static <Key> int firstIndexOf (Key[] a, Key key, Comparator<Key> comparator) {
		//if parameters is null, nullpointerexception
		if (a == null || key == null || comparator == null) {
			throw new NullPointerException();
		}
		
		int low = 0, high = a.length - 1;
		int mid = (low+high)/2;
		final int in = mid, lowa = low, higha = high;
			
		//base cases
		if (low < high) {
			//if a[mid] has key in the first try, go up until you reach first term with key
			if (comparator.compare(a[mid], key) == 0) {
				for (int i = in; i > 0; i--) {
					if (comparator.compare(a[mid-1], key) == 0) {
						mid--;
					}
				}
				return mid;
			}
			//if you narrowed it down to 2 items in the array, check if low or high is key
			if (a.length == 2) {
				if (comparator.compare(a[low], key) == 0) {
					return low;
				}
				if (comparator.compare(a[high], key) == 0) {
					return high;
				}
				return -1;
			}
			
			//if mid comes after key alphabetically, recursively call on first half of a[]
			else if (comparator.compare(a[mid], key) > 0) {
				Key[] temp = Arrays.copyOfRange(a, lowa, in);
				return low + firstIndexOf(temp, key, comparator);
			}
			
			//if mid comes before key alphabetically, recursively call on second half of a[]
			else if (comparator.compare(a[mid], key) < 0) {
				Key[] temp = Arrays.copyOfRange(a, in, a.length);
				return mid+ firstIndexOf(temp, key, comparator);
			}
		}
		
		//you recursively called until there is only one element, if that element is key, return index of element
		if (low == high) {
			if (comparator.compare(a[high], key) == 0) {
				return low;
			}
		}
		
		//if none of the elements in a[] is key, return -1
		return -1;
		
	}
	
//returns the index of the last key in a[] that equals the search key or -1 if no such key
	public static <Key> int lastIndexOf (Key[] a, Key key, Comparator<Key> comparator) {
		// if not null good ese yell
		if (a == null || key == null || comparator == null) {
			throw new NullPointerException("Not enought arguments. Check call to lastIndexOf");
		}
		
		int low = 0, high = a.length - 1;
		int mid = (low+high)/2;
		final int in = mid, lowa = low, higha = high;
			
		//base cases
		if (low < high) {
			//if first time, mid = key, index keep going bigger until find last instance of key
			if (comparator.compare(a[mid], key) == 0) {
				for (int i = in; i > 0; i--) {
					if (comparator.compare(a[mid+1], key) == 0) {
						mid++;
					}
				}
				return mid;
			}
			
			//if there is two elements in the array, check if high or low is key
			if (a.length == 2) {
				if (comparator.compare(a[high], key) == 0) {
					return high;
				}
				if (comparator.compare(a[low], key) == 0) {
					return low;
				}
				return -1;
			}
			
			//if mid is after key alphabetically, recursive on first half of a[]
			else if (comparator.compare(a[mid], key) > 0) {
				Key[] temp = Arrays.copyOfRange(a, lowa, in);
				return low + lastIndexOf(temp, key, comparator);	
			}
			
			//if mid is before key alphabetically, recursive on second half of a[]
			else if (comparator.compare(a[mid], key) < 0) {
				Key[] temp = Arrays.copyOfRange(a, in, a.length);
				return mid+ lastIndexOf(temp, key, comparator);
			}
		}
		//if one element in array, check if equals key
		if (low == high) {
			if (comparator.compare(a[high], key) == 0) {
				return low;
			}
		}
		
		//if none of elements matches key
		return -1;
		
	}
	
//unit testing required
	public static void main(String[] args) {
		Term[] terms = {new Term("oief",1), new Term("dsfgh", 2), new Term("fsdgherth",69), new Term("fsml", 69), new Term("fskys", 420), new Term("birb", 4)};
		Arrays.sort(terms, Term.byPrefixOrder(2));
		System.out.println(Arrays.toString(terms));
		System.out.println(BinarySearchDeluxe.firstIndexOf(terms, new Term("fs", 2), Term.byPrefixOrder(2)));
		System.out.println(BinarySearchDeluxe.lastIndexOf(terms, new Term("fs", 1), Term.byPrefixOrder(2)));
		
		Arrays.sort(terms, Term.byReverseWeightOrder());
		System.out.println(Arrays.toString(terms));
		System.out.println(BinarySearchDeluxe.lastIndexOf(terms, new Term("fs", 69), Term.byReverseWeightOrder()));
		System.out.println(BinarySearchDeluxe.firstIndexOf(terms, new Term("fs", 69), Term.byReverseWeightOrder()));
		
	}
}
