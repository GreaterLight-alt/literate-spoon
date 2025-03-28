import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList ;
import java.util.Collections;

/**
*This the class that allows us to perform the various experiment to see the different number of comparisons as we vary the value of n
*@author 
*@version 1.0
*@since
*/
public class Experiment{

 
 
/*
 * Static Variables to track experiemntal data
 */
  public static ArrayList<Statement> knowledgebase;
  public static int experimentCount;
  public static int experimentSearchCount;
  public static ArrayList<Integer> insertCount;
  public static ArrayList<Integer> searchCount;

 /**
 *This the main program  to run the experiment
 *@param args is the only parameter and this allows an array of parameters to be run
 */
 public static void main(String[] args) throws FileNotFoundException {
        Scanner keyboard = new Scanner(System.in);
        knowledgebase = new ArrayList<Statement>();
        System.out.print("Enter file name that contains the knowledge base: ");
        String fileName = keyboard.next();
        try {
          loadKnowledgeBase(fileName);
          // run experiemnt for different data sizes
          int [] sizes ={10,50,100,250,500,1000,2500,5000,10000,50000};
          for (int size:sizes){
            insertExperiment(size);
          }
          System.out.println("Experiment  completed. Check output files");
        }catch (Exception e){
          System.out.println("An error occurred" + e.getMessage());
        }finally{
          keyboard.close();
        }
        }


        public static void  loadKnowledgeBase(String filename) throws FileNotFoundException {
          
          File file = new File(filename);
          try(Scanner scanner = new Scanner(file)){
            while (scanner.hasNextLine()) {
              String data = scanner.nextLine();
              String[] parts = data.split("\t");
              if (parts.length == 3) {
                Statement object = new Statement(parts[0], parts[1], Double.parseDouble(parts[2]));
                 knowledgebase.add(object);

              }
            }
                
         System.out.println("Knowledge base loaded successfully.Total entries: "+ knowledgebase.size());
          }catch(NumberFormatException e)  {
            System.out.println("Error parsing confidence score in knowledge base");
            throw e;
          }
        }         
                
      
        /**
        * This method is the one that is used to do the experiment of the varying sizes and get the various counts that are needed in each size
        * @param Size This is the only parameter and is of type int
        * The method allows writes to a file the number of operations that are done when inserting a node each time. 
        * It also write the total comparisons to the file
        */
        public static void insertExperiment(int size) {
           AVLTree knowledgebaseTree = new AVLTree ();
        
           //shuffle the knowledge base to randomise insertion
           Collections.shuffle(knowledgebase);
           experimentCount = 0;
           insertCount = new ArrayList<Integer> ();
           try{
           FileWriter form = new FileWriter("insertCount.txt",true);
           form.write("Number of comparisons done when n is " + size + "\n");
         for (int run = 0 ;run < 10 ;run++){// run 10 times for each size
          knowledgebaseTree.resetCounters();
          insertCount.clear();
          experimentCount = 0;
         // perform insertions and track comparisons
          for(int i = 0; i < Math.min(size, knowledgebase.size()); i++) {
            knowledgebaseTree.resetCounters();
            knowledgebaseTree.insert(knowledgebase.get(i));

            int insertComparisons = knowledgebaseTree.getInsertCount();
            insertCount.add(insertComparisons);
            experimentCount+= insertComparisons;
            form.write((i + 1)+ " " + insertComparisons + "\n");
          }

          
          form.write("Total comparisons: " + calculateSum(insertCount) + "\n");
          form.write("The best case(min comparisons): " + Collections.min(insertCount) + "\n");
          form.write("The worst case(max comparions): " + Collections.max(insertCount) + "\n");
          form.write("The average case: " + calculateAverage(insertCount)/(double) size + "\n");
          saveToCSV("experimentResults.csv", insertCount, new ArrayList<>(), size, "GenericsKB-queries.txt");
         }
      

        form.close(); 
  
        //perform search experiment on created tree
        searchExperiment(knowledgebaseTree,"GenericsKB-queries.txt",size);
        
        
      }catch(Exception e) {
          System.out.println("An error ocurred during experinment" + e.getMessage());
        }
      }
        
      /**
      * This methods is counts the number of search comparisons that are done each time when adding a file
      * @param tree This is the same tree that was used in the insertExperiment that is used here
      * @param File This is the filename of the queries that are going to be searched in the file
      */ 
      public static void searchExperiment(AVLTree  tree, String queryFile, int size) {
        searchCount = new ArrayList<Integer>();
        experimentSearchCount= 0;
      
      
        try{
           FileWriter form = new FileWriter("searchCount.txt",true);
           
           form.write("Number of comparisons done when searching a sample of size n:" + size + "\n");
           for(int run = 0 ; run < 10;run++){
            searchCount.clear();// clear each search run before each count
            experimentSearchCount = 0;

           
           try (Scanner scan = new Scanner(new File(queryFile))) {
            
               int count = 0;
               while (scan.hasNextLine() && count < size) {
                  String line = scan.nextLine().trim();
                  tree.resetCounters();
                  Statement searchStatement = new Statement(line, "", 0.0);
                  tree.find(searchStatement);
                  int searchComparisons = tree.getSearchCount();
                  form.write(searchComparisons + "\n");
                  searchCount.add(searchComparisons);
                  experimentSearchCount += searchComparisons;
                  
                  count++;
               }
              
               form.write("Total search comparisons " + experimentSearchCount + "\n");
               form.write("The best case: " + Collections.min(searchCount) + "\n");
               form.write("The worst case: " + Collections.max(searchCount) + "\n");
               form.write("The average case: " + calculateAverage(searchCount) / (double) size + "\n");
             saveToCSV("experimentResults.csv", new ArrayList<>(), searchCount, size, queryFile);  
           }
          }
          form.close();
                      
            } catch (FileNotFoundException e) {
                           System.out.println("File Not Found" +queryFile);
            } catch (Exception e) {
              System.out.println("An error occurred during search experiment: "+ e.getMessage());
            }
        }
    
        /*
         * calculate the total of a list of integers
         */
        public static int calculateSum(ArrayList<Integer> list) {
            int total = 0;
            for (int element : list) {
                total += element;
            }
            return total;
        }

        /*
         * calculate the average of a list of integers
         */
        public static double calculateAverage(ArrayList<Integer> list) {
            int total = calculateSum(list);
            return (double)total/ list.size();
        }
        /*
         * write my experiments into a csv file
         */
        public static void saveToCSV(String filename, ArrayList<Integer> insertData, ArrayList<Integer> SearchData, int size, String fileName){
                  try(FileWriter writer = new FileWriter(filename, true)){
                    File file = new File(fileName);
                    if(file.length() == 0 && insertData.size() > 0){
                      writer.write("Sample Size, Insert Run, Insert Comparisons, Search Run, Search Comparisons\n");
                    }
                    // save the insert results
                  for( int i = 0 ; i < Math.min(insertData.size(), SearchData.size());i++){
                
                    writer.write(size+ ","+ (i+1)+","+insertData.get(i)+","+(i+1)+ "," + SearchData.get(i)+ "\n");
          }

          }catch (IOException e){
            System.out.println("Error writing to CSV: "+ e.getMessage());
          }
        }
      }
      

