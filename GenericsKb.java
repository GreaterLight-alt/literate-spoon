import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**This class represents a single entry into the array(the term, statement and the array) in the Knowledge base and includes static methods  to manage the knowledge base for the knowledgeKbApp class
 *  @author Ziyanda Mthethwa
 * @version 2025/03/07
 * */ 
public class GenericsKb {
    // Instance variables for a single knowledge entry
    private String term;
    private String statement;
    private double confidenceScore = 0.0;
    private static GenericsKb[] kbArray; //Array to store objects
    private static int lineCount; // to store the number of lines
    private static  int currentSize = 0; //to track  number of entries currently stored

    // constructor to initialise an entry

public GenericsKb(String term ,String statement,double confidenceScore){
    this.term = term;
    this.statement = statement;
    this.confidenceScore = confidenceScore;
   }
   
   /**
    * counts the number of lines in a file
    * @param file
    * @return the number of line in the file
    * @throws FileNotFoundException if the file cannot be found
    */
   public static int countLines(File file) throws FileNotFoundException{
      Scanner scanner  = new Scanner(file);
      int count = 0;
      while(scanner.hasNextLine()){
         scanner.nextLine();
         count++;
      }
      scanner.close();
      return count;
   }

/**
 * loads knowledge base entries from a file
 * @param filename the name  offile to read
 * @return the array of objects loaded from file
 */
public static GenericsKb[] loadFromFile(String filename){
   try{
      File file = new File(filename);
      lineCount = countLines(file); // Count the number of lines
      kbArray = new GenericsKb[lineCount];//Initialise the array
      
      Scanner scanner = new Scanner(file);
      int index = 0;
      while(scanner.hasNextLine()){
         String line = scanner.nextLine();
         String[] parts = line.split("\t");
      if (parts.length == 3){
         String term = parts[0];
         String statement = parts[1];
         double confidenceScore = Double.parseDouble(parts[2]);

      
      kbArray[index++] = new GenericsKb(term, statement, confidenceScore);
      currentSize++; //Increment current size
     
      }
      }
      scanner.close();
      System.out.println("Knowledge base loaded successfully.");
      return kbArray;
   }catch(FileNotFoundException e){
      System.out.println("File not found. "+ filename);
      return null;
   }
 
}
/**
 * update an existing knowledge base entry or adds a new one
 * @param term   to update /add
 * @param statement 
 * @param confidenceScore
 * @return if updated/false if not
 */
public static boolean updateKnowledge(String term, String statement, double confidenceScore){
   for(int i =0; i < currentSize; i++){
   if (kbArray[i].getTerm().equalsIgnoreCase(term)){
      //update if score is higher
      if(confidenceScore > kbArray[i].getConfidence()){
      kbArray[i].setStatement(statement);
      kbArray[i].setconfidence(confidenceScore);
return true;
      }
      return false; // terms exists but not updated
   }
}
// if we only add if there in enough space 
if (currentSize < kbArray.length){
   kbArray[currentSize++] = new GenericsKb(term, statement, confidenceScore);
   return true;
}
return false; // no space to add the entry
}
/**
 * Find knowledge base entry by term
 * @param term to search for 
 * @return GenericsKb object if found, null otherwise
 * 
 */

 public static GenericsKb findByTermKb(String term){
   for(int i =0 ; i < lineCount; i++){
      if (kbArray[i]!= null && kbArray[i].getTerm().equalsIgnoreCase(term)){
         return kbArray[i];
      }
   }
return null;
      
}

/**
 * Update or add a statement to knowledge base
 * @param term
 * @param statement
 * @param confidenceScore
 * @return true if updated or added , false otherwise
 */
// method to add or update a new statement to the array
public static boolean updateStatement (String term,String statement, double confidenceScore){
   //first check if the statement exits
   for(int i = 0 ; i < currentSize; i++){
if (kbArray[i]  != null && kbArray[i].getTerm().equalsIgnoreCase(term)){
   // update if the confidenceScore is higher
   if(confidenceScore > kbArray[i].getConfidence()){
      kbArray[i].setStatement(statement);
      kbArray[i].setconfidence(confidenceScore);
      return true;
   }
   return false;
}

}

// Add new entry if it doesn't exist and there's space
if (currentSize < kbArray.length){
   kbArray[currentSize++] = new GenericsKb(term, statement, confidenceScore);
   return true;
}
return false;
}

/*Checks if a specific  statement about a term a term exists in the knowledge base
 * @param term  The term to check
 * @param statement Statement 
 * @return the confidence score if found , -1 otherwise
 * 
 */
public static double findByTermKbandStatement(String term,String statement){
   for(int i = 0; i < currentSize; i++){
      if(kbArray[i] != null && kbArray[i].getTerm().equalsIgnoreCase(term)  && kbArray[i].getStatement().equalsIgnoreCase(statement)){
         return kbArray[i].getConfidence();
      }

   }
   return -1.0; // not found
}

// getters 
 
public String getTerm(){
    return term;
 } 
 public String getStatement(){
    return statement;
 }
 public double getConfidence(){
    return confidenceScore;
 }
 //setters for
 public void setStatement(String statement){
    this.statement = statement;

 }
 public void setTerm(String term){
    this.term= term;
}
public void setconfidence(double confidenceScore){
    this.confidenceScore = confidenceScore;

}

@Override
public String toString(){
   return "Statement found: " + statement + "(Confidence score: " + confidenceScore +")";
}
}
