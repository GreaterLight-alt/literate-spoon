
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
        AVLTree knowledgeBase = new AVLTree();
        Scanner keyboard = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Choose an action from the menu:");
            System.out.println("1. Load a knowledge base from a file");
            System.out.println("2. Add a file with a list of words to perform a search");
            System.out.println("3. Quit");
            System.out.print("Enter your choice: ");

            int input;
            try {
                input = Integer.parseInt(keyboard.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 3.");
                continue;
            }

            switch (input) {
                case 1:
                    // Load knowledge base from file
                    try {
                        File myObj = new File("GenericsKB.txt");
                        Scanner myReader = new Scanner(myObj);
                        while (myReader.hasNextLine()) {
                            String data = myReader.nextLine();
                            String[] parts = data.split("\t", 3);
                            if (parts.length == 3) {
                                String term = parts[0].trim();
                                String statement = parts[1].trim();
                                double confidenceScore = Double.parseDouble(parts[2].trim());
                                knowledgeBase.insert(new Statement(term, statement, confidenceScore));
                            }
                        }
                        myReader.close();
                        System.out.println("Knowledge base loaded successfully.");
                    } catch (FileNotFoundException e) {
                        System.out.println("An error has occurred. The file was not found.");
                    }
                    break;

                case 2:
                    // Process queries from file
                    try {
                        File queryFile = new File("GenericsKB-queries.txt");
                        Scanner quScanner = new Scanner(queryFile);
                        while (quScanner.hasNextLine()) {
                            String term = quScanner.nextLine().trim();
                            Statement searchStatement = new Statement(term, "", 0.0);
                            BinaryTreeNode<Statement> result = knowledgeBase.find(searchStatement);
                            if (result != null) {
                                System.out.println("Found: " + result.data);
                            } else {
                                System.out.println("Term was not found: \"" + term + "\"");
                            }
                        }
                        quScanner.close();
                    } catch (FileNotFoundException e) {
                        System.err.println("Error: GenericsKB-queries.txt not found.");
                    }
                    break;

                case 3:
                    // Quit the program
                    System.out.println("Exiting the program. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }

        keyboard.close();
    }
}