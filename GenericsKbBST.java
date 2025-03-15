/*
 * @ author Ziyanda Mthethwa
 *
 * 
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GenericsKbBST{

    private BinarySearchTree<Statement> statementTree;
    public  GenericsKbBST(){
        statementTree = new BinarySearchTree<>();
    }
    //
    /* Method for loading, search , add data from file 
     * takes a filename as a String parameter
     * this method throws an IOException if there is a issue reading the file
     */
    public int loadFromFile(String filename)throws IOException{
        int count = 0;
   try(BufferedReader reader = new BufferedReader(new FileReader(filename))){ // opens the file uses the FileReader
    String line;
    while ((line = reader.readLine()) != null){
        // split by tabs
        String[] parts = line.split("\t");
        if (parts.length >= 3){
           String term = parts[0];
           String statement = parts[1];
           double confidenceScore = Double.parseDouble(parts[2]);
           addOrUpdateStatement(term, statement, confidenceScore);
           count++;
        }
    }

    }
    return count;
   }
// adds new statements /updates existing ones
public void  addOrUpdateStatement(String term, String sentence , double confidenceScore){
    Statement  newStatement = new Statement(term, sentence, confidenceScore);
    // check if we already have this term
    BinaryTreeNode<Statement> existingNode = statementTree.find(newStatement );
    
        //only replace if the new confidence is higher
        if (confidenceScore > existingNode.data.getConfidence()){
            statementTree.delete(existingNode.data);
            statementTree.insert(newStatement);

        }

        else{
            // term doesnot exist yet ,add it
            statementTree.insert(newStatement);
        }
    }

// finds a statement by its term
public Statement searchByTerm(String term){
    //create a dummy statement with the search term
    Statement dummyStatement = new Statement(term, "", 0);
    BinaryTreeNode<Statement> node = statementTree.find(dummyStatement);
    return node != null ? node.data :null;
}


    //searching by term and sentence, checking if a specific statement exists
    public Statement searchByTermAndSentenceStatement(String term ,String sentence){
        Statement foundStatement = searchByTerm(term);
        if(foundStatement != null && foundStatement.getStatement().equals(sentence)){
            return foundStatement;
        }

return null;
}
}

      
      
    

      
     