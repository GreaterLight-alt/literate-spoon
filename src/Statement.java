/*@ author: Ziyanda Mthethwa
@date: 2025-03-19
 * this is the class that
 */

 public class Statement implements Comparable<Statement>{
    private String term;
    private String sentence;
    private double confidenceScore;

    
    
    /* 
     * This is the constructor for the GenericsKbAVL class
     * @param term: 
     * @param sentence
     * @param confidenceScore
    */
    public Statement(String term, String sentence, double confidenceScore){
        this.term = term;
        this.sentence = sentence;
        this.confidenceScore = confidenceScore;
    }
    /*
     * 
     */
    public String getTerm(){
        return term;
    }
    /*
     * 
     */
    public String getSentence(){
        return sentence;
    }
    /*
     * 
     */
    public double getConfidenceScore(){
        return confidenceScore;
    }
    /*
     * 
     */
    public void setTerm(String term){
        this.term = term;
    }
    /*
     * 
     */
    public void setSentence(String sentence){
        this.sentence = sentence;
    }
    /*
     * 
     */
    public void setConfidenceScore(double confidenceScore){
        this.confidenceScore = confidenceScore;
    }
    /*
     * 
     */
    @Override
    public String toString(){
        return "Term: " + term + " Sentence: " + sentence + " Confidence Score: " + confidenceScore;
    }
    /*
     * compare statement by the term
     */
    @Override
    public int compareTo(Statement other) {
        return this.term.compareTo(other.term);
    }
 }