/*Main Appllication class for the BST-base knowledge base
@author Ziyanda Mthethwa MTHZIY010
@ SINCE 2025/03/14
 * 
 */

import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class GenericsKbBSTApp{
   public static  void  main String(String[] args) {
    Scanner sc = new Scanner(System.in);
    GenericsKbBST knowledgeBase = new GenericsKbBST();
    boolean running = true;
    GenericsKbBST kb  = new GenericsKbBST();
    while (running){
        try{
            // Display the menu 
            System.out.println("\nChoose an action from the menu: ");
            System.out.println("1. Load a knowledge base from a file");
            System.out.println("2. Add a new statement to the knowledge base");
            System.out.println("3. Search for a statement in the knowledge base by term");
            System.out.println("4. Search for a statement in the knowledge base by term and sentence");
            System.out.println("5. Quit");
            System.out.print("Enter your choice (1-5): ");
        
        String choice = sc.nextLine();
        //validate the choice
        if(!choice.matches("[1-5]")){
            throw new IllegalArgumentException("Invalid choice menu.")
        }
        switch(choice){
            case "1":
            // implement file loading
            System.out.print
            ("Enter file name: ");
            String filename = sc.nextLine();
            //check if file exists
            File file = new File(filename);
            if(!file.exists()){throw new FileNotFoundException("File is not found: "+ filename);
        }
            break;
            case "2";
            System.out.print("Enter the term: ");
            String term = sc.nextLine();
            System.out.print("Enter the statement: ");
            String statement = sc.nextLine();
            System.out.print("Enter the confidence score:");
            double confidenceScore = Double.parseDouble(sc.nextLine());
            boolean updated = kb.addOrUpdateStatement(term,statement,confidenceScore);
            if(updated ){
                System.out.println("Statement for term "+ term + " has been updated.");
            }
            else{
                System.out.println("Statement for term "+ term + " could not be updated.");   
            }
            break;
            case "3":
    
            System.out.println("Enter the term to search: ");
            String searchTerm = sc.nextLine();
            Statement result= kb.searchByTermTermKb(searchTerm);
            if(result != null){
                
                System.out.println("Statement found:" +result);
            }
        else{
            System.out.println("No statement found for the term: "+ searchTerm);;
        }
            break;
            case "4":
        
            System.out.print("Enter the term: ");
            String termString = sc.nextLine();
            System.out.print("Enter the statement to search for: ");
            String statemString = sc.nextLine();
          Statement foundSta =  kb.searchByTermAndSentenceStatement(termString, statemString);
          if(foundSta != null){
            System.out.println("The statement was found and has confidence score of" + foundSta.getConfidence() + ".");
          }else{
            System.out.println("The statement was not found.");
          }
            break;
            case "5":
            running = false;
            System.out.println("Quit");
            break;
        }
    }catch (IllegalArgumentException e){
        //handle invalid input errors
        System.out.println("Error: " + e.getMessage());
    }
    catch (FileNotFoundException e){
        // handle file not found errors
        System.out.println("Error: " + e.getMessage());
    }catch (IOException e){
        Sytem.out.println("Error reading file: " + e.getMessage());
    }catch (Exception e){
        //Handle any other error
        System.out.println("Unexpected error occured: "+ e.getMessage());
        e.printStackTrace();
    }
}
           
sc.close();  
  
    }
}
  