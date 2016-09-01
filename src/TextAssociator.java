import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* Yumeng Wang
 * CSE 373, Summer 2016
 * Homework 3: TextAssociator
 * 
 * TextAssociator represents a collection of associations between words.
 * See write-up for implementation details and hints
 * 
 */
public class TextAssociator {
	private WordInfoSeparateChain[] table;   // a table of WordInfoSeparateChain
	private int size;                        // total elements in the table
	
	/* INNER CLASS
	 * Represents a separate chain in your implementation of your hashing
	 * A WordInfoSeparateChain is a list of WordInfo objects that have all
	 * been hashed to the same index of the TextAssociator
	 */
	private class WordInfoSeparateChain {
		private List<WordInfo> chain;
		
		/* Creates an empty WordInfoSeparateChain without any WordInfo
		 */
		public WordInfoSeparateChain() {
			this.chain = new ArrayList<WordInfo>();
		}
		
		/* Adds a WordInfo object to the SeparateCahin
		 * Returns true if the WordInfo was successfully added, false otherwise
		 */
		public boolean add(WordInfo wi) {
			if(!this.chain.contains(wi)){
				return this.chain.add(wi);
			}
			return false;
		}
		
		/* Removes the given WordInfo object from the separate chain
		 * Returns true if the WordInfo was successfully removed, false otherwise
		 */
		public boolean remove(WordInfo wi) {
			if(this.chain.contains(wi)){
				return this.chain.remove(wi);
			}
			return false;
			// TODO: implement as explained in spec
		}
		
		// Returns the size of this separate chain
		public int size() {
			return chain.size();
		}
		
		// Returns the String representation of this separate chain
		public String toString() {
			return chain.toString();
		}
		
		// Returns the list of WordInfo objects in this chain
		public List<WordInfo> getElements() {
			return chain;
		}
	}
	
	
	/* Creates a new TextAssociator without any associations 
	 */
	public TextAssociator() {
		this.size = 0;
		this.table = new WordInfoSeparateChain[101];
	}
	
	
	/* Adds a word with no associations to the TextAssociator 
	 * Returns False if this word is already contained in your TextAssociator ,
	 * Returns True if this word is successfully added
	 */
	public boolean addNewWord(String word) {
		// rehash table if the load factor is greater or equal than 1
		if(this.size / this.table.length >= 1){
			rehash();
		}
		int hashVal = Math.abs(word.hashCode() % this.table.length);
		WordInfo pendingWord = new WordInfo(word);
		if(this.table[hashVal] == null){
			this.table[hashVal] = new WordInfoSeparateChain();
			this.size++;
			return this.table[hashVal].add(pendingWord);
		}else if(findWordInfo(hashVal, word) == null){
			this.size++;
			return this.table[hashVal].add(pendingWord);
		}
		return false;
	}
	
	
	/* Adds an association between the given words. Returns true if association correctly added, 
	 * returns false if first parameter does not already exist in the TextAssociator or if 
	 * the association between the two words already exists
	 */
	public boolean addAssociation(String word, String association) {
		int hashVal = Math.abs(word.hashCode() % this.table.length);
		WordInfo currentWord = findWordInfo(hashVal, word);
		if(currentWord != null && !currentWord.getAssociations().contains(association)){
			return currentWord.addAssociation(association);
		}
		return false;
	}
	
	
	/* Remove the given word from the TextAssociator, returns false if word 
	 * was not contained, returns true if the word was successfully removed.
	 * Note that only a source word can be removed by this method, not an association.
	 */
	public boolean remove(String word) {
		int hashVal = Math.abs(word.hashCode() % this.table.length);
		WordInfo currentWord = findWordInfo(hashVal, word);
		if(currentWord != null){
			return this.table[hashVal].remove(currentWord);
		}
		return false;
	}
	
	
	/* Returns a set of all the words associated with the given String  
	 * Returns null if the given String does not exist in the TextAssociator
	 */
	public Set<String> getAssociations(String word) {
		int hashVal = Math.abs(word.hashCode() % this.table.length);
		WordInfo currentWord = findWordInfo(hashVal, word);
		if(currentWord != null){
			return currentWord.getAssociations();
		}
		return null;
	}
	
	
	/* Prints the current associations between words being stored
	 * to System.out
	 */
	public void prettyPrint() {
		System.out.println("Current number of elements : " + size);
		System.out.println("Current table size: " + table.length);
		
		//Walk through every possible index in the table
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				WordInfoSeparateChain bucket = table[i];
				
				//For each separate chain, grab each individual WordInfo
				for (WordInfo curr : bucket.getElements()) {
					System.out.println("\tin table index, " + i + ": " + curr);
				}
			}
		}
		System.out.println();
	}
	
	// A help method to find is the given word already in the dash table
	// return the word if found, otherwise return null
	private WordInfo findWordInfo(int hashVal, String word){
		if(this.table[hashVal] != null){
			for(WordInfo curr : this.table[hashVal].getElements()){
				if(word.equals(curr.getWord())){
					return curr;
				}
			}
		}
		return null;
	}
	
	// A function double the current hash table size to increase efficiency
	private void rehash() {
		WordInfoSeparateChain[] oldTable = this.table;
		this.table = new WordInfoSeparateChain[2 * this.table.length];
		for(int i = 0; i < oldTable.length; i++){
			if (oldTable[i] != null) {
				WordInfoSeparateChain bucket = oldTable[i];
				// For each separate chain, grab each individual WordInfo
				for (WordInfo curr : bucket.getElements()) {
					int hashVal = Math.abs(curr.getWord().hashCode() % this.table.length); 
					// Create a WordInfoSeparateChain if current position has no chains
					if(this.table[hashVal] == null) {
						this.table[hashVal] = new WordInfoSeparateChain();
					}
					this.table[hashVal].add(curr);
				}
			}
		}
	}
}
