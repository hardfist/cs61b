import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Scanner;

import sun.misc.Queue;

/**
 * Implements autocomplete on prefixes for a given dictionary of terms and weights.
 */
public class Autocomplete {
    /**
     * Initializes required data structures from parallel arrays.
     * @param terms Array of terms.
     * @param weights Array of weights.
     */
    private String[] terms;
    private double[] weights;
    private Trie trie = new Trie();
    public Autocomplete(String[] terms, double[] weights) {
        this.terms = terms;
        this.weights = weights;
        for(String str : terms)
        {
            trie.insert(str);
        }
    }

    /**
     * Find the weight of a given term. If it is not in the dictionary, return 0.0
     * @param term
     * @return
     */
    public double weightOf(String term) {
        for(int i=0;i<terms.length;i++)
        {
            if(terms[i].equals(term))
            {
                return weights[i];
            }
        }
        return 0.0;
    }

    /**
     * Return the top match for given prefix, or null if there is no matching term.
     * @param prefix Input prefix to match against.
     * @return Best (highest weight) matching string in the dictionary.
     */
    public String topMatch(String prefix) {
        ArrayList<String> preSet =  (ArrayList<String>) trie.prefixOf(prefix);
        String maxString = null;
        double maxWeight = 0;
        for(String str : preSet)
        {
            if(weightOf(str) > maxWeight)
            {
                maxString = str;
                maxWeight = weightOf(str);
            }
        }
        return maxString;
        
    }

    /**
     * Returns the top k matching terms (in descending order of weight) as an iterable.
     * If there are less than k matches, return all the matching terms.
     * @param prefix
     * @param k
     * @return
     */
    public Iterable<String> topMatches(String prefix, int k) {
        ArrayList<String> preSet = trie.prefixOf(prefix);
        PriorityQueue<String> pq = new PriorityQueue<String>(k,
                new Comparator<String>()
                {
                    @Override
                    public int compare(String lhs,String rhs)
                    {
                        double diff = weightOf(lhs) - weightOf(rhs);
                        if(diff<0) return -1;
                        else if(diff>0) return 1;
                        else return 0;
                        
                    }
                });
        for(String str : preSet)
        {
            if(pq.size() == k)
            {
                pq.remove();
            }
            pq.add(str);
        }
        ArrayList<String> res = new  ArrayList<String>();
        while(!pq.isEmpty())
        {
            res.add(pq.remove());
        }
        return res;
    }


    /**
     * Returns the highest weighted matches within k edit distance of the word.
     * If the word is in the dictionary, then return an empty list.
     * @param word The word to spell-check
     * @param dist Maximum edit distance to search
     * @param k    Number of results to return 
     * @return Iterable in descending weight order of the matches
     */
    public Iterable<String> spellCheck(String word, int dist, int k) {
        LinkedList<String> results = new LinkedList<String>();  
        /* YOUR CODE HERE; LEAVE BLANK IF NOT PURSUING BONUS */
        return results;
    }
    /**
     * Test client. Reads the data from the file, 
     * then repeatedly reads autocomplete queries from standard input and prints out the top k matching terms.
     * @param args takes the name of an input file and an integer k as command-line arguments
     */
    public static void main(String[] args) throws Exception{
        // initialize autocomplete data structure
       // In in = new In(new File("baby-names.txt"));
        //Scanner scanner = new Scanner(new File("input"));
        In in = new In(args[0]);
        int N = in.readInt();
        String[] terms = new String[N];
        double[] weights = new double[N];
        for (int i = 0; i < N; i++) {
            weights[i] = in.readDouble();   // read the next weight
            in.readChar();                  // scan past the tab
            terms[i] = in.readLine();       // read the next term
        }

        Autocomplete autocomplete = new Autocomplete(terms, weights);

        // process queries from standard input
        int k = Integer.parseInt(args[1]);
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            for (String term : autocomplete.topMatches(prefix, k))
                StdOut.printf("%14.1f  %s\n", autocomplete.weightOf(term), term);
        }
    }
}
