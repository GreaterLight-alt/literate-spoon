
/* This is the application to read in the attached text file 
@author: Ziyanda Mthethwa
@date: 2025-03-19
 * 
*/
import java.io.File; 
import java.io.FileNotFoundException;
import java.util.Scanner;


public class GenericsKbAVLApp {
    public static void main(String[] args) {
    
        Scanner keyboard = new Scanner(System.in);
        AVLTree knowledgebase = null;
    
try{
    int input = Integer.parseInt(keyboard.nextLine().trim());
    switch (input) {
        case 1:
            // Load knowledge base from file
            
                knowledgebase = loadKnowledgeBase(keyboard);
                break;
            

        case 2:
        performSearch(keyboard,knowledgebase);
        break;

        case 3:
        System.out.println("GoodBye");
        return;

        default:
        System.out.println("Invalid choice.Please try again");
            
}
    }catch (NumberFormatException e){
        System.out.println("Please enter a valid number.");
    }

    }
    /*
     * displays the main menu options
     */
        public static void displayMenu(){
            System.out.println("Choose an action from the menu:");
            System.out.println("1. Load a knowledge base from a file");
            System.out.println("2. Add a file with a list of words to perform a search");
            System.out.println("3. Quit");
            System.out.print("Enter your choice: ");
        }
    
     /*
     loads a knowledge base from a file

      */       
      private static AVLTree loadKnowledgeBase(Scanner keyboard){
        AVLTree knowledgeBase = new AVLTree();
        System.out.print("Enter a file name:");
        String fileName = keyboard.nextLine();
        try{
         File file = new File(fileName) ;
         Scanner scan = new Scanner(file);
         while(scan.hasNextLine()){
            String line = scan.nextLine();
            String[] parts = line.split("\t");
            if (parts.length == 3){
                Statement statement = new Statement(parts[0],parts[1], Double.parseDouble(parts[2]));
                knowledgeBase.insert(statement);
            }
         }
scan.close();
System.out.println("Knowledge base loaded successfully.");
return knowledgeBase;
        }catch(FileNotFoundException e){
            System.out.println("File not found: "+ fileName);
        }catch (NumberFormatException e){
            System.out.println("Error parsing confidence score in the file.");
        }

return null;

    }
    /*
     * performs searches from a query
     * 
     */
public static void performSearch(Scanner keyboard,AVLTree knowledgebase){
    //check if knowledge base is loaded
    if(knowledgebase == null)
    {
        System.out.println("load knowledge first");
        return;
    }
    System.out.println("Enter the query file: ");
    String fileName = keyboard.nextLine();
    try{
        File file = new File(fileName);
        Scanner scan = new Scanner(file);
        while(scan.hasNextLine()){
            String line = scan.nextLine().trim();

            //Reset counters before each search
            knowledgebase.resetCounters();
            //create a search statement
            Statement searchStatement = new Statement(line, "", 0);
BinaryTreeNode<Statement> result = knowledgebase.find(searchStatement);
if(result == null){
    System.err.println("Term not found: "+ line);
}else{
    Statement foundStatement = result.data;
    System.out.println(line + ":"+foundStatement.getSentence()+ " (Confidence: " + foundStatement.getConfidenceScore() +")");
    System.out.println("Search comparisons: "+knowledgebase.getSearchCount());
}
        }
        scan.close();

    }catch(FileNotFoundException e){
        System.out.println("File not found: "+ fileName);
    }

    }
}

         