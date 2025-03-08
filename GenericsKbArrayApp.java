/* mthziy010
2025/03/05
this class the main method and handle the user interface(text-base menu)
this shows the menu and awaits the users input
*/ 
import java.util.Scanner;

public class GenericsKbArrayApp{

public static void main(String[]args) {
    Scanner sc = new Scanner(System.in);
    boolean running = true;
    //load the file
GenericsKb.loadFromFile("GenericsKB.txt");
    while(running){
        // Display the menu 
        System.out.println("Choose an action from the menu: ");
        System.out.println("1. Load a knowledge base from a file");
        System.out.println("2. Add a new statement to the knowledge base");
        System.out.println("3. Search for a statement in the knowledge base by term");
        System.out.println("4. Search for a statement in the knowledge base by term and sentence");
        System.out.println("5. Quit");
        System.out.print("Enter your choice: ");
        
        String choice = sc.nextLine(); 
        //Switch Case
        switch(choice) {
            case "1":
            // implement file loading
            System.out.print
            ("Enter file name: ");
            String filename = sc.nextLine();
            GenericsKb.loadFromFile(filename);
            break;
            case "2":
            
            System.out.print("Enter the term: ");
            String term = sc.nextLine();
            System.out.print("Enter the statement: ");
            String statement = sc.nextLine();
            System.out.print("Enter the confidence score:");
            double confidenceScore = Double.parseDouble(sc.nextLine());
            boolean updated = GenericsKb.updateStatement(term,statement,confidenceScore);
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
            GenericsKb result= GenericsKb.findByTermKb(searchTerm);
            if(result != null){
                
                System.out.println(result);
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
          double score =  GenericsKb.findByTermKbandStatement(termString, statemString);
          if(score>=0){
            System.out.println("The statement was found and has confidence score of" + score + ".");
          }else{
            System.out.println("The statement was not found.");
          }
            break;
            case "5":
            running = false;
            System.out.println("Quit");
            break;
            default:
            System.out.println("Invalid choice, please try again.");
            
        }

    }
    sc.close();  
  
}
}