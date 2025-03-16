/*
 * @ author Ziyanda Mthethwa
 *
 * 
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GenericsKbBST{
//constructor to initialize the BST
    private BinarySearchTree<Statement> statementTree;
    
    public  GenericsKbBST(){
        statementTree = new BinarySearchTree<>();
    }
    //
    /* Method for loading, search , add data from file 
     * @param takes a filename as a String parameter
      this method throws an IOException if there is a issue reading the file
     */
    public int loadFromFile(String filename)throws IOException{
        int count = 0;
    File file = new File(filename);
    if (!file.exists()){
        throw new IOException("File not found: "+ filename);
    }
   try(BufferedReader reader = new BufferedReader(new FileReader(filename))){ // opens the file uses the FileReader
    String line;
    while ((line = reader.readLine()) != null){
        // split by tabs
        String[] parts = line.split("\t");
        if (parts.length == 3){
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
/**
     * Adds or updates a statement in the BST.
     * Updates only if the new confidence score is higher.
     * @param term The term to add/update
     * @param statement The statement associated with the term
     * @param confidenceScore The confidence score of the statement
     * @return True if the statement was added or updated, false otherwise
     */
public boolean addOrUpdateStatement(String term, String statement , double confidenceScore){
    Statement  newStatement = new Statement(term, statement, confidenceScore);
    // check if we already have this term
    BinaryTreeNode<Statement> existingNode = statementTree.find(newStatement );
    if(existingNode != null){
        //only replace if the new confidence is higher
        if (confidenceScore > existingNode.data.getConfidence()){
            existingNode.data.setStatement(statement);
            existingNode.data.setConfidence(confidenceScore);
            return true;
        }
            return false;// no update if confidence score is lower
        }
    
        // term does not exist yet, add it
        statementTree.insert(newStatement);
        return true;
    }



/**
     * Searches for a term in the BST.
     * @param term The term to search for
     * @return The corresponding Statement if found, or null if not found
     */
public Statement searchByTerm(String term){
    //create a dummy statement with the search term
    Statement dummyStatement = new Statement(term, "", 0);
    BinaryTreeNode<Statement> node = statementTree.find(dummyStatement);
    return node != null ? node.data :null;
}


/**
     * Searches for a term and statement in the BST.
     * @param term The term to search for
     * @param sentence The statement to search for
     * @return The corresponding Statement if found, or null if not found
     */
    public Statement searchByTermAndSentenceStatement(String term ,String sentence){
        Statement foundStatement = searchByTerm(term);
        if(foundStatement != null && foundStatement.getStatement().equals(sentence)){
            return foundStatement;
        }

return null;
}
}

      
      
    

      
     