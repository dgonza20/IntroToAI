// David Gonzales

// Assignment 2: Astar Algorithm

package aiassignment2;

/**
 *
 * @author degme
 */
import java.util.*;
import java.util.Scanner;

/**
 *
 * @author degme
 */
public class AIAssignment2 {
    
    public static Node startNode;
    public static Node goalNode;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        System.out.println("Hello welcome to the Astar Algortihm");        
        
        setChoice();
        
        Astar astar = new Astar(startNode, goalNode);
        
        astar.printBoard();
        
        List<Node> star =astar.AstarAlg();
        
        // If no path was found
        if(star.isEmpty()){
            System.out.println("No path exist");
        }else{
        
        System.out.println("");
        System.out.println("");
        
        // Prints out the nodes taken to get to the goal
        astar.printPath();
        
        System.out.println("");
        System.out.println("");
        
        // Prints out the final board
        astar.printPathBoard();
        }
    }
    
    
    
    
    public static void setChoice(){
        Scanner choice = new Scanner(System.in);
        int pick =0 ;
        int row;
        int col;
        
        
        
        
            try{
                System.out.println("Please enter '1' if you would like to enter the starting node and end node");
                System.out.println("Or enter '2' if you would like them to to be randomly placed: ");
                pick = choice.nextInt(); 
            }
            catch(InputMismatchException ime){
                System.out.println("You did not enter 1 or 2 ");
            }
            catch(NoSuchElementException e){
                System.out.println("You did not enter a number");
            }
        
        


        if(pick == 1){
            System.out.println("Please enter the row and column (both between 1 and 15) for the starting node");
            System.out.println("Row: ");
            row = choice.nextInt() -1;
            System.out.println("Columun: ");
            col = choice.nextInt() -1;
            startNode = new Node(row,col);
            System.out.println("Please enter the row and column (both between 1 and 15)for the goal node");
            System.out.println("Row: ");
            row=choice.nextInt() -1;
            System.out.println("Columun: ");
            col = choice.nextInt() -1;
            goalNode = new Node(row, col);
        }
        else if(pick == 2){
            Random rand = new Random();
            row = rand.nextInt(14);
            col = rand.nextInt(14);
            startNode=new Node(row, col);
            row = rand.nextInt(14);
            col = rand.nextInt(14);
            goalNode = new Node(row, col);
        }
    }
    
}
