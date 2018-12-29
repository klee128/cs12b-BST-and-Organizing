package cs12b;
import java.util.Arrays;
import java.util.Comparator;
/*
 * Tennessee Philips Ward, 1614708, and Kelsy Lee, 1587641
 * Class 12B
 * Term.java
 * This program creates Terms with a String query and a long weight. Using this information, we can compare two terms 
 * against one another alphabetically and by weight. 
 * see main for test examples
 */

public class Term implements Comparable<Term>{

String cary;
long wait;
//initializes a term with the given query string and weight
	public Term(String query, long weight) {
		if (query == null) {
			throw new NullPointerException();
		}
		if (weight < 0) {
			throw new IllegalArgumentException();
		}
		cary = query;
		wait = weight;
	}

//compares the two terms in descending order by weight
	public static Comparator<Term> byReverseWeightOrder(){
		return new Comparator<Term>() {
			public int compare(Term a, Term b) {
				long num1 = a.wait;
				long num2 = b.wait;
				if (num1 < num2)
					return -1;
				else if (num1 > num2)
					return 1;
				return 0;
			}
		};
		
		
	}

//compares two terms in lexicographic order but using only the first r characters of each query	
	public static Comparator<Term> byPrefixOrder(int r){
		if (r < 0) {
			throw new IllegalArgumentException();
		}
		return new Comparator<Term>() {
			public int compare(Term a, Term b) {
				String que1 = a.cary.substring(0,r);
				String que2 = b.cary.substring(0,r);
				return que1.compareTo(que2);
			}
		};
	}

//compares the two terms in lexicographic order by query	
	public int compareTo(Term that) {
		int len;
		if (this.cary.length() < that.cary.length())
			len = this.cary.length();
		else
			len = that.cary.length();
		
		for (int i = 0; i < len; i++) {
			if ((int)this.cary.charAt(i) < (int)that.cary.charAt(i))
				return -1;
			if ((int)this.cary.charAt(i) > (int)that.cary.charAt(i))
				return 1;
			
		}
		
		if (this.cary.length() < that.cary.length()) {
			return -1;
		}
		if (this.cary.length() > that.cary.length()) {
			return 1;
		}
		
		return 0;
		
	}

//returns a string representation of this term in the following format
//the weight, followed by a tab, followed by the query
	public String toString() {
		return wait + "\t" + cary;
		
	}

//unit testing (required)	
	public static void main(String[] args) {
		Term[] terms = {new Term("charlie",3), new Term("chase",5), new Term("charlene",10)};
		System.out.println(Arrays.toString(terms));
		Arrays.sort(terms, Term.byPrefixOrder(5));
		System.out.println(Arrays.toString(terms));
		Arrays.sort(terms, Term.byReverseWeightOrder());
		System.out.println(Arrays.toString(terms));
		System.out.println(Term.byPrefixOrder(5).compare(terms[0], terms[1]));
	}
	
}
