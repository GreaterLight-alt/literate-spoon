/*

 * this class represents a data  in our knowledge base
 * what is stores the term, statement and confidenceScore
 * @author Ziyanda Mthethwa MTHZIY010
 * @since 2025/03/09
 */

public class Statement implements Comparable<Statement>{
    private String term;
    private String statement;
    private double confidenceScore;

    public Statement(String term ,String statement,double confidenceScore){
        this.term = term;
        this.statement = statement;
        this.confidenceScore = confidenceScore;
       }
       public String getTerm(){
        return term;
     } 
     public String getStatement(){
        return statement;
     }
     public double getConfidence(){
        return confidenceScore;
     }
     @Override 
     /* method that compares two statements by comparing their terms
      * 
     */
     public int compareTo(Statement other){
        return this.term.compareToIgnoreCase(other.term);
     }
     @Override

public String toString(){
    return statement + "(Confidence score: " + confidenceScore + ")";
}
}