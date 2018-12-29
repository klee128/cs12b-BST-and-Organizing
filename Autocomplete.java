package cs12b;
import java.util.Arrays;
/*
 * Tennessee Philips Ward, 1614708, and Kelsy Lee, 1587641
 * Class 12B
 * Autocomplete.java
 * This program takes information from Term.java and BinarySearchDeluxe.java to find all the terms with matching prefixes
 * and the number of terms with said prefix
 * Using BinarySearchDeluxe.java methods, you can create a new array of terms that is sorted by prefix order or by 
 * reverse weight. 
 * See main for test examples 
 */
public class Autocomplete {
//initiallizes the data structure from given array of temrs
	Term[] aTerm;
	public Autocomplete(Term[] terms) {
		aTerm = terms;
	}
	
//returns all terms that start with given prefix in descending order of weight
	
	public Term[] allMatches(String prefix) {
		//aTerm.BinarySearchDeluxe.firstIndexOf(terms, new Term(prefix, 0), Term.byPrefixOrder(2)));
		Arrays.sort(aTerm, Term.byPrefixOrder(prefix.length()));
		int first = BinarySearchDeluxe.firstIndexOf(aTerm, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
		int last = BinarySearchDeluxe.lastIndexOf(aTerm, new Term(prefix, 0), Term.byPrefixOrder(prefix.length()));
		Term[] temp = Arrays.copyOfRange(aTerm, first, last+1);
		Arrays.sort(temp, Term.byReverseWeightOrder());
		return temp;
		
	}
	
//returns number of terms that start with given prefix
	public int numOfMatches(String prefix) {
		Arrays.sort(aTerm, Term.byPrefixOrder(prefix.length()));
		if (BinarySearchDeluxe.lastIndexOf(aTerm, new Term(prefix,0), Term.byPrefixOrder(prefix.length())) - BinarySearchDeluxe.firstIndexOf(aTerm, new Term(prefix,0), Term.byPrefixOrder(prefix.length())) == 0){
			return 0;
		}
		return 1 + BinarySearchDeluxe.lastIndexOf(aTerm, new Term(prefix,0), Term.byPrefixOrder(prefix.length())) - BinarySearchDeluxe.firstIndexOf(aTerm, new Term(prefix,0), Term.byPrefixOrder(prefix.length()));
	}
	
//unit testing makes me cry
	public static void main(String[] args) {
		Term[] terms = {new Term("oief",1), new Term("dsfsgh", 2), new Term("fsdgherth",3), new Term("fsml", 69), new Term("fskys", 420), new Term("birb", 4)};
		System.out.println(Arrays.toString(new Autocomplete(terms).allMatches("fs")));
		System.out.println(new Autocomplete(terms).numOfMatches("fs"));
		System.out.println(new Autocomplete(terms).numOfMatches("fsz"));
	}

	
}
