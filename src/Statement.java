/**
 * The Statement class represents a knowledge base entry with a term, 
 * a sentence, and a confidence score. It implements the Comparable interface 
 * to allow comparison based on the term.
 * 
 * @author Ziyanda Mthethwa
 * @date 2025-03-19
 */

 public class Statement implements Comparable<Statement>{
    private String term;
    private String sentence;
    private double confidenceScore;

    
    

    /**
     * Constructs a new Statement object.
     * 
     * @param term The term associated with the statement.
     * @param sentence The sentence or description of the term.
     * @param confidenceScore The confidence score of the statement.
     */
    public Statement(String term, String sentence, double confidenceScore){
        this.term = term;
        this.sentence = sentence;
        this.confidenceScore = confidenceScore;
    }
    /**
     * Gets the term of the statement.
     * 
     * @return The term of the statement.
     */
    public String getTerm(){
        return term;
    }
/**
     * Gets the sentence of the statement.
     * 
     * @return The sentence of the statement.
     */
    public String getSentence(){
        return sentence;
    }
    /**
     * Gets the confidence score of the statement.
     * 
     * @return The confidence score of the statement.
     */
    public double getConfidenceScore(){
        return confidenceScore;
    }
     /** 
     * Sets the term of the statement.
     * 
     * @param term The new term to set.
     */

    
    public void setTerm(String term){
        this.term = term;
    }
    /** 
     * Sets the term of the statement.
     * 
     * @param term The new term to set.
     */

     /**
     * Sets the sentence of the statement.
     * 
     * @param sentence The new sentence to set.
     */
    public void setSentence(String sentence){
        this.sentence = sentence;
    }
    

     /**
     * Sets the confidence score of the statement.
     * 
     * @param confidenceScore The new confidence score to set.
     */
    public void setConfidenceScore(double confidenceScore){
        this.confidenceScore = confidenceScore;
    }
    

      /**
     * Returns a string representation of the statement.
     * 
     * @return A string containing the term, sentence, and confidence score.
     */
    @Override
    public String toString(){
        return "Term: " + term + " Sentence: " + sentence + " Confidence Score: " + confidenceScore;
    }
    
    /**
     * Compares this statement to another statement based on the term.
     * 
     * @param other The other statement to compare to.
     * @return A negative integer, zero, or a positive integer as this term 
     *         is lexicographically less than, equal to, or greater than the other term.
     */ 
    @Override
    public int compareTo(Statement other) {
        return this.term.compareTo(other.term);
    }
 }